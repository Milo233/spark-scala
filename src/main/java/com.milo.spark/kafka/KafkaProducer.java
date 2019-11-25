package com.milo.spark.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 *  kafka 生产者
 *  写到服务器的kafka时可能出现 kafka.common.FailedToSendMessageException
 *  需要把kafka 配置文件加上一行 advertised.host.name=外网ip地址 后重启
 */
public class KafkaProducer extends Thread {

    private String topic;

    private Producer<Integer, String> producer;

    public KafkaProducer(String topic) {
        this.topic = topic;

        Properties properties = new Properties();
        properties.put("metadata.broker.list", KafkaProperties.BROKER_LIST);
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("request.required.acks", "1");

        producer = new Producer<Integer, String>(new ProducerConfig(properties));
    }

    @Override
    public void run() {

        int messageNo = 1;

        while (true) {
            String message = "heyman!! message_" + messageNo;
            producer.send(new KeyedMessage<>(topic, message));
            System.out.println("Sent: " + message);

            messageNo++;

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new KafkaProducer(KafkaProperties.TOPIC).start();

        new KafkaConsumer(KafkaProperties.TOPIC).start();
    }

}
