package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController


// added to build.gradle file in the dependencies block:
// def nav_version = "2.6.0"
// implementation "androidx.navigation:navigation-compose:$nav_version"
// implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SetupNavGraph(navController = navController)
        }
    }
}