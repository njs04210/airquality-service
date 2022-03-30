package com.example.air.infrastructure.api;

import com.example.air.application.AirQualityInfo;
import com.example.air.application.constant.Region;

public interface AirQualityApiCaller {

    Region getRegion();

    AirQualityInfo getAirQuality();

}
