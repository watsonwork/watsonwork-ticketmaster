package com.ibm.watsonwork.model.google;

import lombok.Data;
import java.util.List;

@Data
public class GeoCodeResponse {
    private List<GeoCodeResult> results;
    private String status;
}
