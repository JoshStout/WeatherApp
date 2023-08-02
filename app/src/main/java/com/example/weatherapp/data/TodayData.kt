package com.example.weatherapp.data

import com.squareup.moshi.Json

data class IconData(
    @Json(name = "icon") val iconName: String,
    @Json(name = "description") val description: String,
)

data class TodayData(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherData: List<IconData>,
    @Json(name = "main") val conditions: CurrentConditionsData,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temp: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
)
