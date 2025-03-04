package gcp.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import gcp.config.Connection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PublisherClient {
    private String projectId;

    public PublisherClient(String projectId) {
        this.projectId = projectId;
    }

    public String publishMessage(String topicId, Object message, Class<?> avroSchemaClass) throws IOException, ExecutionException, InterruptedException {
        Publisher publisher = null;
        String messageJson = toJson(message);

        if (isValidMessage(messageJson, avroSchemaClass)) {
            try {
                TopicName topicName = TopicName.of(projectId, topicId);
                publisher = Publisher.newBuilder(topicName)
                        .setCredentialsProvider(() -> Connection.getInstance().getCredentials())
                        .build();

                ByteString data = ByteString.copyFromUtf8(messageJson);
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
        } else {
            return "";
        }
    }

    private String toJson(Object objectMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(objectMessage);
    }

    private boolean isValidMessage(String message, Class<?> avroSchemaClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readValue(message, avroSchemaClass);
        } catch (JsonProcessingException e) {
            System.out.println("Invalid message: " + message);
            return false;
        }

        return true;
    }
}
