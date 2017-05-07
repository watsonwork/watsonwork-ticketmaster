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

package com.ibm.watsonwork.model.ticketmaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TicketmasterEventResponse {

    @JsonProperty("_embedded")
    private TicketmasterEventContainer _embedded;

    @JsonProperty("page")
    private TicketmasterEventPaging page;

    private String location;
    private String startDateTime;
    private String endDateTime;
    private String name;
    private String description;
    private String url;
}