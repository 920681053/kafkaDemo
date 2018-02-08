package com.example.demo.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author YuanJian
 * @data 18-2-8下午2:17
 */
@Configuration
public class KafkaConfig {

    @Bean
    public KafkaConsumer getConsumerConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.4.208:9092");
        properties.put("group.id", "device");
        properties.put("enable.auto.commit", true);
        properties.put("auto.commit.interval.ms", 1000);
        properties.put("session.timeout.ms", 100000);
        properties.put("request.timeout.ms", 120000);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(properties);
    }

    @Bean
    public KafkaProducer getProducerConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.4.208:9092");
        properties.put("metadata.fetch.timeout.ms", 30000);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("linger.ms", 1);
        properties.put("batch.size", 16384);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(properties);
    }
}
