package com.ibm.watsonwork.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "type"
})
@Data
public class GraphQLField {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;


}

