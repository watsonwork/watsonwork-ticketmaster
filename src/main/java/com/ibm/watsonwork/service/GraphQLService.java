package com.ibm.watsonwork.service;

import com.ibm.watsonwork.model.graphql.Message;
import com.ibm.watsonwork.model.graphql.TargetedMessage;
import com.ibm.watsonwork.model.graphql.WebhookEvent;

public interface GraphQLService extends Service {

    void createMessage(WebhookEvent webhookEvent, TargetedMessage targetedMessage);

    void createTargetedMessage(WebhookEvent webhookEvent, TargetedMessage targetedMessage);

    Message getMessage(String messageId);

}
