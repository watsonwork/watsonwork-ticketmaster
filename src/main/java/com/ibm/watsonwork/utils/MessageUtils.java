package com.ibm.watsonwork.utils;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watsonwork.model.graphql.AnnotationPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);


    public static AnnotationPayload mapAnnotationPayload(String annotationPayload){
        AnnotationPayload payload = null;
        try {
            payload = objectMapper.readValue(annotationPayload, AnnotationPayload.class);
        } catch (IOException e) {
            LOGGER.error("Failed to read the annotation", e);
        }
        return payload;
    }


}
