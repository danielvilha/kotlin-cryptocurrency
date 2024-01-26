package com.danielvilha.cryptocurrency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielvilha.cryptocurrency.presentation.coin.CoinUiState
import com.danielvilha.models.Coin
import com.danielvilha.models.CoinDetail
import com.danielvilha.models.CoinStatus
import com.danielvilha.models.sources.CryptocurrencyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<CoinStatus>()
    val status: MutableLiveData<CoinStatus>
        get() = _status

    private val _state = MutableStateFlow(CoinUiState())
    val state = _state.asStateFlow()

    private val _valid : MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val valid : StateFlow<Boolean?> = _valid

    // Internally, I use a MutableLiveData, because I will be updating the List of CoinDto
    // with new values
    private val _coins = MutableLiveData<MutableList<Coin>>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    val coins: LiveData<MutableList<Coin>>
        get() = _coins

    private val _isRefreshing : MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean?> = _isRefreshing

    val selectedActive : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val selectedInactive : MutableStateFlow<Boolean> = MutableStateFlow(true)

    fun selectedActive(value: Boolean) { selectedActive.value = value }
    fun selectedInactive(value: Boolean) { selectedInactive.value = value }

    /**
     * Call getCoinsList() on init so we can display status immediately.
     */
    init {
        getCoinsList()
    }

    fun trackSplashScreenStarted() {
        //dummy method
    }

    fun refresh() {
        _coins.value = mutableListOf()
        getCoinsList()
    }

    fun updateState(newState: CoinUiState) {
        _state.value = newState
    }

    fun getCoinsList() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _state.value.coinDetail = null
            _status.value = CoinStatus.LOADING

            val callback = CryptocurrencyApi.retrofitService.getCoinsList()
            callback.enqueue(object: Callback<MutableList<Coin>> {
                override fun onResponse(
                    call: Call<MutableList<Coin>>,
                    response: Response<MutableList<Coin>>
                ) {
                    _coins.value = response.body()
                    _status.value = CoinStatus.DONE
                    _isRefreshing.value = false
                    _valid.update { true }
                }

                override fun onFailure(call: Call<MutableList<Coin>>, t: Throwable) {
                    _coins.value = mutableListOf()
                    _status.value = CoinStatus.ERROR
                    _isRefreshing.value = false
                    _valid.update { true }
                }
            })
        }
    }

    fun getCoinById(id: String) {
        _state.value.status = CoinStatus.LOADING

        val callback = CryptocurrencyApi.retrofitService.getCoinById(id)
        callback.enqueue(object : Callback<CoinDetail> {
            override fun onResponse(
                call: Call<CoinDetail>,
                response: Response<CoinDetail>
            ) {
                _state.value.coinDetail = response.body()
                _state.value.status = CoinStatus.DONE
                _valid.update { true }
            }

            override fun onFailure(call: Call<CoinDetail>, t: Throwable) {
                _state.value.status = CoinStatus.ERROR
                _valid.update { true }
            }
        })
    }
}