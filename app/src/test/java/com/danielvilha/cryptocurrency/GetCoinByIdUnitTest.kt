package com.danielvilha.cryptocurrency

import com.danielvilha.cryptocurrency.network.CryptocurrencyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
class GetCoinByIdUnitTest {

    @Test
    fun get_coin_by_id() {
        CoroutineScope(Dispatchers.IO).launch {
            val test = CryptocurrencyApi.retrofitService.getCoinById("btc-bitcoin").execute()
            val body = test.body()

            assert(body!!.id.isNotEmpty())
        }
    }
}