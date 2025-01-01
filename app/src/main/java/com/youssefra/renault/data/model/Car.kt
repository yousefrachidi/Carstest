package com.youssefra.renault.data.model

data class CarResponse(
    val cars: List<Car> // List of car objects
)
data class Car(
    var model_name:String= "null" ,
    var model_make_id:String= "null",
    val make: String= "null",
    val model: String= "null",
    val country: String  = "null",
    val imageUrl: String = "https://via.placeholder.com/150" // image BY DEFAULT
)
