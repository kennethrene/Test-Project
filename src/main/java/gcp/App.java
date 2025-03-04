package gcp;

import gcp.dto.CommissionStatement;
import gcp.pubsub.PublisherClient;
import gcp.pubsub.SubscriptionClient;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    private static final String PROJECT_ID = "test-project-1-447321";

    private PublisherClient publisher;
    private SubscriptionClient subscription;

    public App() {
        publisher = new PublisherClient(PROJECT_ID);
        subscription = new SubscriptionClient(PROJECT_ID);
    }

    public static void main(String ... args) {
        App app = new App();

        try {
            Instant now = Instant.now();
            ZonedDateTime dateTime = now.atZone(ZoneId.of("UTC"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateText = dateTime.format(formatter);
            Long dateLong = now.toEpochMilli();

            CommissionStatement commissionStatementNotification = new CommissionStatement(
                    String.valueOf(Math.round(Math.random() * 1000)),
                    "2025-02",
                    "WCR001",
                    dateLong,
                    dateText);

            app.publisher.publishMessage("user-notifications", commissionStatementNotification, CommissionStatement.class);
            //app.subscription.consumeMessage("subscription1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
