package com.example.cloudsimple.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

// 测试位移提交导致重复消费
public class KafkaITestCommit {


    public static void main(String[] args) throws InterruptedException {
//        mockData();
        testCommit();
        TimeUnit.SECONDS.sleep(1000);
    }

    private static void testCommit() {
        KafkaConsumer<String, String> consumer = getKafkaConsumer();
        consumer.subscribe(Collections.singletonList("testCommit123"));
        doConsume(consumer);
    }


    private static void mockData() {
        KafkaProducer<String, String> kafkaProducer = getKafkaProducer(null);
        //模拟数据
        for (int i = 0; i < 100; i++) {
            RecordHeaders headers = new RecordHeaders();
            headers.add("number", String.valueOf(i).getBytes());
            System.out.println("send = " + i);
            kafkaProducer.send((new ProducerRecord<String, String>("testCommit123", null, null, "message-" + i, headers)));
        }
    }

    private static void doConsume(KafkaConsumer<String, String> consumer) {
        try {
            //拉取消息
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(20 * 1000));
                if (records.isEmpty()) {
                    return;
                }
                for (ConsumerRecord<String, String> record : records) {
                    long offset = record.offset();
                    System.out.printf("offset = %d, key = %s, value = %s%n", offset, record.key(), record.value());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private static KafkaConsumer<String, String> getKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
//        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //KafkaConsumer类不是线程安全的
        return new KafkaConsumer<>(props);
    }

    private static KafkaProducer<String, String> getKafkaProducer(String txId) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        if (txId != null) {
            properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, txId);
            properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        }
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(properties);
    }

}
