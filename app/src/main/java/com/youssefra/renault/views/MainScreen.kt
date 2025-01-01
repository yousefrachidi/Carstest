package com.youssefra.renault.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.youssefra.renault.views.car.CarViewModel
import com.youssefra.renault.views.car.CarListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(carViewModel: CarViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { "Car List" })

        }
    ) { paddingValues ->
        // Afficher la liste des voitures
        CarListScreen(viewModel = carViewModel)
    }
}








