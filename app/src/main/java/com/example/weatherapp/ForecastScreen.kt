package com.example.weatherapp

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.data.DayForecast
import com.example.weatherapp.data.ForecastTemp
import java.text.SimpleDateFormat
import java.util.Date


val temp1 = ForecastTemp(72F, 65F, 80F) // 7/1
val temp2 = ForecastTemp(71F, 61F, 81F)
val temp3 = ForecastTemp(72F, 62F, 82F)
val temp4 = ForecastTemp(73F, 63F, 83F)
val temp5 = ForecastTemp(74F, 64F, 84F)
val temp6 = ForecastTemp(75F, 65F, 85F)
val temp7 = ForecastTemp(76F, 66F, 86F)
val temp8 = ForecastTemp(77F, 67F, 87F)
val temp9 = ForecastTemp(78F, 68F, 88F)
val temp10 = ForecastTemp(79F, 69F, 89F)
val temp11 = ForecastTemp(80F, 70F, 90F)
val temp12 = ForecastTemp(81F, 71F, 91F)
val temp13 = ForecastTemp(82F, 72F, 92F)
val temp14 = ForecastTemp(83F, 73F, 93F)
val temp15 = ForecastTemp(84F, 74F, 94F)
val temp16 = ForecastTemp(85F, 75F, 95F)
val temp17 = ForecastTemp(86F, 76F, 96F) // 7/17

private val forecastItems = listOf(
    // date: 7-1-23 12:00am = UTC 7-2-23 3:00AM; sunrise: 5:00am = UTC 10:00am; sunset: 5:00pm = UTC 10:00pm
    DayForecast(1688268180, 1688292000, 1688335200, temp1, 1023.0F, 100),
    DayForecast(1688354580, 1688378700, 1688421900, temp2, 1002.0F, 2), // 7/2, sunrise: 5:05am, sunset: 5:05pm
    DayForecast(1688440980, 1688465400, 1688508600, temp3, 1003.0F, 3), // 7/3, sunrise: 5:10am, sunset: 5:10pm
    DayForecast(1688527380, 1688552100, 1688595300, temp4, 1004.0F, 4), // 7/4, sunrise: 5:15am, sunset: 5:15pm
    DayForecast(1688613780, 1688638800, 1688682000, temp5, 1005.0F, 5), // 7/5, sunrise: 5:20am, sunset: 5:20pm
    DayForecast(1688700180, 1688725500, 1688768700, temp6, 1006.0F, 6), // 7/6
    DayForecast(1688786580, 1688812200, 1688855400, temp7, 1007.0F, 7), // 7/7
    DayForecast(1688872980, 1688898900, 1688942100, temp8, 1008.0F, 8), // 7/8
    DayForecast(1688959380, 1688985600, 1689028800, temp9, 1009.0F, 9), // 7/9
    DayForecast(1689045780, 1689072300, 1689115500, temp10, 1010.0F, 10), // 7/10
    DayForecast(1689132180, 1689159000, 1689202200, temp11, 1011.0F, 11), // 7/11
    DayForecast(1689218580, 1689245700, 1689288900, temp12, 1012.0F, 12), // 7/12
    DayForecast(1689304980, 1689332400, 1689375600, temp13, 1013.0F, 13), // 7/13
    DayForecast(1689391380, 1689419100, 1689462300, temp14, 1014.0F, 14), // 7/14
    DayForecast(1689477780, 1689505800, 1689549000, temp15, 1015.0F, 15), // 7/15
    DayForecast(1689564180, 1689592500, 1689635700, temp16, 1016.0F, 16), // 7/16
    DayForecast(1689650580, 1689679200, 1689722400, temp17, 1017.0F, 17)  // 7/17
)

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
        LazyColumn(){
            items(items = forecastItems){
                ForecastRows(forecastDay = it)
            }
        }
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
fun ForecastRows(forecastDay: DayForecast){

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
            //Spacer(modifier = Modifier.width(5.dp))
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

@Composable
fun getFirstForecastObj(): DayForecast{
    return forecastItems[0]
}