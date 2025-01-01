package com.youssefra.renault.views.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssefra.renault.data.repository.CarRepository
import com.youssefra.renault.data.model.Car
import com.youssefra.renault.views.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CarViewModel @Inject constructor(
    private val carRepository: CarRepository
) :  BaseViewModel() {

    // StateFlow to hold the car list and handle loading and error states
    private val _carList = MutableStateFlow<List<Car>>(emptyList())
    val carList: StateFlow<List<Car>> get() = _carList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Function to load cars from the repository
    fun loadCars() {
        viewModelScope.launch {
            try {
                val cars = carRepository.getCars()  // Fetch cars directly from the repository
                _carList.value = cars
                _errorMessage.value = null // Clear any existing error message
            } catch (e: Exception) {
                _carList.value = emptyList()  // Clear car list on error
                _errorMessage.value = "Failed to load cars: ${e.message}" // Set error message
            }
        }
    }
}

