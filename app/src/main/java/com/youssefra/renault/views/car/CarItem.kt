package com.youssefra.renault.views.car

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.youssefra.renault.data.model.Car

@Composable
fun CarList(carList: List<Car>) {
    if (carList.isEmpty()) {
        EmptyCarListMessage()
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(carList) { car ->
                CarCard(car = car)
            }
        }
    }
}




@Composable
fun EmptyCarListMessage() {
    Text(
        text = "No cars available.",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(16.dp),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
    )
}

