package com.danielvilha.cryptocurrency.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
const val BASE_URL = "https://api.coinpaprika.com/v1/"

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CryptocurrencyApi {
    val retrofitService: CryptocurrencyApiService by lazy {
        retrofit.create(CryptocurrencyApiService::class.java)
    }
}