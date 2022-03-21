package com.example.air.application;

import com.example.air.infrastructure.api.busan.BusanAirQualityApiCaller;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;

    public AirQualityInfo getAirQualityInfo(String regionName, String siteName) {

        if (regionName.equals("seoul")) {
            AirQualityInfo airQualityInfo = seoulAirQualityApiCaller.getAirQuality();

            if (StringUtils.hasLength(siteName)) {
                return airQualityInfo.searchBySiteName(siteName);
            }
            return airQualityInfo;
        }

        if (regionName.equals("busan")) {
            AirQualityInfo airQualityInfo = busanAirQualityApiCaller.getAirQuality();

            if (StringUtils.hasLength(siteName)) {
                return airQualityInfo.searchBySiteName(siteName);
            }
            return airQualityInfo;
        }

        throw new RuntimeException("요청한 시(도) 정보가 없습니다.");
    }
}
