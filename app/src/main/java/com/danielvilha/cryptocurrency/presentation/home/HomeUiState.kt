package com.danielvilha.cryptocurrency.presentation.home

import com.danielvilha.models.Coin

/**
 * Created by Daniel Ferreira de Lima Vilha 25/11/2023.
 */
data class HomeUiState(
    val coins: MutableList<Coin> = mutableListOf()
)
