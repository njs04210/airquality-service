package com.example.air.interfaces.api;

import com.example.air.application.AirQualityService;
import com.example.air.application.AirQualityInfo;

import com.example.air.application.constant.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AirQualityApiController {
    private final AirQualityService airQualityService;

    @GetMapping("/")
    public String main(Model model) {
        List<Region> regions = Arrays.asList(Region.values());
        model.addAttribute("regions", regions);
        return "main";
    }

    @ResponseBody
    @GetMapping("/v1/api/air-quality/{regionName}")
    public AirQualityInfo getAirQualityInfo(@PathVariable String regionName,
                                            @RequestParam(required = false) String siteName) {

        return airQualityService.getAirQualityInfo(Region.of(regionName), siteName);
    }
}
