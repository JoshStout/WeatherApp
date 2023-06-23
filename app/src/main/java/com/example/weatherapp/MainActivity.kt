package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")

                    InitialScreen()

                }
            }
        }
    }
}

@Composable
fun DisplayTemp(){
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
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column() {
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Text(text = stringResource(id = R.string.temp),
                        fontSize = 55.sp
                    )
                    Text(text = stringResource(id = R.string.feels_like),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = stringResource(id = R.string.low_temp), fontSize = 15.sp)
                Text(text = stringResource(id = R.string.high_temp), fontSize = 15.sp)
                Text(text = stringResource(id = R.string.humidity), fontSize = 15.sp)
                Text(text = stringResource(id = R.string.pressure), fontSize = 15.sp)
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
fun InitialScreen(){
    Column{
        TopBar()
        DisplayTemp()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        InitialScreen()
    }
}