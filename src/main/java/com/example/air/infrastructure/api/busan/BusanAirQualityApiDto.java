package com.example.air.infrastructure.api.busan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

public class BusanAirQualityApiDto {
    @Getter
    @Setter
    @ToString
    public static class GetAirQualityResponse {
        @JsonProperty("getAirQualityInfoClassifiedByStation")
        private Response response;
    }

    @Getter
    @Setter
    @ToString
    public static class Response {
        @JsonProperty("header")
        private Header header;
        @JsonProperty("item")
        private List<Item> items;

        public boolean isSuccess() {
            return Objects.equals(header.getCode(), "00");
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Header {
        @JsonProperty("code")
        private String code;
        @JsonProperty("message")
        private String message;
    }

    @Getter
    @Setter
    @ToString
    public static class Item {
        @JsonProperty("site")
        private String site;
        @JsonProperty("controlnumber")
        private String measurementTime;
        @JsonProperty("so2")
        private Double so2;
        @JsonProperty("so2Cai")
        private String so2Grade;
        @JsonProperty("no2")
        private Double no2;
        @JsonProperty("no2Cai")
        private String no2Grade;
        @JsonProperty("o3")
        private Double o3;
        @JsonProperty("o3Cai")
        private String o3Grade;
        @JsonProperty("co")
        private Double co;
        @JsonProperty("coCai")
        private String coGrade;
        @JsonProperty("pm25")
        private Integer pm25;
        @JsonProperty("pm25Cai")
        private String pm25Grade;
        @JsonProperty("pm10")
        private Integer pm10;
        @JsonProperty("pm10Cai")
        private String pm10Grade;
    }
}
