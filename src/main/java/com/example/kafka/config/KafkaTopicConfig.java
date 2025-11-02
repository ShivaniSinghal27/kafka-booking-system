package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${app.kafka.topic}")
    private String mainTopic;

    @Value("${app.kafka.partitions}")
    private int partitions;

    @Value("${app.kafka.replicas}")
    private int replicas;

    @Bean
    public NewTopic bookingCommandsTopic() {
        return TopicBuilder.name(mainTopic)
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic bookingCommandsRetry0() {
        return TopicBuilder.name(mainTopic + "-retry-0")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic bookingCommandsRetry1() {
        return TopicBuilder.name(mainTopic + "-retry-1")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic bookingCommandsRetry2() {
        return TopicBuilder.name(mainTopic + "-retry-2")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }

    @Bean
    public NewTopic bookingCommandsDlt() {
        return TopicBuilder.name(mainTopic + "-dlt")
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }
}
