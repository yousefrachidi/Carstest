package com.youssefra.renault.data.repository

import com.youssefra.renault.data.remote.services.CarApiService
import com.youssefra.renault.data.model.Car
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val carApiService: CarApiService
) {
    suspend fun getCars(): List<Car> {
        val response = carApiService.getCarsMakes()

        if (response.isSuccessful) {
            // Safely handle the response body
            print("debug response *************************************")
            print(response.body())
            return    getCarsTest() // Return the cars list, or empty if null
        } else {
            // If the request fails, throw an exception with the error message
            throw Exception("Failed to load cars: ${response.message()}")
        }
    }


      fun getCarsTest(): List<Car> {
        return listOf(
            Car(
                make = "Toyota g",
                model = "A reliable family car",
                imageUrl = "https://example.com/toyota.jpg"
            ),
            Car(
                make = "Ford Mustang",
                model = "A classic muscle car",
                imageUrl = "https://example.com/mustang.jpg"
            ),
            Car(
                make = "Tesla Model S",
                model = "An innovative electric car",
            )
        )
    }
}

