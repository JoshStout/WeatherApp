package com.example.weatherapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.Screen
import com.example.weatherapp.data.DailyForecastItem
import com.example.weatherapp.viewModels.DailyForecastViewModel
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ForecastScreen(
    navController: NavController
){
    Column(
        modifier = Modifier.clickable{
            navController.navigate(Screen.Today.route)
        }
    ) {
        TopForecastBar()
        DailyForecastRows()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopForecastBar(){
    TopAppBar(
        title = {Text(text = "Forecast")},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
    )
}

@Composable
fun DailyForecastRows(
    viewModel: DailyForecastViewModel = hiltViewModel()
){
    val forecastData = viewModel.dailyForecastConditionsData.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }

    LazyColumn(){
        items(items = forecastData.value?.listOfForecasts ?: listOf()){
            ForecastRows(forecastDay = it)
        }
    }
}

@Composable
fun ForecastRows(forecastDay: DailyForecastItem){

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(bottom = 15.dp)
            )
            Text(
                text = "${getMonthDay(forecastDay.date.toString())}",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 9.dp)
            )
            Spacer(modifier = Modifier.width(25.dp))
            Row(){
                Column(){
                    Text(text = "Temp: ${forecastDay.temp.day.toInt()}°")
                    Text(text = "High: ${forecastDay.temp.max.toInt()}°")
                }
                Column() {
                    Text(text = "")
                    Text(text = "Low: ${forecastDay.temp.min.toInt()}°")
                }
                Spacer(modifier = Modifier.width(25.dp))
                Column(){
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "Sunrise: ${getTime(forecastDay.sunrise.toString())}"
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "Sunset: ${getTime(forecastDay.sunset.toString())}"
                    )
                }
            }
        }
    }
}

// convert UTC timestamp to datetime in Kotlin
private fun getMonthDay(s: String): String? {
    try {
        val sdf = SimpleDateFormat("MMM d")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}

// convert UTC timestamp to datetime in Kotlin
private fun getTime(s: String): String? {
    try {
        val sdf = SimpleDateFormat("h:mm a")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}