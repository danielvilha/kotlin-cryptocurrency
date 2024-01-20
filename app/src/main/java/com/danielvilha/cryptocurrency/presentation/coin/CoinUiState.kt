package com.danielvilha.cryptocurrency.presentation.coin

import com.danielvilha.models.CoinDetail

/**
 * Created by Daniel Ferreira de Lima Vilha 29/11/2023.
 */
data class CoinUiState(
    var id: String? = null,
    var coinDetail: CoinDetail? = null
)
