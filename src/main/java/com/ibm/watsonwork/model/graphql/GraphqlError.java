package com.ibm.watsonwork.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "field"
})
@Data
public class GraphqlError {

    @JsonProperty("message")
    private String message;
    @JsonProperty("field")
    private GraphQLField field;
}