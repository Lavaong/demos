package com.ltlon.restapi.code.kafka;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
消息消费拦截器,消息过期
 */
public class ConsumerInterceptorTTL implements ConsumerInterceptor<String,String> {


    private static final long EXPIRE_INTERVAL = 10 * 1000;

    /**
     * poll方法返回前的过滤
     * @param consumerRecords
     * @return
     */
    @Override
    public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {
        long now = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
        Set<TopicPartition> topicPartitionSet= consumerRecords.partitions();
        for (TopicPartition tp : topicPartitionSet) {
            List<ConsumerRecord<String,String>> tpRecords = consumerRecords.records(tp);
            List<ConsumerRecord<String,String>> newTpRecords = consumerRecords.records(tp);
            for(ConsumerRecord consumerRecord : tpRecords){
                if(now-consumerRecord.timestamp() < EXPIRE_INTERVAL){
                    newTpRecords.add(consumerRecord);
                }
            }
            if(!newTpRecords.isEmpty()){
                newRecords.put(tp,newTpRecords);
            }
        }
        return new ConsumerRecords(newRecords);
    }

    @Override
    public void close() {

    }

    /**
     * 提交位移时的过滤
     * @param offsets
     */
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        offsets.forEach((tp,offset) -> System.out.println(tp+"_"+offset.offset()));
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
