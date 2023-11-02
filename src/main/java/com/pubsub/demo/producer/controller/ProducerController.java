package com.pubsub.demo.producer.controller;

import com.pubsub.demo.producer.service.ProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/producer/push")
    public String produceMessage(@RequestParam String message) throws IOException {

        producerService.sendMessage(message);
        return "Message: '" + message + "' successfully send at " + LocalDateTime.now();
    }

}
