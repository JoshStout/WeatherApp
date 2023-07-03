package com.example.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            var obj = getFirstForecastObj()
            CurrentConditionScreen(navController, obj)
        }
        composable(
            route = Screen.Forecast.route,
//            arguments = listOf(
//                navArgument("id"){
//                    type = NavType.IntType
//                }
//            )
        ){
            //val id = it.arguments?.getInt("id")
            ForecastScreen(navController)
        }
    }
}