package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DailyForecastData
import com.example.weatherapp.models.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    val zipCode: MutableLiveData<String> = MutableLiveData("55106")
    private val _dailyForecastConditionsData: MutableLiveData<DailyForecastData> = MutableLiveData()
    val dailyForecastConditionsData: LiveData<DailyForecastData>
        get() = _dailyForecastConditionsData


    fun viewAppeared(zip: String? = zipCode.value) = viewModelScope.launch {
        _dailyForecastConditionsData.value = apiService.getDailyForecastData(zip.toString() + ",us")
    }

}