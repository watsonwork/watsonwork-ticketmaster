package com.ibm.watsonwork.model.graphql;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Keyword {

    @JsonProperty("text")
    private String text;

    @JsonProperty("relevance")
    private String relevance;

}
