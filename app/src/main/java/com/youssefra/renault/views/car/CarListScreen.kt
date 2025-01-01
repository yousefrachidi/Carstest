package com.youssefra.renault.views.car

import android.widget.Toast
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.youssefra.renault.data.model.Car

@Composable
fun CarListScreen(viewModel: CarViewModel) {
    // Observe the state of carList and errorMessage
    val carList = viewModel.carList.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value

    // Load cars when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.loadCars()
    }

    // UI Layout
    Column(modifier = Modifier.fillMaxSize()) {
        CarErrorMessage(errorMessage) // Display the error message if exists
        CarList(carList) // Display the list of cars
    }
}

@Composable
fun CarErrorMessage(errorMessage: String?) {
    errorMessage?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
    }
}
