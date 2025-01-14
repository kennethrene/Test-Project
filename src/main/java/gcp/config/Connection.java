package gcp.config;

import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.InputStream;

public final class Connection {
    private static Connection INSTANCE;
    private ServiceAccountCredentials serviceAccountCredentials;

    public Connection() {
        String credentialsFilePath = "gcp/test-project-1.json";

        try {
            InputStream credentialsStream = Connection.class
                    .getClassLoader()
                    .getResourceAsStream(credentialsFilePath);

            if (credentialsStream == null) {
                throw new IllegalArgumentException("It was not possible to load the file: " + credentialsFilePath);
            }

            serviceAccountCredentials = ServiceAccountCredentials.fromStream(credentialsStream);
        } catch (Exception e) {
            System.err.println("Connection error in Google Cloud: " + e.getMessage());
        }
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Connection();
        }

        return INSTANCE;
    }

    public ServiceAccountCredentials getCredentials() {
        return serviceAccountCredentials;
    }
}
