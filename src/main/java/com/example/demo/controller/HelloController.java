package com.example.demo.controller;

import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.common.GlobaConstant.TEST_TOPIC;

/**
 * @author YuanJian
 * @data 18-2-8下午5:14
 */
@RestController
public class HelloController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/hello")
    public void hello() {
        for (int i = 0; i < 5; i++) {
            kafkaProducerService.sendMsg(TEST_TOPIC, i + "");
        }
    }
}
