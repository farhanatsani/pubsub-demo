package com.pubsub.demo.consumer.service;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.pubsub.demo.config.PubsubConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ConsumerService {
    private final PubsubConfig pubsubConfig;
    public void subscribeAsyncExample() {

        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(
                pubsubConfig.getProjectId(), pubsubConfig.getSubscriptionId()
        );

        // Instantiate an asynchronous message receiver.
        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> {
                    // Handle incoming message, then ack the received message.
                    log.info("Consume message ID: {}", message.getMessageId());
                    log.info("Data: {}", message.getData().toStringUtf8());
                    consumer.ack();
                };

        Subscriber subscriber = null;
        subscriber = Subscriber.newBuilder(subscriptionName, receiver)
                .setCredentialsProvider(pubsubConfig.getCredentialsConfig())
                .build();
        // Start the subscriber.
        subscriber.startAsync().awaitRunning();
        log.info("Listening for messages on {}", subscriptionName.toString());
        // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
        subscriber.awaitRunning();
    }
}
