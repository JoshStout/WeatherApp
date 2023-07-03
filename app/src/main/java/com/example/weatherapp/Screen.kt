package com.example.weatherapp

sealed class Screen(val route: String){
    object Today: Screen(route = "today")
//    object Forecast: Screen(route = "dayforecast/{id}"){
//        fun passNameDescription(id: String): String {
//            return "dayforecast/$id"
//        }
//    }
    object Forecast: Screen(route = "dayforecast")
}