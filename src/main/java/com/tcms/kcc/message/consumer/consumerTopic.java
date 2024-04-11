package com.tcms.kcc.message.consumer;

import java.util.Arrays;
import java.util.Objects;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class consumerTopic {

    private final KafkaListenerEndpointRegistry registry;

    @Value("${topic.name.consumer")
    private String topicName;

    //private final Acknowledgment ack;

    @KafkaListener(id="consumer1",topics = "${topic.name.consumer}", containerFactory = "topicoTeste", groupId = "group_id", autoStartup = "false")
    public void consume(ConsumerRecord<String, String> payload, Acknowledgment ack){
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

        ack.acknowledge();

    }
  

    @EventListener(condition = "event.listenerId.startsWith('consumer1')")
    public void idleEventHandler(ListenerContainerIdleEvent event){
        log.info("Stopping processing consumer");
        registry.stop();
    }

}