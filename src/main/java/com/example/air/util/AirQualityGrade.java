package com.example.air.util;

public final class AirQualityGrade {

    private AirQualityGrade() {

    }

    public static String getPm25Grade(Integer amount) {
        if (amount >= 0 && amount <= 15)
            return "좋음";
        if (amount >= 16 && amount <= 35)
            return "보통";
        if (amount >= 36 && amount <= 75)
            return "나쁨";
        if (amount >= 76 && amount <= 500)
            return "매우나쁨";
        return "";
    }

    public static String getPm10Grade(Integer amount) {
        if (amount >= 0 && amount <= 30)
            return "좋음";
        if (amount >= 31 && amount <= 80)
            return "보통";
        if (amount >= 81 && amount <= 150)
            return "나쁨";
        if (amount >= 151 && amount <= 600)
            return "매우나쁨";
        return "";
    }

    public static String getO3Grade(Double amount) {
        if (amount >= 0 && amount <= 0.03)
            return "좋음";
        if (amount >= 0.031 && amount <= 0.09)
            return "보통";
        if (amount >= 0.091 && amount <= 0.15)
            return "나쁨";
        if (amount >= 0.151 && amount <= 0.6)
            return "매우나쁨";
        return "";
    }

    public static String getNo2Grade(Double amount) {
        if (amount >= 0 && amount <= 0.03)
            return "좋음";
        if (amount >= 0.031 && amount <= 0.06)
            return "보통";
        if (amount >= 0.061 && amount <= 0.2)
            return "나쁨";
        if (amount >= 0.201 && amount <= 2)
            return "매우나쁨";
        return "";
    }

    public static String getCoGrade(Double amount) {
        if (amount >= 0 && amount <= 2)
            return "좋음";
        if (amount >= 2.01 && amount <= 9)
            return "보통";
        if (amount >= 9.01 && amount <= 15)
            return "나쁨";
        if (amount >= 15.01 && amount <= 50)
            return "매우나쁨";
        return "";
    }

    public static String getSo2Grade(Double amount) {
        if (amount >= 0 && amount <= 0.02)
            return "좋음";
        if (amount >= 0.021 && amount <= 0.05)
            return "보통";
        if (amount >= 0.051 && amount <= 0.15)
            return "나쁨";
        if (amount >= 0.151 && amount <= 1)
            return "매우나쁨";
        return "";
    }
}
