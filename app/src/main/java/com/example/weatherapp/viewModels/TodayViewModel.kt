package com.example.weatherapp.viewModels

import androidx.core.text.isDigitsOnly
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

    val zipCode: MutableLiveData<String> = MutableLiveData("55106")
    val invalidZip: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _todayData: MutableLiveData<TodayData> = MutableLiveData()
    val todayData: LiveData<TodayData>
        get() = _todayData

    fun validateZip(): Boolean {
        val input = zipCode.value
        return if (input.isNullOrEmpty() || input.length != 5 || !input.isDigitsOnly()) {
            zipCode.value = "55106"
            false
        } else {
            viewAppeared()
            true
        }
    }
    fun viewAppeared(zip: String? = zipCode.value) = viewModelScope.launch {
        _todayData.value = apiService.getCurrentData(zip.toString())
    }

}