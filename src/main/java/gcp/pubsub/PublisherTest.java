package gcp.pubsub;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import gcp.config.Connection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PublisherTest {
    private String projectId;

    public PublisherTest(String projectId) {
        this.projectId = projectId;
    }

    public String publishMessage(String topicId, String message) throws IOException, ExecutionException, InterruptedException {
        Publisher publisher = null;
        try {
            TopicName topicName = TopicName.of(projectId, topicId);
            publisher = Publisher.newBuilder(topicName)
                    .setCredentialsProvider(() -> Connection.getInstance().getCredentials())
                    .build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            System.out.println("Pub/Sub Message Id: " + messageIdFuture.get());

            return messageIdFuture.get();
        } finally {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(30, TimeUnit.SECONDS);
            }
        }
    }
}
