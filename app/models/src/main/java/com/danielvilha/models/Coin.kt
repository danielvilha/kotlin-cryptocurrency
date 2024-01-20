package com.danielvilha.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Ferreira de Lima Vilha 25/11/2023.
 */
data class Coin(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
