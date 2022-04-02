package com.example.air.application.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_REGION_NAME_ERROR("존재하지 않는 시(도)명입니다."),
    NO_SITE_NAME_ERROR("요청하신 자치구 정보가 없습니다."),
    NO_OPENAPI_SERVICE_ERROR("요청하신 시(도)의 Open API가 연동되지 않은 상태입니다."),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String description;

    ErrorCode(final String description) {
        this.description = description;
    }
}
