package com.example.air.application.constant;

import com.example.air.application.error.exception.RegionNotExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    seoul("서울시"),
    busan("부산시");

    private final String description;

    public static Region of(String regionName) {
        Region region;
        try {
            region = Region.valueOf(regionName);
        } catch (IllegalArgumentException e) {
            throw new RegionNotExistsException(regionName);
        }
        return region;
    }
}
