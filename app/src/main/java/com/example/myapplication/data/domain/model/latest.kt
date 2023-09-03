package com.example.myapplication.data.domain.model

data class Latest(
    val timestamp: String,
    val base: String,
    val date: String,
    val success: Boolean,
    //val rates: Map<String, Double>
    val rates:Rate

)
