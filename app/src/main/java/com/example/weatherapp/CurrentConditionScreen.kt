package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.data.DayForecast

@Composable
fun CurrentConditionScreen(navController: NavController, forecastObj: DayForecast){

    Column{
        TopBar()
        DisplayTemp(forecastObj)
        ForecastButton(navController = navController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "My Weather App")},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
    )
}

@Composable
fun DisplayTemp(current: DayForecast){
    Column(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = stringResource(id = R.string.city), fontSize = 15.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column() {
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Text(text = "${current.temp.day.toInt()}°",
                        fontSize = 55.sp
                    )
                    Text(text = stringResource(id = R.string.feels_like),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Low ${current.temp.min.toInt()}°", fontSize = 15.sp)
                Text(text = "High ${current.temp.max.toInt()}°", fontSize = 15.sp)
                Text(text = "Humidity ${current.humidity}%", fontSize = 15.sp)
                Text(text = "Pressure ${current.pressure} hPa", fontSize = 15.sp)
            }
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun Image",
                modifier = Modifier
                    .size(size = 100.dp)
                    .clip(CircleShape),
            )
        }
    }
}

@Composable
fun ForecastButton(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
    ){
        Button(
            shape = RectangleShape,
            onClick = {
                navController.navigate(route = Screen.Forecast.route)
            },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.width(180.dp)
        ){
            Text(text = "Forecast", color = Color.Black)
        }
    }
}