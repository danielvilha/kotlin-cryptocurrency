package com.danielvilha.models.sources

import com.danielvilha.models.Coin
import com.danielvilha.models.CoinDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
interface CryptocurrencyService {

    @GET("coins")
    fun getCoinsList(): Call<MutableList<Coin>>

    @GET("coins/{coinId}")
    fun getCoinById(@Path("coinId") coinId: String): Call<CoinDetail>
}