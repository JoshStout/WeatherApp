package com.example.weatherapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.Screen
import com.example.weatherapp.data.DailyForecastItem
import com.example.weatherapp.viewModels.DailyForecastViewModel
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ForecastScreen(
    navController: NavController,
    zip: String
){
    Column(
        modifier = Modifier.clickable{
            navController.navigate(Screen.Today.route)
        }
    ) {
        TopForecastBar()
        DailyForecastRows(zip)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopForecastBar(){
    TopAppBar(
        title = {Text(text = stringResource(id = R.string.forecast))},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
    )
}


@Composable
fun DailyForecastRows(
    zip: String,
    viewModel: DailyForecastViewModel = hiltViewModel(),
){
    val forecastData = viewModel.dailyForecastConditionsData.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared(
            zip,
        )
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
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${forecastDay.weatherData[0]?.iconName}@2x.png",
                contentDescription = "${forecastDay.weatherData[0]?.description}@2x.png",
                modifier = Modifier.size(55.dp)
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
                    Text(text = stringResource(id = R.string.temp) +
                            stringResource(id = R.string.space) +
                            forecastDay.temp.day.toInt() +
                            stringResource(id = R.string.degree_symbol))
                    Text(text = stringResource(id = R.string.high) +
                            stringResource(id = R.string.space) +
                            forecastDay.temp.max.toInt() +
                            stringResource(id = R.string.degree_symbol))
                }
                Column() {
                    Text(text = stringResource(id = R.string.space))
                    Text(text = stringResource(id = R.string.low) +
                            stringResource(id = R.string.space) +
                            forecastDay.temp.min.toInt() +
                            stringResource(id = R.string.degree_symbol))
                }
                Spacer(modifier = Modifier.width(25.dp))
                Column(){
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = stringResource(id = R.string.sunrise) +
                                stringResource(id = R.string.space) +
                                getTime(forecastDay.sunrise.toString())
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = stringResource(id = R.string.sunset) +
                                stringResource(id = R.string.space) +
                                getTime(forecastDay.sunset.toString())
                    )
                }
            }
        }
    }
}

// convert UTC timestamp to datetime
private fun getMonthDay(s: String): String? {
    try {
        val sdf = SimpleDateFormat("MMM d")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}

// convert UTC timestamp to datetime
private fun getTime(s: String): String? {
    try {
        val sdf = SimpleDateFormat("h:mm a")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}