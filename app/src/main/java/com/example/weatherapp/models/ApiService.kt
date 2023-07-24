package com.example.weatherapp.models

import com.example.weatherapp.data.DailyForecastData
import com.example.weatherapp.data.TodayData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentData (
        @Query("zip") zip: String = "55106",
        @Query("appid") appID: String = "2712b017519c4b8ee510f6bb4396943c",
        @Query("units") units: String = "imperial",
    ) : TodayData

    @GET("data/2.5/forecast/daily")
    suspend fun getDailyForecastData (
        @Query("zip") zip: String = "55106,us",
        @Query("appid") appID: String = "2712b017519c4b8ee510f6bb4396943c",
        @Query("units") units: String = "imperial",
        @Query("cnt") count: Int = 16,
    ) : DailyForecastData

}