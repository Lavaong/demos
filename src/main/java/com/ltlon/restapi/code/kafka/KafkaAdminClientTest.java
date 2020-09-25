package com.ltlon.restapi.code.kafka;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaAdminClientTest {

    static final String brokerList = "188.131.134.127:9092";
    static final String topic = "topic-admin";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG,30000);
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic(topic,4,(short)1);
        NewPartitions newPartitions = NewPartitions.increaseTo(6);
        Map<String,NewPartitions> newPartitionsMap = new HashMap<>();
        newPartitionsMap.put(topic,newPartitions);
        //CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));
        CreatePartitionsResult result = adminClient.createPartitions(newPartitionsMap);
        try {
            //result.all().get();
            result.all().get();
            ListTopicsResult topicsResult = adminClient.listTopics();
            topicsResult.listings().get().forEach(listing -> System.err.println(listing));
            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singleton(topic));
            describeTopicsResult.all().get().forEach((key,value) -> {
                System.err.println(key);
                System.err.println(value.toString());
            });
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        adminClient.close();
    }
}
