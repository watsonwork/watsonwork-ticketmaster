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

package com.ibm.watsonwork.client;

import com.ibm.watsonwork.model.ticketmaster.TicketmasterEventResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface TicketMasterClient {

    String SEARCH_PATH = "/discovery/v2/events.json";
    String HTTP_ACCEPT_JSON="HTTP-ACCEPT: application/json";

    @Headers({HTTP_ACCEPT_JSON})
    @GET(SEARCH_PATH)
    Call<TicketmasterEventResponse> searchByLatLongBetweenDate(@Query("apikey") String key, @Query("latlong") String query,@Query("startDateTime") String startDateTime,@Query("endDateTime") String endDateTime);

    @Headers({HTTP_ACCEPT_JSON})
    @GET("/discovery/v2/events/{id}.json")
    Call<TicketmasterEventResponse> getEvent(@Path("id") String id,@Query("apikey") String key);


}
