package com.ltlon.restapi.code.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class producer {

    private static final String brokerList = "188.131.134.127:9092";
    private static final String topic = "topic-demo";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        //线程安全
        KafkaProducer producer = new KafkaProducer(properties);
        int i = 100;
        while (i<100){
            ProducerRecord producerRecord = new ProducerRecord(topic,"Hello-topic");
            i++;
            producer.send(producerRecord);
        }
        producer.close();
    }
}
