package com.danielvilha.cryptocurrency.network

import com.danielvilha.cryptocurrency.data.CoinDetailDto
import com.danielvilha.cryptocurrency.data.CoinDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
interface CryptocurrencyApiService {

    @GET("coins")
    fun getCoinsList(): Call<List<CoinDto>>

    @GET("coins/{coinId}")
    fun getCoinById(@Path("coinId") coinId: String): Call<CoinDetailDto>
}