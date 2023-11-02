package com.pubsub.demo.config;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.TopicName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class PubsubConfig {
    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;
    @Value("${pubsub.topic-id}")
    private String topicId;
    @Value("${pubsub.subscription-id}")
    private String subscriptionId;
    @Autowired
    private CredentialsConfig credentialsConfig;

    private TopicName topicName() {
        return TopicName.of(projectId, topicId);
    }

    @Bean
    public Publisher publisher() throws IOException {
        return Publisher.newBuilder(topicName())
                .setCredentialsProvider(credentialsConfig).build();
    }

    public CredentialsConfig getCredentialsConfig() {
        return credentialsConfig;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

}
