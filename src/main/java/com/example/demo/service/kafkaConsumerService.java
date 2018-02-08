package com.example.demo.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.GlobaConstant.TEST_TOPIC;

@Slf4j
@Data
@Service("ConsumerService")
public class kafkaConsumerService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            log.info("Kafka Consumer Service Start Success");
            consumeData();
        }).start();
    }

    private void consumeData() {
        try {
            List<String> topicList = new ArrayList<>();
            topicList.add(TEST_TOPIC);
            kafkaConsumer.subscribe(topicList);
            while (true) {
                ConsumerRecords<Object, Object> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<Object, Object> record : records) {
                    if (record.value() != null) {
                        String json = record.value().toString();
                        switch (record.topic()) {
                            case TEST_TOPIC:
                                exce(json);
                                break;
                            default:
                                break;
                        }
                    }
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exce(String json) {
        System.err.println(json);
    }
}

