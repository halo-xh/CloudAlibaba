package com.example.cloudsimple.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaITest {


    public static void main(String[] args) throws InterruptedException {
        // 测试kafka幂等遇见长事务是会会阻塞消费
        testLongTx();

    }

    /**
     * Last Stable Offset（LSO）
     * 在事务机制的实现中，Kafka 又设置了一个新的 offset 概念，那就是 Last Stable Offset，简称 LSO（其他的 Offset 概念可参考 Kafka Offset 那些事），先看下 LSO 的定义：
     * The LSO is defined as the latest offset such that the status of all transactional messages at lower offsets have been determined (i.e. committed or aborted).
     * <p>
     * 对于一个 Partition 而言，offset 小于 LSO 的数据，全都是已经确定的数据，这个主要是对于事务操作而言，在这个 offset 之前的事务操作都是已经完成的事务（已经 commit 或 abort），如果这个 Partition 没有涉及到事务数据，那么 LSO 就是其 HW（水位）。
     * Server 处理 read_committed 类型的 Fetch 请求
     * 如果 Consumer 的消费策略设置的是 read_committed，其在向 Server 发送 Fetch 请求时，Server 端只会返回 LSO 之前的数据，在 LSO 之后的数据不会返回。
     * <p>
     * 这种机制有没有什么问题呢？我现在能想到的就是如果有一个 long transaction，比如其 first offset 是 1000，另外有几个已经完成的小事务操作，
     * 比如：txn1（offset：1100~1200）、txn2（offset：1400~1500），假设此时的 LSO 是 1000，也就是说这个 long transaction 还没有完成，
     * 那么已经完成的 txn1、txn2 也会对 consumer 不可见（假设都是 commit 操作），此时受 long transaction 的影响可能会导致数据有延迟。
     */
    public static void testLongTx() throws InterruptedException {
        // 消费
        KafkaConsumer<String, String> consumer = getKafkaConsumer();
        consumer.subscribe(Collections.singletonList("testLongTx"));
        new Thread(() -> doConsume(consumer)).start();
        new Thread(() -> {
            try {
                mockData();
            }catch (Exception e){
            }
        }).start();
        KafkaProducer<String, String> kafkaProducer = getKafkaProducer("222");
        TimeUnit.SECONDS.sleep(1);
        kafkaProducer.initTransactions();
        //模拟长事务
        kafkaProducer.beginTransaction();
        kafkaProducer.send((new ProducerRecord<String, String>("testLongTx", "messageLog2" )));
        TimeUnit.SECONDS.sleep(60);
        kafkaProducer.commitTransaction();
        kafkaProducer.close();
        // todo. 结果长事务会阻塞消费，现象：在长事务过程中用工具查看有新消息被保存到broker，但是消费者无法消费新数据直至事务结束。
    }

    private static void mockData() {
        KafkaProducer<String, String> kafkaProducer = getKafkaProducer(null);
        //模拟数据
        for (int i = 100; i < 2000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kafkaProducer.send((new ProducerRecord<String, String>("testLongTx", "message" + i)));
        }
    }

    private static void doConsume(KafkaConsumer<String, String> consumer) {
        try {
            while (true) {
                //拉取消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(Integer.MAX_VALUE));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf(Thread.currentThread().getName() + "offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
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
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //KafkaConsumer类不是线程安全的
        return new KafkaConsumer<>(props);
    }

    private static KafkaProducer<String, String> getKafkaProducer(String txId) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
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
