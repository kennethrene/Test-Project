package com.test.util;

public class SQS {
    /*private SqsClient sqsClient;
    private String queueName;

    public BatchEvents() {
        this.sqsClient = getSqsClient();
        this.queueName = "taap-notification-api";
    }

    public static void main(String... args) throws IOException, InterruptedException {
        BatchEvents batchEvents = new BatchEvents();
        JsonResourceObjectMapper mapper = new JsonResourceObjectMapper();
        String[] json = mapper.loadJsonAsString("events.json").split("\n");

        String queueUrl = batchEvents.getQueueUrl();
        int counter = 0;

        for (String event: json) {
            counter++;
            System.out.println("Sending: " + event);

            SendMessageRequest send_msg_request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(event)
                    .build();

            batchEvents.sqsClient.sendMessage(send_msg_request);

            if (counter == 100) {
                TimeUnit.SECONDS.sleep(10);
                counter = 0;
            }
        }
    }

    private SqsClient getSqsClient() {
        return SqsClient.builder()
                .region(Region.of("us-west-2"))
                .build();
    }

    private String getQueueUrl() {
        final GetQueueUrlResponse urlResponse = sqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build());

        return urlResponse.queueUrl();
    }
}

class JsonResourceObjectMapper {
    private ObjectMapper objectMapper;

    public JsonResourceObjectMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public String loadJsonAsString(String fileName) throws IOException {
        String text;
        ClassLoader classLoader = this.getClass().getClassLoader();

        try (InputStream inputStream= classLoader.getResourceAsStream(fileName)) {
            text = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

        return text;
    }*/
}
