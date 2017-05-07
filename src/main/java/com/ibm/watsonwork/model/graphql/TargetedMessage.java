package com.ibm.watsonwork.model.graphql;

import java.util.List;

import lombok.Data;

@Data
public class TargetedMessage {

    private String conversationId;
    private String targetDialogId;
    private String targetUserId;
    private List<Annotation> annotations;
    private Annotation annotation;

}

