package com.ibm.watsonwork;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class WatsonWorkProperties {

    @Value("${watsonwork.webhook.secret}")
    private String webhookSecret;

    @Value("${watsonwork.app.id}")
    private String appId;

    @Value("${watsonwork.app.secret}")
    private String appSecret;

    @Value("${watsonwork.api.uri}")
    private String apiUri;

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public void setWebhookSecret(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    @Override
    public String toString() {
        return "WatsonWorkProperties{" +
                "webhookSecret='" + webhookSecret + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", apiUri='" + apiUri + '\'' +
                '}';
    }
}
