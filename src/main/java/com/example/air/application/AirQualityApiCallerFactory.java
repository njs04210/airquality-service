package com.example.air.application;

import com.example.air.application.constant.Region;
import com.example.air.application.error.exception.SiteNotExistsException;
import com.example.air.infrastructure.api.AirQualityApiCaller;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AirQualityApiCallerFactory {

    private final Map<Region, AirQualityApiCaller> callerMap = new HashMap<>();

    public AirQualityApiCallerFactory(List<AirQualityApiCaller> airQualityApiCallers) {
        for (AirQualityApiCaller airQualityApiCaller : airQualityApiCallers) {
            callerMap.put(airQualityApiCaller.getRegion(), airQualityApiCaller);
        }
    }

    public AirQualityApiCaller getAirQualityApiCaller(Region region) {
        return Optional.of(callerMap.get(region))
                .orElseThrow(SiteNotExistsException::new);

    }
}
