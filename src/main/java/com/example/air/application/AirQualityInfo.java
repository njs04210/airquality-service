package com.example.air.application;

import com.example.air.application.constant.AirQualityGrade;
import com.example.air.application.error.exception.SiteNotExistsException;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class AirQualityInfo {
    private Meta meta;
    private Result result;

    public AirQualityInfo searchBySiteName(String siteName) {
        Site searchedSite = result.searchSite(siteName);
        List<Site> sites = Collections.singletonList(searchedSite);
        this.result.sites = sites;
        this.meta.totalCount = sites.size();
        return this;
    }

    @Getter
    @Builder
    public static class Meta {
        private Integer totalCount;

    }

    @Getter
    @Builder
    public static class Result {
        private Region region;
        private List<Site> sites;

        private Site searchSite(String siteName) {
            return sites.stream()
                    .filter(site -> site.getSiteName().equals(siteName))
                    .findFirst()
                    .orElseThrow(() -> new SiteNotExistsException(siteName));
        }
    }

    @Getter
    @Builder
    public static class Region {
        private String regionName;
        private ZonedDateTime measureDatetime;
        private Integer avgPm10;
        private AirQualityGrade avgPm10Grade;
    }

    @Getter
    @Builder
    public static class Site {
        private String siteName;
        private ZonedDateTime measureDatetime;
        private Integer pm25;
        private AirQualityGrade pm25Grade;
        private Integer pm10;
        private AirQualityGrade pm10Grade;
        private Double o3;
        private AirQualityGrade o3Grade;
        private Double no2;
        private AirQualityGrade no2Grade;
        private Double co;
        private AirQualityGrade coGrade;
        private Double so2;
        private AirQualityGrade so2Grade;
    }

}
