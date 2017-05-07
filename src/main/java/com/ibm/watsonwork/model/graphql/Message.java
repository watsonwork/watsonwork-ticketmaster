package com.ibm.watsonwork.model.graphql;

import java.util.List;

import lombok.Data;

@Data
public class Message {

    private String id;
    private String content;
    private String type;
    private double version;
    private List<String> annotations;

}
