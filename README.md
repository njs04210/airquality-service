# 전국 대기질 정보 서비스 

선택한 행정구역(시/도)로 해당 행정구역의 평균 미세먼지 현황과 전체 자치구(동)의 기타 대기질 정보를 확인할 수 있습니다. 자치구(동)을 선택해 일부만을 조회할 수도 있습니다.

** 현재 서울, 부산만 지원
<img width="612" alt="스크린샷 2022-06-10 16 19 50" src="https://user-images.githubusercontent.com/68816492/173021684-f784ebd7-7e52-4863-9fed-905cbc682d99.png">

---

## Request

### URL

```jsx
GET /v1/api/air-quality HTTP/1.1
Host: localhost
```

### Parameter

| Name | Type | Description | Required |
| --- | --- | --- | --- |
| regionName | String | 시/도 (영문) *seoul : 서울시 *busan : 부산시 | O |
| siteName | String | 자치구(동) (한글) ex) 성북구, 노원구, 구로구, ... | X |

---

## Response

**meta**

| Name | Type | Description |
| --- | --- | --- |
| totalCount | Integer | 검색된 자치구(동) 수 |

**result**

| Name | Type | Description |
| --- | --- | --- |
| region | Region | 선택된 시/도 정보, 아래 region 참고 |
| sites | Sites | 선택된 자치구(동) 정보, 아래 sites 참고 |

**region**

| Name | Type | Description |
| --- | --- | --- |
| regionName | String | 시/도 |
| measureDatetime | String | 측정시간, ISO-8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm 형태로 돌아옵니다.
ex) 2022-03-11T21:00:00+09:00 |
| avgPm10 | Integer | 시/도 평균 미세먼지 농도(㎍/㎥) |
| avgPm10Grade | String | 시/도 평균 미세먼지 등급 |

**sites**

| Name | Type | Description |
| --- | --- | --- |
| siteName | String | 자치구(동) |
| measureDatetime | String | 측정시간, ISO-8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm 형태로 돌아옵니다.
ex) 2022-03-11T21:00:00+09:00 |
| pm25 | Integer | 초미세먼지 농도(㎍/㎥) |
| pm25Grade | String | 초미세먼지 등급 |
| pm10 | Integer | 미세먼지 농도(㎍/㎥) |
| pm10Grade | String | 미세먼지 등급 |
| o3 | Double | 오존 농도(ppm) |
| o3Grade | String | 오존 등급 |
| no2 | Double | 이산화질소 농도(ppm) |
| no2Grade | String | 이산화질소 등급 |
| co | Double | 일산화탄소 농도(ppm) |
| coGrade | String | 일산화탄소 등급 |
| so2 | Double | 아황산가스 농도(ppm) |
| so2Grade | String | 아황산가스 등급 |

---

## Sample

### **Request: 서울시 성북구 대기질 정보 조회**

```bash
curl -v -X GET “http://localhost/v1/api/air-quality/seoul”
--data-urlencode "siteName=성북구"
```

### Response

```json
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "meta": {
    "totalCount": 1
  },
  "result": {
    "region": {
      "regionName": "서울시",
      "measureDatetime": "2022-03-11T21:00:00+09:00",
      "avgPm10": 6,
      "avgPm10Grade": "좋음"
    },
    "sites": [
      {
        "siteName": "성북구",
        "measureDatetime": "2022-03-11T21:00:00+09:00",
        "pm25": 5,
        "pm25Grade": "좋음",
        "pm10": 3,
        "pm10Grade": "좋음",
        "o3": 0.028,
        "o3Grade": "좋음",
        "no2": 0.014,
        "no2Grade": "좋음",
        "co": 0.5,
        "coGrade": "좋음",
        "so2": 0.003,
        "so2Grade": "좋음"
    }
  ]
}
```