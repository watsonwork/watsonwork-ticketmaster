package com.ibm.watsonwork.model.graphql;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Entity {

    private String text;

    private String type;

    private String source;

    private String count;

    private String relevance;

    private ArrayList location;

}
