package com.example.air.interfaces.api;

import com.example.air.application.AirQualityService;
import com.example.air.application.AirQualityInfo;
import com.example.air.application.constant.Region;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/v1/api/air-quality")
@RestController
@RequiredArgsConstructor
public class AirQualityApiController {
    private final AirQualityService airQualityService;

    @GetMapping("/{regionName}")
    public AirQualityInfo getAirQualityInfo(@PathVariable Region regionName,
                                            @RequestParam(required = false) String siteName) {

        return airQualityService.getAirQualityInfo(regionName, siteName);

    }
}
