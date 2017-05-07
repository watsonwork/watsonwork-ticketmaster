package com.ibm.watsonwork.controller;

import com.ibm.watsonwork.WatsonWorkProperties;
import com.ibm.watsonwork.model.graphql.AnnotationPayload;
import com.ibm.watsonwork.model.graphql.Message;
import com.ibm.watsonwork.model.graphql.WebhookEvent;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import com.ibm.watsonwork.service.TicketmasterService;
import com.ibm.watsonwork.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.ibm.watsonwork.MessageTypes.ACTION_SELECTED;
import static com.ibm.watsonwork.MessageTypes.MESSAGE_ANNOTATION_ADDED;
import static com.ibm.watsonwork.MessageTypes.VERIFICATION;
import static com.ibm.watsonwork.WatsonWorkConstants.X_OUTBOUND_TOKEN;

@RestController
public class WatsonWorkController {

    @Autowired
    private WatsonWorkProperties watsonWorkProperties;

    @Autowired
    private TicketmasterService ticketmasterService;

    @Autowired
    private GraphQLService graphQLService;

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WatsonWorkController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity home(){
        String responseBody = "Ticketmaster action fulfillment app.";
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    @RequestMapping(value = "webhook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity webhookCallback(@RequestHeader(X_OUTBOUND_TOKEN) String outboundToken, @RequestBody WebhookEvent webhookEvent){

        // Verify the challenge to the webhook
        if(VERIFICATION.equalsIgnoreCase(webhookEvent.getType()) && authService.isValidVerificationRequest(webhookEvent, outboundToken)) {
            LOGGER.info("Verify");
            return buildVerificationResponse(webhookEvent);
        }

        // Process the message annotation added for actionSelected
        if(!watsonWorkProperties.getAppId().equals(webhookEvent.getUserId())
                && MESSAGE_ANNOTATION_ADDED.equalsIgnoreCase(webhookEvent.getType())
                && ACTION_SELECTED.equalsIgnoreCase(webhookEvent.getAnnotationType())) {

            LOGGER.info("Action Selected annotation received");

            AnnotationPayload payload = MessageUtils.mapAnnotationPayload(webhookEvent.getAnnotationPayload());

            // Make a graphql getMessage call to get the details of the message that the user clicked on.
            Message message = graphQLService.getMessage(payload.getReferralMessageId());

            LOGGER.info("Message id [{}]", webhookEvent.getMessageId());

            if (message.getId()!=null) {

                LOGGER.info("Returned  [{}]", message.getId());

                AnnotationPayload payloadMessageFocus = new AnnotationPayload();
                for(String annotation : message.getAnnotations()) {
                    LOGGER.info("Result [{}]", annotation);
                    if (annotation.contains("message-focus")) {
                        payloadMessageFocus = MessageUtils.mapAnnotationPayload(annotation);
                    }
                }

                if (payload.getTargetDialogId().equals(payload.getActionId())) {
                    // Make a brewerydb call and return a targetedResponse to respond as a targeted Message to the user.
                    ticketmasterService.findEvents(webhookEvent,payloadMessageFocus);
                } else {
                    if (payload.getActionId().contains("Share")) {
                        ticketmasterService.shareEvent(webhookEvent);
                    } else {
                        ticketmasterService.getEvent(webhookEvent);
                    }
                }
            }

        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private ResponseEntity buildVerificationResponse(WebhookEvent webhookEvent) {
        String responseBody = String.format("{\"response\": \"%s\"}", webhookEvent.getChallenge());

        String verificationHeader = authService.createVerificationHeader(responseBody);
        LOGGER.info("Building verification response [{}]",responseBody);
        return ResponseEntity.status(HttpStatus.OK)
                .header(X_OUTBOUND_TOKEN, verificationHeader)
                .body(responseBody);
    }

}
