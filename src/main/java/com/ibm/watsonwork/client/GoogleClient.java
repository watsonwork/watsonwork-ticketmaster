package com.ibm.watsonwork.client;

import com.ibm.watsonwork.model.google.GeoCodeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface GoogleClient {

    String GOOGLE_GEOCODE_PATH = "/maps/api/geocode/json";
    String HTTP_ACCEPT_JSON="HTTP-ACCEPT: application/json";

    @Headers({HTTP_ACCEPT_JSON})
    @GET(GOOGLE_GEOCODE_PATH)
    Call<GeoCodeResponse> getGeoData(@Query("address") String spaceId, @Query("key") String key);

}
