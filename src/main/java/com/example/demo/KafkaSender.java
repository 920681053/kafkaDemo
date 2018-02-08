package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author YuanJian
 * @data 18-2-8上午11:05
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void send(Object object) {
        kafkaTemplate.send("test", object);
    }
}