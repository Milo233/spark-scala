package com.milo.spark.kafka;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer extends Thread {

    private String topic;

    public KafkaConsumer(String topic) {
        this.topic = topic;
    }

    private ConsumerConnector createConnector(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect", KafkaProperties.ZK);
        properties.put("group.id",KafkaProperties.GROUP_ID);
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    @Override
    public void run() {
        ConsumerConnector consumer = createConnector();

        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
//        topicCountMap.put(topic2, 1);
//        topicCountMap.put(topic3, 1);

        // String: topic
        // List<KafkaStream<byte[], byte[]>>  对应的数据流
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStream =  consumer.createMessageStreams(topicCountMap);
        //获取我们每次接收到的数据 因为是从index=0处开始，每次重新启动都可能再次收到之前已经处理过的数据
        KafkaStream<byte[], byte[]> stream = messageStream.get(topic).get(0);

        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();

        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println("===========> receive msg: " + message);
        }
    }

}
