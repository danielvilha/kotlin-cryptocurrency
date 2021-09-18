package com.danielvilha.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
data class CoinDto(
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
