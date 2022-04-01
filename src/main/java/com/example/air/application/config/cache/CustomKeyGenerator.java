package com.example.air.application.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Calendar;

public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_") + "_"
                + getDateAndHour();
    }

    private String getDateAndHour() {
        Calendar today = Calendar.getInstance();
        return String.valueOf(today.get(Calendar.YEAR)) + (today.get(Calendar.MONTH) + 1) +
                today.get(Calendar.DAY_OF_MONTH) + today.get(Calendar.HOUR_OF_DAY);
    }

}
