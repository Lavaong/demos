package com.ltlon.restapi.code.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    private static final String brokerList = "188.131.134.127:9092";
    private static final String topic = "topic-demo";
    private static final String GROUP_ID = "group.demo";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id",GROUP_ID);
        KafkaConsumer consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singletonList(topic));
        try {
                ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ofMinutes(1000));
                for(ConsumerRecord consumerRecord : consumerRecords){
                    System.err.println(consumerRecord.value());
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
