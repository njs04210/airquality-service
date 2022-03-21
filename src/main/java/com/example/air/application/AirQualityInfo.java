package com.example.air.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
public class AirQualityInfo {
    private Meta meta;
    private Result result;

    @Getter
    @Builder
    public static class Meta {
        private Integer totalCount;

        public void updateTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }
    }

    @Getter
    @Builder
    public static class Result {
        private Region region;
        private List<Site> sites;

        public void updateSites(List<Site> sites) {
            this.sites = sites;
        }
    }

    @Getter
    @Builder
    public static class Region {
        private String regionName;
        private ZonedDateTime measureDatetime;
        private Integer avgPm10;
        private String avgPm10Grade;
    }

    @Getter
    @Builder
    public static class Site {
        @JsonProperty("siteName")
        private String siteName;
        @JsonProperty("measureDatetime")
        private ZonedDateTime measureDatetime;
        @JsonProperty("pm25")
        private Integer pm25;
        @JsonProperty("pm25Grade")
        private String pm25Grade;
        @JsonProperty("pm10")
        private Integer pm10;
        @JsonProperty("pm10Grade")
        private String pm10Grade;
        @JsonProperty("o3")
        private Double o3;
        @JsonProperty("o3Grade")
        private String o3Grade;
        @JsonProperty("no2")
        private Double no2;
        @JsonProperty("no2Grade")
        private String no2Grade;
        @JsonProperty("co")
        private Double co;
        @JsonProperty("coGrade")
        private String coGrade;
        @JsonProperty("so2")
        private Double so2;
        @JsonProperty("so2Grade")
        private String so2Grade;
    }
}
