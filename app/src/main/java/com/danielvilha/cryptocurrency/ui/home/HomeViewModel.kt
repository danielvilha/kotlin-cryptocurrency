package com.danielvilha.cryptocurrency.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielvilha.cryptocurrency.data.CoinDto
import com.danielvilha.cryptocurrency.network.CryptocurrencyApi
import com.danielvilha.cryptocurrency.ui.binding.CoinStatus
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 *
 * The [ViewModel] that is attached to the [HomeFragment].
 */
class HomeViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<CoinStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<CoinStatus>
        get() = _status

    // Internally, I use a MutableLiveData, because I will be updating the List of CoinDto
    // with new values
    private val _coins = MutableLiveData<List<CoinDto>>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    val coins: LiveData<List<CoinDto>>
        get() = _coins

    /**
     * Call getCoinsList() on init so we can display status immediately.
     */
    init {
        getCoinsList()
    }

    private fun getCoinsList() {
        viewModelScope.launch {
            _status.value = CoinStatus.LOADING

            val callback = CryptocurrencyApi.retrofitService.getCoinsList()
            callback.enqueue(object: Callback<List<CoinDto>> {
                override fun onResponse(
                    call: Call<List<CoinDto>>,
                    response: Response<List<CoinDto>>
                ) {
                    _coins.value = response.body()
                    _status.value = CoinStatus.DONE
                }

                override fun onFailure(call: Call<List<CoinDto>>, t: Throwable) {
                    _coins.value = mutableListOf()
                    _status.value = CoinStatus.ERROR
                }
            })
        }
    }
}