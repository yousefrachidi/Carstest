package com.youssefra.renault

import com.youssefra.renault.data.model.Car
import com.youssefra.renault.data.model.CarResponse
import com.youssefra.renault.data.remote.services.CarApiService
import com.youssefra.renault.data.repository.CarRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class CarRepositoryTest {

    private val mockApiService: CarApiService = Mockito.mock(CarApiService::class.java)
    private val carRepository = CarRepository(mockApiService)

    @Test
    fun `getCars should return list of cars from API`() = runBlocking {
        // Données simulées
        val mockResponse = CarResponse(
            Models = listOf(
                Car("Focus", "Compact car", "https://example.com/focus.jpg"),
                Car("Mustang", "Sport car", "https://example.com/mustang.jpg")
            )
        )

        // Simulation de la réponse de l'API
        Mockito.`when`(mockApiService.getCars()).thenReturn(Response.success(mockResponse))

        // Appel à la méthode et vérification du résultat
        val result = carRepository.getCars()
        assertEquals(mockResponse.Models, result)
    }
}
