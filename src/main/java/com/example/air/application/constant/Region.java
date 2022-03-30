package com.example.air.application.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    seoul("서울시"),
    busan("부산시");

    private final String description;
}
