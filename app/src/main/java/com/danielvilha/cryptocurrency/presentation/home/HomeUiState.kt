package com.danielvilha.cryptocurrency.presentation.home

import com.danielvilha.models.Coin
import com.danielvilha.models.CoinStatus

/**
 * Created by Daniel Ferreira de Lima Vilha 25/11/2023.
 */
data class HomeUiState(
    val status: CoinStatus? = null,
    val coins: MutableList<Coin> = mutableListOf()
)
