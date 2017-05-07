package com.ibm.watsonwork;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties
public class GoogleProperties {

    @Value("${google.api.uri}")
    private String apiUri;

    @Value("${google.api.key}")
    private String apiKey;

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "GoogleProperties{" +
                "apiUri='" + apiUri + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
