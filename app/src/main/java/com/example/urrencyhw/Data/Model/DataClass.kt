package com.example.urrencyhw.Data.Model

import com.google.gson.JsonObject

data class DataClass(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: JsonObject
)