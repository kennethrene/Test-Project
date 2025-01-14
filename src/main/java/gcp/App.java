package gcp;

import gcp.pubsub.PublisherTest;
import gcp.pubsub.SubscriptionTest;

public class App {
    private static final String PROJECT_ID = "test-project-1-447321";

    private PublisherTest publisherTest;
    private SubscriptionTest subscriptionTest;

    public App() {
        publisherTest = new PublisherTest(PROJECT_ID);
        subscriptionTest = new SubscriptionTest(PROJECT_ID);
    }

    public static void main(String ... args) {
        App app = new App();

        try {
            //app.publisherTest.publishMessage("user-notifications", "Notification " + Math.round(Math.random() * 10000));
            app.subscriptionTest.consumeMessage("subscription1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
