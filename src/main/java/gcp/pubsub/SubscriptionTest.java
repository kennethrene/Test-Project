package gcp.pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import gcp.config.Connection;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubscriptionTest {
    private String projectId;

    public SubscriptionTest(String projectId) {
        this.projectId = projectId;
    }

    public void consumeMessage(String subscriptionId) {
        ProjectSubscriptionName subscriptionName= ProjectSubscriptionName.of(projectId, subscriptionId);

        MessageReceiver messageReceiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
            System.out.println("Receiving Id: " + message.getMessageId());
            System.out.println("Data: " + message.getData().toStringUtf8());
            consumer.ack();
        };

        Subscriber subscriber = null;

        try {
            subscriber = Subscriber
                    .newBuilder(subscriptionName, messageReceiver)
                    .setCredentialsProvider(() -> Connection.getInstance().getCredentials())
                    .build();
            subscriber.startAsync();

            System.out.printf("Listening for messages on %s:\n", subscriptionName);
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            subscriber.stopAsync();
        }
    }
}
