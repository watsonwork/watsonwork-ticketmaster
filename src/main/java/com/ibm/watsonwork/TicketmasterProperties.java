/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2017
 *
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 */

package com.ibm.watsonwork;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties
public class TicketmasterProperties {

    @Value("${ticketmaster.api.uri}")
    private String apiUri;

    @Value("${ticketmaster.api.consumer-key}")
    private String apiConsumerKey;

    @Value("${ticketmaster.api.consumer-secret}")
    private String apiConsumerSecret;

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getApiConsumerKey() {
        return apiConsumerKey;
    }

    public void setApiConsumerKey(String apiConsumerKey) {
        this.apiConsumerKey = apiConsumerKey;
    }

    public String getApiConsumerSecret() {
        return apiConsumerSecret;
    }

    public void setApiConsumerSecret(String apiConsumerSecret) {
        this.apiConsumerSecret = apiConsumerSecret;
    }

    @Override
    public String toString() {
        return "TicketmasterProperties{" +
                "apiUri='" + apiUri + '\'' +
                ", apiConsumerKey='" + apiConsumerKey + '\'' +
                ", apiConsumerSecret='" + apiConsumerSecret + '\'' +
                '}';
    }
}
