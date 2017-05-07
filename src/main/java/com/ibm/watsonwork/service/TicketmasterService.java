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

package com.ibm.watsonwork.service;

import com.ibm.watsonwork.model.graphql.AnnotationPayload;
import com.ibm.watsonwork.model.graphql.TargetedMessage;
import com.ibm.watsonwork.model.graphql.WebhookEvent;

public interface TicketmasterService extends Service {

    void findEvents(WebhookEvent webhookEvent, AnnotationPayload annotationPayload);

    void getEvent(WebhookEvent webhookEvent);

    void shareEvent(WebhookEvent webhookEvent);

}
