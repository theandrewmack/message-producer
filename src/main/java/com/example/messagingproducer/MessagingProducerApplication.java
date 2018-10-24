package com.example.messagingproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableBinding(Source.class)
@EnableScheduling
public class MessagingProducerApplication {

    @Autowired
    private Source source;

    public static void main(String[] args) {
        SpringApplication.run(MessagingProducerApplication.class, args);
    }

    @Scheduled(fixedRate = 1000)
    public void logMessage() {
        String message = "Dummy String" + LocalDateTime.now().toString();
        System.out.println(message);
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
