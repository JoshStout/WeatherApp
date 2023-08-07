package com.example.weatherapp

sealed class Screen(val route: String){
    object Today: Screen(route = "today")
    object Forecast: Screen(route = "dayforecast/{id}"){
        fun passID(id: String): String{
            return "dayforecast/$id"
        }
    }
}