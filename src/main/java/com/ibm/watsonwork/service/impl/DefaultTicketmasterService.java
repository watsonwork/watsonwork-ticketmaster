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

package com.ibm.watsonwork.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.ibm.watsonwork.TicketmasterProperties;
import com.ibm.watsonwork.client.TicketMasterClient;
import com.ibm.watsonwork.model.google.LocationData;
import com.ibm.watsonwork.model.graphql.Annotation;
import com.ibm.watsonwork.model.graphql.AnnotationPayload;
import com.ibm.watsonwork.model.graphql.Button;
import com.ibm.watsonwork.model.graphql.Entity;
import com.ibm.watsonwork.model.graphql.TargetedMessage;
import com.ibm.watsonwork.model.graphql.WebhookEvent;
import com.ibm.watsonwork.model.ticketmaster.TicketmasterDate;
import com.ibm.watsonwork.model.ticketmaster.TicketmasterEvent;
import com.ibm.watsonwork.model.ticketmaster.TicketmasterEventContainer;
import com.ibm.watsonwork.model.ticketmaster.TicketmasterEventResponse;
import com.ibm.watsonwork.service.GoogleService;
import com.ibm.watsonwork.service.GraphQLService;
import com.ibm.watsonwork.service.TicketmasterService;
import com.ibm.watsonwork.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Service
public class DefaultTicketmasterService implements TicketmasterService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DefaultTicketmasterService.class);

    @Autowired
    private TicketmasterProperties ticketmasterProperties;

    @Autowired
    private TicketMasterClient ticketMasterClient;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private GraphQLService graphQLService;

    @Override
    public void findEvents(WebhookEvent webhookEvent, AnnotationPayload payloadMessageFocus) {

        String query;
        List<TicketmasterDate> extractedDates = new ArrayList();
        String city = "";
        String country = "";

        List<Entity> entities = payloadMessageFocus.getExtractedInfo().getEntities();
        for (Entity entity : entities) {

            if (entity.getType().equals("sys-date")) {
                TicketmasterDate ticketmasterDate = new TicketmasterDate();
                ticketmasterDate.setEntity(entity);
                extractedDates.add(ticketmasterDate);
            }
            if (entity.getType().equals("Country")) {
                country = entity.getText();
            }
            else if (entity.getType().equals("City")) {
                city = entity.getText();
            }
        }

        String dateQuery="";
        String startDateTime = "";
        String endDateTime = "";
        int counter = 1;
        for(TicketmasterDate extractedDate : extractedDates) {
            LOGGER.info("Result [{}]", extractedDate.getEntity().toString());

            if (counter==1) {
                startDateTime = String.format("%sT00:00:00Z", extractedDate.getEntity().getText());
                dateQuery = String.format(" from %s", extractedDate.getEntity().getText());
            }
            if (counter==2) {
                endDateTime = String.format("%sT00:00:00Z", extractedDate.getEntity().getText());
                dateQuery = String.format("%s to %s", dateQuery, extractedDate.getEntity().getText());
            }
            counter++;

        }


        if (!city.equals("") && !country.equals("")) {
            query = String.format("%s,%s", city, country);
        } else if (!country.equals("")) {
            query = country;
        } else {
            query = city;
        }


        LOGGER.info("query [{}]", query);

        Call<TicketmasterEventResponse> call;
        if (!query.equals("")) {
            LocationData locationData = googleService.getLocationData(query);

            String latLongQuery = Float.toString(locationData.getLat()) + "," + Float.toString(locationData.getLng());

            call = ticketMasterClient.searchByLatLongBetweenDate(ticketmasterProperties.getApiConsumerKey(), latLongQuery, startDateTime, endDateTime);

            String finalDateQuery = dateQuery;
            String finalQuery = query;
            call.enqueue(new Callback<TicketmasterEventResponse>() {
                @Override
                public void onResponse(Call<TicketmasterEventResponse> call, Response<TicketmasterEventResponse> response) {
                    LOGGER.info("Ticketmaster Request successful.");
                    LOGGER.info("Ticketmaster response=" + response);

                    AnnotationPayload payload = MessageUtils.mapAnnotationPayload(webhookEvent.getAnnotationPayload());

                    TicketmasterEventResponse eventResponse = response.body();

                    TicketmasterEventContainer eventContainer = eventResponse.get_embedded();

                    List<Button> eventButtons = new ArrayList<>();
                    for (TicketmasterEvent event : eventContainer.getEvents()) {
                        LOGGER.info(String.format("Name is %s", event.getName()));

                        Button eventButton = new Button();
                        eventButton.setId(event.getId());
                        eventButton.setStyle("PRIMARY");
                        String title = HtmlUtils.htmlUnescape(event.getName());
                        eventButton.setTitle(title.replaceAll("\"", Matcher.quoteReplacement("\\\"")));
                        eventButtons.add(eventButton);
                    }

                    Annotation annotation = new Annotation();

                    String title = String.format("We found these events in %s", finalQuery);
                    String dates = String.format("%s", finalDateQuery);
                    if (!dates.equals("")) {
                        title = String.format("%s%s.", title, dates);
                    }

                    annotation.setTitle(title);
                    annotation.setText("");
                    annotation.setButtons(eventButtons);

                    TargetedMessage targetedMessage = new TargetedMessage();
                    targetedMessage.setConversationId(webhookEvent.getSpaceId());
                    targetedMessage.setTargetDialogId(payload.getTargetDialogId());
                    targetedMessage.setTargetUserId(webhookEvent.getUserId());
                    targetedMessage.setAnnotation(annotation);

                    graphQLService.createTargetedMessage(webhookEvent, targetedMessage);

                }

                @Override
                public void onFailure(Call<TicketmasterEventResponse> call, Throwable t) {
                    LOGGER.error("Ticketmaster Request failed.", t);
                }

            });
        }

    }

    @Override
    public void getEvent(WebhookEvent webhookEvent) {
        AnnotationPayload payload = MessageUtils.mapAnnotationPayload(webhookEvent.getAnnotationPayload());

        Call<TicketmasterEventResponse> call = ticketMasterClient.getEvent(payload.getActionId(),ticketmasterProperties.getApiConsumerKey());

        call.enqueue(new Callback<TicketmasterEventResponse>() {
            @Override
            public void onResponse(Call<TicketmasterEventResponse> call, Response<TicketmasterEventResponse> response) {
                LOGGER.info("Ticketmaster Request successful.");
                TicketmasterEventResponse event = response.body();
                LOGGER.info("Response="+response);
                LOGGER.info(event.toString());
                String targetText = "";
                if (event.getName() != null) {
                    targetText += String.format("*Name*: %s \\n", HtmlUtils.htmlUnescape(event.getName()));
                }

                if (event.getDescription() != null) {
                    targetText += String.format("*Description*: %s \\n", HtmlUtils.htmlUnescape(event.getDescription()));
                }

                if (event.getUrl() != null) {
                    targetText += String.format("*Book*: %s \\n", HtmlUtils.htmlUnescape(event.getUrl()));
                }
                List<Button> breweryButtons = new ArrayList<>();
                Button shareButton = new Button();
                shareButton.setId("Share-" + payload.getActionId());
                shareButton.setStyle("PRIMARY");
                shareButton.setTitle("Share");
                breweryButtons.add(shareButton);

                Annotation annotation = new Annotation();
                annotation.setTitle(event.getName());
                annotation.setText(targetText.replaceAll("\"", Matcher.quoteReplacement("\\\"")));
                annotation.setButtons(breweryButtons);

                TargetedMessage targetedMessage = new TargetedMessage();
                targetedMessage.setConversationId(webhookEvent.getSpaceId());
                targetedMessage.setTargetDialogId(payload.getTargetDialogId());
                targetedMessage.setTargetUserId(webhookEvent.getUserId());
                targetedMessage.setAnnotation(annotation);

                graphQLService.createTargetedMessage(webhookEvent, targetedMessage);

            }

            @Override
            public void onFailure(Call<TicketmasterEventResponse> call, Throwable t) {
                LOGGER.error("Ticketmaster Request failed.", t);
            }

        });
    }

    @Override
    public void shareEvent(WebhookEvent webhookEvent) {
        AnnotationPayload payload = MessageUtils.mapAnnotationPayload(webhookEvent.getAnnotationPayload());

        Call<TicketmasterEventResponse> call = ticketMasterClient.getEvent(payload.getActionId().substring(6),ticketmasterProperties.getApiConsumerKey());

        call.enqueue(new Callback<TicketmasterEventResponse>() {
            @Override
            public void onResponse(Call<TicketmasterEventResponse> call, Response<TicketmasterEventResponse> response) {
                LOGGER.info("Ticketmaster Request successful.");
                TicketmasterEventResponse event = response.body();
                LOGGER.info("Response="+response);
                LOGGER.info(event.toString());
                String targetText = "";
                if (event.getName() != null) {
                    targetText += String.format("*Name*: %s \\n", HtmlUtils.htmlUnescape(event.getName()));
                }

                if (event.getDescription() != null) {
                    targetText += String.format("*Description*: %s \\n", HtmlUtils.htmlUnescape(event.getDescription()));
                }

                if (event.getUrl() != null) {
                    targetText += String.format("*Book*: %s \\n", HtmlUtils.htmlUnescape(event.getUrl()));
                }

                Annotation annotation = new Annotation();
                annotation.setTitle("");
                annotation.setText(targetText.replaceAll("\"", Matcher.quoteReplacement("\\\"")));

                TargetedMessage targetedMessage = new TargetedMessage();
                targetedMessage.setConversationId(webhookEvent.getSpaceId());
                targetedMessage.setAnnotation(annotation);

                graphQLService.createMessage(webhookEvent, targetedMessage);

                Annotation annotation1 = new Annotation();
                annotation1.setTitle("");
                annotation1.setText(String.format("Event '%s' shared in the space.", event.getName()));
                annotation1.setButtons(null);

                TargetedMessage targetedMessage1 = new TargetedMessage();
                targetedMessage1.setConversationId(webhookEvent.getSpaceId());
                targetedMessage1.setTargetDialogId(payload.getTargetDialogId());
                targetedMessage1.setTargetUserId(webhookEvent.getUserId());
                targetedMessage1.setAnnotation(annotation1);

                graphQLService.createTargetedMessage(webhookEvent, targetedMessage1);
            }

            @Override
            public void onFailure(Call<TicketmasterEventResponse> call, Throwable t) {
                LOGGER.error("Ticketmaster Request failed.", t);
            }
        });
    }
}
