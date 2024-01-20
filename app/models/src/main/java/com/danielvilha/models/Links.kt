package com.danielvilha.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
data class Links(
    val explorer: List<String> = emptyList(),
    val facebook: List<String> = emptyList(),
    val reddit: List<String> = emptyList(),
    @SerializedName("source_code")
    val sourceCode: List<String> = emptyList(),
    val website: List<String> = emptyList(),
    val youtube: List<String> = emptyList()
)
