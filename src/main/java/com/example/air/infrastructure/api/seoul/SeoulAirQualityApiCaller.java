package com.example.air.infrastructure.api.seoul;

import com.example.air.application.AirQualityInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
public class SeoulAirQualityApiCaller {
    private final SeoulAirQualityApi seoulAirQualityApi;

    public SeoulAirQualityApiCaller(@Value("${api.seoul.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.seoulAirQualityApi = retrofit.create(SeoulAirQualityApi.class);
    }

    public AirQualityInfo getAirQuality() {
        try {
            var call = seoulAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResponse() == null) {
                throw new RuntimeException("[seoul] getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResponse().isSuccess()) {
                log.info(response.toString());
                return convert(response);
            }

            throw new RuntimeException("[seoul] getAirQuality 응답이 올바르지 않습니다. header=" + response.getResponse().getResult());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("[seoul] getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    private AirQualityInfo convert(SeoulAirQualityApiDto.GetAirQualityResponse response) {

        List<SeoulAirQualityApiDto.Row> rows = response.getResponse().getRows();

        // sites
        List<AirQualityInfo.Site> sites = convert(rows);


        // meta
        AirQualityInfo.Meta meta = AirQualityInfo.Meta.builder()
                .totalCount(sites.size())
                .build();

        // region
        Integer avgPm10 = averagePm10(rows);
        String avgPm10Grade = getPm10Grade(avgPm10);
        AirQualityInfo.Region region = AirQualityInfo.Region.builder()
                .regionName("서울시")
                .measureDatetime(changeFormat(rows.get(0).getMeasurementTime()))
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

    private List<AirQualityInfo.Site> convert(List<SeoulAirQualityApiDto.Row> rows) {

        List<AirQualityInfo.Site> sites = new ArrayList<>();

        for (SeoulAirQualityApiDto.Row row : rows) {
            AirQualityInfo.Site site = AirQualityInfo.Site.builder()
                    .siteName(row.getSite())
                    .measureDatetime(changeFormat(row.getMeasurementTime()))
                    .pm25(row.getPm25())
                    .pm25Grade(getPm25Grade(row.getPm25()))
                    .pm10(row.getPm10())
                    .pm10Grade(getPm10Grade(row.getPm10()))
                    .o3(row.getO3())
                    .o3Grade(getO3Grade(row.getO3()))
                    .no2(row.getNo2())
                    .no2Grade(getNo2Grade(row.getNo2()))
                    .co(row.getCo())
                    .coGrade(getCoGrade(row.getCo()))
                    .so2(row.getSo2())
                    .so2Grade(getSo2Grade(row.getSo2()))
                    .build();

            sites.add(site);
        }
        return sites;
    }

    private ZonedDateTime changeFormat(String dateTime) {
        LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return date.atZone(ZoneId.systemDefault());
    }

    private Integer averagePm10(List<SeoulAirQualityApiDto.Row> rows) {
        Integer totalPm10 = 0;

        for (SeoulAirQualityApiDto.Row row : rows) {
            totalPm10 += row.getPm10();
        }

        return totalPm10 / rows.size();
    }
}
