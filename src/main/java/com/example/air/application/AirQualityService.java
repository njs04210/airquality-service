package com.example.air.application;

import com.example.air.application.constant.Region;
import com.example.air.infrastructure.api.AirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final AirQualityApiCallerFactory airQualityApiCallerFactory;

    @Cacheable(value = "airQualityInfo", keyGenerator = "customKeyGenerator")
    public AirQualityInfo getAirQualityInfo(String regionName, String siteName) {

        AirQualityApiCaller airQualityApiCaller = airQualityApiCallerFactory.getAirQualityApiCaller(Region.of(regionName));
        AirQualityInfo airQualityInfo = airQualityApiCaller.getAirQuality();

        if (StringUtils.hasLength(siteName)) {
            return airQualityInfo.searchBySiteName(siteName);
        }

        return airQualityInfo;
    }
}
