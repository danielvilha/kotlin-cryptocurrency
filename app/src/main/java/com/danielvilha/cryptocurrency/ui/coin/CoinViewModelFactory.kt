package com.danielvilha.cryptocurrency.ui.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by danielvilha on 18/09/21
 * https://github.com/danielvilha
 */
class CoinViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
            return CoinViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}