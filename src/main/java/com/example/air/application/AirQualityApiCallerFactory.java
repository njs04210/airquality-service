package com.example.air.application;

import com.example.air.application.error.exception.RegionNotExistsException;
import com.example.air.infrastructure.api.AirQualityApiCaller;
import com.example.air.infrastructure.api.busan.BusanAirQualityApiCaller;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirQualityApiCallerFactory {

    private final BusanAirQualityApiCaller busanAirQualityApiCaller;
    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityApiCaller getAirQualityApiCaller(String regionName) {

        if (regionName.equals("seoul")) {
            return seoulAirQualityApiCaller;
        }

        if (regionName.equals("busan")) {
            return busanAirQualityApiCaller;
        }

        throw new RegionNotExistsException();
    }
}
