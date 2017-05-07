package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.List;

import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.model.graphql.*;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Service
public class DefaultGraphQLService implements GraphQLService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGraphQLService.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private GraphQLClient graphQLClient;


    @Override
    public void createMessage(WebhookEvent webhookEvent, TargetedMessage targetedMessage) {
        String body = "mutation {" +
                "createMessage(input: {conversationId: \"" + targetedMessage.getConversationId() + "\"," +
                "annotations: [{genericAnnotation: {title:\"" + targetedMessage.getAnnotation().getTitle() + "\",text:\"" + targetedMessage.getAnnotation().getText() + "\"" +
                "}}]}) {" +
                "message {id}" +
                "}}";

        GraphQLQuery graphqlQuery = new GraphQLQuery();
        graphqlQuery.setQuery(body);

        Call<GraphQLResponse> call = graphQLClient.createMessage(authService.getAppAuthToken(), graphqlQuery);

        call.enqueue(new Callback<GraphQLResponse>() {
            @Override
            public void onResponse(Call<GraphQLResponse> call, Response<GraphQLResponse> response) {
                LOGGER.info("GraphQL call createTargetedMessage successful.");
            }

            @Override
            public void onFailure(Call<GraphQLResponse> call, Throwable t) {
                LOGGER.error("GraphQL call createTargetedMessage failed.", t);
            }
        });

    }

    @Override
    public void createTargetedMessage(WebhookEvent webhookEvent, TargetedMessage targetedMessage) {
        Annotation annotation = targetedMessage.getAnnotation();
        List<Button> buttons = annotation.getButtons();

        StringBuilder buttonString = new StringBuilder();
        if (buttons != null) {
            buttonString = new StringBuilder(",buttons:[");
            for (Button button : buttons) {
                buttonString.append("{ postbackButton: {id:\"").append(button.getId()).append("\",title:\"").append(button.getTitle()).append("\",style:PRIMARY} }");
            }
            buttonString.append("]");
        }

        System.out.println("targetedMessage=" + targetedMessage.toString());
        String body = "mutation {" +
                "createTargetedMessage(input: {conversationId: \"" + targetedMessage.getConversationId() + "\"," +
                "targetDialogId: \"" + targetedMessage.getTargetDialogId() + "\"," +
                "targetUserId: \"" + targetedMessage.getTargetUserId() + "\"," +
                "annotations: [{genericAnnotation: {title:\"" + targetedMessage.getAnnotation().getTitle() + "\",text:\"" + targetedMessage.getAnnotation().getText() + "\"" +
                buttonString +
                "}}]}) {" +
                "successful" +
                "}}";

        GraphQLQuery graphqlQuery = new GraphQLQuery();
        graphqlQuery.setQuery(body);
        LOGGER.info("final mutation: " + graphqlQuery.getQuery());

        Call<GraphQLResponse> call = graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphqlQuery);

        call.enqueue(new Callback<GraphQLResponse>() {
            @Override
            public void onResponse(Call<GraphQLResponse> call, Response<GraphQLResponse> response) {
                LOGGER.info("GraphQL call createTargetedMessage successful."+response);
            }

            @Override
            public void onFailure(Call<GraphQLResponse> call, Throwable t) {
                LOGGER.error("GraphQL call createTargetedMessage failed.", t);
            }
        });

    }

    @Override
    public Message getMessage(String messageId) {

        String body = "query {" +
                "        message(id: \""+ messageId+"\") {" +
                "            id" +
                "            content" +
                "            contentType" +
                "            annotations" +
                "        }" +
                "     }";

        GraphQLQuery graphqlQuery = new GraphQLQuery();
        graphqlQuery.setQuery(body);

        Call<GraphQLResponse> call = graphQLClient.getMessage(authService.getAppAuthToken(), graphqlQuery);

        GraphQLResponse response = new GraphQLResponse();
        Message message = new Message();
        try {
            response = call.execute().body();
            LOGGER.info("Annotations="+response.getData().getMessage());
            message = response.getData().getMessage();

        } catch (IOException e ) {
            LOGGER.error("Found no message with that id",e);
        }

        return message;

    }


}
