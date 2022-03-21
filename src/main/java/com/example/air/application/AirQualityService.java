package com.example.air.application;

import com.example.air.infrastructure.api.busan.BusanAirQualityApiCaller;
import com.example.air.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;

    public AirQualityInfo getAirQualityInfo(String regionName, String siteName) {

        if (regionName.equals("seoul")) {
            AirQualityInfo airQualityInfo = seoulAirQualityApiCaller.getAirQuality();

            if (StringUtils.hasLength(siteName)) {
                List<AirQualityInfo.Site> filteredSites = filterBySiteName(airQualityInfo.getResult().getSites(), siteName);
                airQualityInfo.getMeta().updateTotalCount(filteredSites.size());
                airQualityInfo.getResult().updateSites(filteredSites);
            }
            return airQualityInfo;
        }

        if (regionName.equals("busan")) {
            AirQualityInfo airQualityInfo = busanAirQualityApiCaller.getAirQuality();

            if (StringUtils.hasLength(siteName)) {
                List<AirQualityInfo.Site> sites = filterBySiteName(airQualityInfo.getResult().getSites(), siteName);
                airQualityInfo.getMeta().updateTotalCount(sites.size());
                airQualityInfo.getResult().updateSites(sites);
            }
            return airQualityInfo;
        }

        throw new RuntimeException("요청한 시(도) 정보가 없습니다.");
    }

    private List<AirQualityInfo.Site> filterBySiteName(List<AirQualityInfo.Site> sites, String siteName) {

        return sites.stream()
                .filter(s -> s.getSiteName().equals(siteName))
                .collect(Collectors.toList());
    }
}
