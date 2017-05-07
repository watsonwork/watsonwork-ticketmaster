package com.ibm.watsonwork.model.graphql;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GraphQLResponse {

    @JsonProperty("data")
    private GraphQLData data;

    @JsonProperty("errors")
    private List<GraphqlError> errors = null;
}