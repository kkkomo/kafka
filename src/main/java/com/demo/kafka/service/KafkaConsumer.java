package com.demo.kafka.service;

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

    public String topic;
    public KafkaConsumer(String topic) {
        this.topic = topic;
    }

    public ConsumerConnector getConnection() {
        Properties properties = new Properties();
        properties.put("group.id", KafkaProperties.GROUP_ID);
        properties.put("zookeeper.connect", KafkaProperties.ZK);
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    @Override
    public void run() {
        ConsumerConnector consumer = getConnection();

        Map<String, Integer> topicMap = new HashMap<String, Integer>();
        //从1个kafkaStream消费数据
        topicMap.put(topic, 1);
        //String:topic
        //List<KafkaStream<byte[], byte[]>>对应的数据流
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStream = consumer.createMessageStreams(topicMap);

        //获取每次接收到的数据
        KafkaStream<byte[], byte[]> stream = messageStream.get(topic).get(0);

        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();

        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println("receive message: " + message);
        }
    }
}
