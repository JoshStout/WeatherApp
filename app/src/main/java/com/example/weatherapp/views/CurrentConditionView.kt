package com.example.weatherapp.views

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.Screen
import com.example.weatherapp.viewModels.TodayViewModel

@Composable
fun CurrentConditionScreen(navController: NavController){

    Column{
        TopBar()
        DisplayTemp()
        ForecastButton(navController = navController)
    }

}

@Composable
fun DisplayTemp(
    viewModel: TodayViewModel = hiltViewModel()
){
    val today = viewModel.todayData.observeAsState()
    LaunchedEffect(Unit){
        viewModel.viewAppeared()
    }

    Column(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "${today.value?.cityName}", fontSize = 15.sp)
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
                    Text(text = "${today.value?.conditions?.temp?.toInt()}°",
                        fontSize = 55.sp
                    )
                    Text(text = "${today.value?.conditions?.feelsLike?.toInt()}°",
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Low ${today.value?.conditions?.tempMin?.toInt()}°",
                    fontSize = 15.sp)
                Text(text = "High ${today.value?.conditions?.tempMax?.toInt()}°",
                    fontSize = 15.sp)
                Text(text = "Humidity ${today.value?.conditions?.humidity}%",
                    fontSize = 15.sp)
                Text(text = "Pressure ${today.value?.conditions?.pressure} hPa",
                    fontSize = 15.sp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "My Weather App")},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
    )
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