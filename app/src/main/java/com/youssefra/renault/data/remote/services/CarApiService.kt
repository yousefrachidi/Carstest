package com.youssefra.renault.data.remote.services

import com.google.gson.JsonArray
import com.youssefra.renault.data.model.CarResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarApiService {

    // Define the GET request to fetch cars
    @GET("?cmd=getModels&make=ford")
    suspend fun getCars(): Response<CarResponse>

    @GET("?cmd=getMakes")
    suspend fun getCarsMakes(): Response<CarResponse>
}