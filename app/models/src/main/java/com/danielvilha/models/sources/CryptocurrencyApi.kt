package com.danielvilha.models.sources

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.coinpaprika.com/v1/"

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object CryptocurrencyApi {
    val retrofitService: CryptocurrencyService by lazy {
        retrofit.create(CryptocurrencyService::class.java)
    }
}