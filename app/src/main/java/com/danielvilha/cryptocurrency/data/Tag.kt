package com.danielvilha.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
data class Tag(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    val id: String,
    val name: String
)
