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

import com.ibm.watsonwork.model.graphql.Entity;
import lombok.Data;

@Data
public class TicketmasterDate {
    private Entity entity;
    private String dateString;
    private String sentenceText;
}