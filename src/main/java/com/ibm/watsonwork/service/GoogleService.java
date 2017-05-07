package com.ibm.watsonwork.service;

import com.ibm.watsonwork.model.google.LocationData;

public interface GoogleService extends Service {

    LocationData getLocationData(String query);

}
