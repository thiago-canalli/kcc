package com.tcms.kcc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consume-control")
@RequiredArgsConstructor
public class ProcessTopicController {

    private final KafkaListenerEndpointRegistry registry;

    @GetMapping(value = "/{valor}")
    public void kafkaStart(@PathVariable String valor) {
       String value = valor.isBlank()? "start":valor;
        this.run(value);

    }

    @Async
    public void run(String value){

        if (value.equals("start")) {
            log.info("Starting processing consumer");
            this.registry.getListenerContainer("consumer1").start();

        }

        if (value.equals("stop")) {
            this.registry.getListenerContainer("consumer1").stop();
            log.info("Stopping processing consumer");
        }
    }
}