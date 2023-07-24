package com.example.weatherapp.data

import com.squareup.moshi.Json

data class DailyForecastData(
    @Json(name = "list") val listOfForecasts: List<DailyForecastItem>
)

data class DailyForecastItem(
    @Json(name = "weather") val weatherData: List<IconData>,
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: Temp,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "humidity") val humidity: Int,
)

data class Temp(
    @Json(name = "day") val day: Float,
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)