package com.ibm.watsonwork;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watsonwork.client.*;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class WatsonWorkConfiguration {

    @Autowired
    private WatsonWorkProperties watsonWorkProperties;

    @Autowired
    private GoogleProperties googleProperties;

    @Autowired
    private TicketmasterProperties ticketmasterProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean(name = "watsonwork")
    @Primary
    public Retrofit retrofit(OkHttpClient client) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(watsonWorkProperties.getApiUri())
                .client(client)
                .build();
    }

    @Bean(name = "google")
    public Retrofit retrofitGoogle(OkHttpClient client) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(googleProperties.getApiUri())
                .client(client)
                .build();
    }

    @Bean(name = "ticketmaster")
    public Retrofit retrofitTicketmaster(OkHttpClient client) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(ticketmasterProperties.getApiUri())
                .client(client)
                .build();
    }

    @Bean
    public GraphQLClient graphQLClient(@Qualifier(value = "watsonwork") Retrofit retrofit) {
        return retrofit.create(GraphQLClient.class);
    }

    @Bean
    public GoogleClient googleClient(@Qualifier(value = "google") Retrofit retrofit) {
        return retrofit.create(GoogleClient.class);
    }

    @Bean
    public TicketMasterClient ticketmasterClient(@Qualifier(value = "ticketmaster") Retrofit retrofit) {
        return retrofit.create(TicketMasterClient.class);
    }

    @Bean
    public AuthClient authClient(Retrofit retrofit) {
        return retrofit.create(AuthClient.class);
    }


}
