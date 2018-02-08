package com.example.demo.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Data
@Service("ProducerService")
public class KafkaProducerService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void sendMsg(String topicName, Object object) {
        kafkaProducer.send(new ProducerRecord<>(topicName, object), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (metadata != null) {
                    log.info("Send Success Metadata [{}]", metadata);
                }
                if (exception != null) {
                    log.info("Send Fail Exception [{}]", exception);
                }
            }
        });
    }
}
