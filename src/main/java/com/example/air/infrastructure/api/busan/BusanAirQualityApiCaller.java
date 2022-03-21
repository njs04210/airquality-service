package com.example.air.infrastructure.api.busan;

import com.example.air.application.AirQualityInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.air.util.AirQualityGrade.*;
import static com.example.air.util.AirQualityGrade.getSo2Grade;

@Slf4j
@Component
public class BusanAirQualityApiCaller {
    private final BusanAirQualityApi busanAirQualityApi;

    public BusanAirQualityApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanAirQualityApi.class);
    }

    public AirQualityInfo getAirQuality() {
        try {
            val call = busanAirQualityApi.getAirQuality();
            val response = call.execute().body();

            if (response == null || response.getResponse() == null) {
                throw new RuntimeException("[busan] getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResponse().isSuccess()) {
                log.info(response.toString());
                return convert(response);
            }

            throw new RuntimeException("[busan] getAirQuality 응답이 올바르지 않습니다. header=" + response.getResponse().getHeader());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("[busan] getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    private AirQualityInfo convert(BusanAirQualityApiDto.GetAirQualityResponse response) {

        List<BusanAirQualityApiDto.Item> items = response.getResponse().getItems();

        // sites
        List<AirQualityInfo.Site> sites = convert(items);


        // meta
        AirQualityInfo.Meta meta = AirQualityInfo.Meta.builder()
                .totalCount(sites.size())
                .build();

        // region
        Integer avgPm10 = averagePm10(items);
        String avgPm10Grade = getPm10Grade(avgPm10);
        AirQualityInfo.Region region = AirQualityInfo.Region.builder()
                .regionName("부산시")
                .measureDatetime(changeFormat(items.get(0).getMeasurementTime()))
                .avgPm10(avgPm10)
                .avgPm10Grade(avgPm10Grade)
                .build();

        // result
        AirQualityInfo.Result result = AirQualityInfo.Result.builder()
                .region(region)
                .sites(sites)
                .build();

        // Json
        return AirQualityInfo.builder()
                .meta(meta)
                .result(result)
                .build();
    }

    private List<AirQualityInfo.Site> convert(List<BusanAirQualityApiDto.Item> items) {

        List<AirQualityInfo.Site> sites = new ArrayList<>();

        for (BusanAirQualityApiDto.Item item : items) {
            AirQualityInfo.Site site = AirQualityInfo.Site.builder()
                    .siteName(item.getSite())
                    .measureDatetime(changeFormat(item.getMeasurementTime()))
                    .pm25(item.getPm25())
                    .pm25Grade(getPm25Grade(item.getPm25()))
                    .pm10(item.getPm10())
                    .pm10Grade(getPm10Grade(item.getPm10()))
                    .o3(item.getO3())
                    .o3Grade(getO3Grade(item.getO3()))
                    .no2(item.getNo2())
                    .no2Grade(getNo2Grade(item.getNo2()))
                    .co(item.getCo())
                    .coGrade(getCoGrade(item.getCo()))
                    .so2(item.getSo2())
                    .so2Grade(getSo2Grade(item.getSo2()))
                    .build();

            sites.add(site);
        }
        return sites;
    }

    private ZonedDateTime changeFormat(String dateTime) {
        LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyyMMddHH"));
        return date.atZone(ZoneId.systemDefault());
    }

    private Integer averagePm10(List<BusanAirQualityApiDto.Item> items) {
        Integer totalPm10 = 0;

        for (BusanAirQualityApiDto.Item item : items) {
            totalPm10 += item.getPm10();
        }

        return totalPm10 / items.size();
    }

}
