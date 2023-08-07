package com.example.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.views.CurrentConditionScreen
import com.example.weatherapp.views.ForecastScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Today.route
    ){
        composable(
            route = Screen.Today.route
        ){
            CurrentConditionScreen(navController)
        }
        composable(
            route = Screen.Forecast.route
        ){
            ForecastScreen(navController)
        }
    }
}