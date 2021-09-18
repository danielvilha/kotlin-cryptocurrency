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
class GetCoinsListUnitTest {

    @Test
    fun get_coins_list() {
        CoroutineScope(Dispatchers.IO).launch {
            val test = CryptocurrencyApi.retrofitService.getCoinsList().execute()
            val body = test.body()

            assert(body!!.isNotEmpty())
        }
    }
}