package com.example.urrencyhw.Data.Remote


import com.example.urrencyhw.Data.Model.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("latest")
    fun getCurrencies(@Query("access_key") key: String): Call<CurrencyModel>
}