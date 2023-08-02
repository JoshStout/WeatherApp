package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.ApiService
import com.example.weatherapp.data.TodayData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _todayData: MutableLiveData<TodayData> = MutableLiveData()
    val todayData: LiveData<TodayData>
        get() = _todayData

    fun viewAppeared() = viewModelScope.launch {
        _todayData.value = apiService.getCurrentData()
    }
}