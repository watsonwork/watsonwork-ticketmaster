package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.ibm.watsonwork.GoogleProperties;
import com.ibm.watsonwork.client.GoogleClient;
import com.ibm.watsonwork.model.google.GeoCodeResponse;
import com.ibm.watsonwork.model.google.GeoCodeResult;
import com.ibm.watsonwork.model.google.LocationData;
import com.ibm.watsonwork.service.GoogleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class DefaultGoogleService implements GoogleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGoogleService.class);

    @Autowired
    private GoogleProperties googleProperties;

    @Autowired
    private GoogleClient googleClient;

    @Override
    public LocationData getLocationData(String query) {
        LOGGER.info("query [{}]", query);

        Call<GeoCodeResponse> call = googleClient.getGeoData(query,googleProperties.getApiKey());

        List<GeoCodeResult> results = Collections.emptyList();
        try {
            GeoCodeResponse geoCodeResponse = call.execute().body();
            LOGGER.info("Status [{}]", geoCodeResponse.getStatus());
            LOGGER.info("Results [{}]", geoCodeResponse.getResults().toString());
            results = geoCodeResponse.getResults();

        } catch (IOException e ) {
            LOGGER.error("Found no geo location data",e);
        }

        LocationData locationData = new LocationData();
        for(GeoCodeResult result : results) {
            LOGGER.info("Result [{}]", result);
            locationData.setLat(result.getGeometry().getLocation().getLat());
            locationData.setLng(result.getGeometry().getLocation().getLng());
        }
        return locationData;
    }


}
