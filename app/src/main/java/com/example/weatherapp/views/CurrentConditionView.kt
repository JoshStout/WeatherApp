package com.example.weatherapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
                    Text(text = today.value?.conditions?.temp?.toInt().toString() +
                            stringResource(id = R.string.degree_symbol),
                        fontSize = 55.sp
                    )
                    Text(text = stringResource(id = R.string.feels_like) +
                            stringResource(id = R.string.space) +
                            today.value?.conditions?.feelsLike?.toInt() +
                            stringResource(id = R.string.degree_symbol),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = stringResource(id = R.string.low_temp) +
                        stringResource(id = R.string.space) +
                        stringResource(id = R.string.space) +
                        today.value?.conditions?.tempMin?.toInt() +
                        stringResource(id = R.string.degree_symbol),
                    fontSize = 15.sp)
                Text(text = stringResource(id = R.string.high_temp) +
                        stringResource(id = R.string.space) +
                        today.value?.conditions?.tempMax?.toInt() +
                        stringResource(id = R.string.degree_symbol),
                    fontSize = 15.sp)
                Text(text = stringResource(id = R.string.humidity) +
                        stringResource(id = R.string.space) +
                        today.value?.conditions?.humidity +
                        stringResource(id = R.string.percent),
                    fontSize = 15.sp)
                Text(text = stringResource(id = R.string.pressure) +
                        stringResource(id = R.string.space) +
                        today.value?.conditions?.pressure +
                        stringResource(id = R.string.space) +
                        stringResource(id = R.string.hPa),
                    fontSize = 15.sp)
            }
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${today.value?.weatherData?.get(0)?.iconName}@2x.png",
                contentDescription = "${today.value?.weatherData?.get(0)?.description}@2x.png",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        ZipCodeField(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = stringResource(id = R.string.app_name))},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
    )
}

@Composable
fun ForecastButton(
    navController: NavController
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
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
            Text(text = stringResource(id = R.string.forecast), color = Color.Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZipCodeField(
    viewModel: TodayViewModel
){
    val input = viewModel.zipCode.observeAsState()
    val showAlert = viewModel.invalidZip.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = input.value.toString(),
            onValueChange = {
                viewModel.zipCode.value = it
            },
            label = { Text(text = stringResource(id = R.string.enter_zip)) },
        )
        Button(
            onClick = {
                viewModel.invalidZip.value = !viewModel.validateZip()
            },
            colors = ButtonDefaults.buttonColors(Color.Gray),
        ){
            Text(text = stringResource(id = R.string.update_zip))
        }
        if(showAlert.value){
            InvalidZip {
                viewModel.invalidZip.value = false
            }
        }
    }
}

@Composable
fun InvalidZip(
    onDismiss: () -> Unit
){
    AlertDialog(onDismissRequest = onDismiss,
        confirmButton = @Composable{
            Button(onClick = onDismiss){
                Text(stringResource(id = R.string.ok))
            }
        },
        text = @Composable{
            Text(text = stringResource(id = R.string.enter_valid_zip))
        }
    )
}