package com.youssefra.renault
import com.youssefra.renault.data.model.Car
import com.youssefra.renault.data.repository.CarRepository
import com.youssefra.renault.views.car.CarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class CarViewModelTest {



    private val mockRepository: CarRepository = mock()
    private val viewModel = CarViewModel(mockRepository)

    @Test
    fun `loadCars should update carList with data from repository`() = runTest {
        // Mock data
        val mockCars = listOf(
            Car("Focus", "Compact car", "https://example.com/focus.jpg"),
            Car("Mustang", "Sport car", "https://example.com/mustang.jpg")
        )

        // Simuler le comportement du repository
        whenever(mockRepository.getCars()).thenReturn(mockCars)

        // Appel de la méthode
        viewModel.loadCars()

        // Vérification
        val result = viewModel.carList.first()
        assertEquals(mockCars, result)
    }

    @Test
    fun `loadCars should update errorMessage when repository throws exception`() = runTest {
        // Simuler une exception dans le repository
        val errorMessage = "Network error"
        whenever(mockRepository.getCars()).thenThrow(RuntimeException(errorMessage))

        // Appel de la méthode
        viewModel.loadCars()

        // Vérification
        val result = viewModel.errorMessage.first()
        assertEquals("Failed to load cars: $errorMessage", result)
    }
}
