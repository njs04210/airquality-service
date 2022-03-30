package com.example.air.interfaces.api;

import com.example.air.application.AirQualityService;
import com.example.air.application.AirQualityInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AirQualityApiController {
    private final AirQualityService airQualityService;

    @GetMapping("/vi/api/air-quality/{regionName}")
    public AirQualityInfo getAirQualityInfo(@PathVariable String regionName,
                                            @RequestParam(required = false) String siteName) {

        return airQualityService.getAirQualityInfo(regionName, siteName);
    }
}
