package com.ltlon.restapi.code.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadConsumer {

    Logger logger = LoggerFactory.getLogger(MultiThreadConsumer.class);
    public static final String brokerList = "188.131.134.127:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";
    public static Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        return properties;
    }

    public static void main(String[] args) throws Exception {
        Properties properties = initConfig();
        new KafkaConsumerThread(properties, topic, Runtime.getRuntime().availableProcessors()).start();
    }

    private static class KafkaConsumerThread extends Thread {

        private int threadNum;
        private KafkaConsumer<String, String> kafkaConsumer;
        private ExecutorService executorService;


        public KafkaConsumerThread(Properties properties, String topic, int threadNum) {
            this.kafkaConsumer = new KafkaConsumer(properties);
            kafkaConsumer.subscribe(Collections.singletonList(topic));
            this.threadNum = threadNum;
            executorService = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                    if (!records.isEmpty()) {
                        executorService.submit(new ConsumerHandler(records));
                    }
                    //手动提交消费位移
                    synchronized (offsets){
                        if(!offsets.isEmpty()){
                            kafkaConsumer.commitSync(offsets);
                            offsets.clear();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }
    }

    private static class ConsumerHandler implements Runnable {
        private ConsumerRecords<String, String> records;

        protected ConsumerHandler(ConsumerRecords consumerRecords) {
            this.records = consumerRecords;
        }

        @Override
        public void run() {
            for (TopicPartition topicPartition : records.partitions()) {
                //获取该批次消息各个分区的消息
                List<ConsumerRecord<String, String>> tpRecords = records.records(topicPartition);
                //业务处理
                //处理消费位移
                //获取到该分区最后一个消费位移
                long lastConsumeOffset = tpRecords.get(tpRecords.size() - 1).offset();
                synchronized (offsets) {
                    if (!offsets.containsKey(topicPartition)) {
                        offsets.put(topicPartition, new OffsetAndMetadata(lastConsumeOffset + 1));
                    } else {
                        //查看该分区现在的消费位移
                        long position = offsets.get(topicPartition).offset();
                        if (position < lastConsumeOffset + 1) {
                            offsets.put(topicPartition, new OffsetAndMetadata(lastConsumeOffset + 1));
                        }
                    }
                }
            }
        }
    }
}
