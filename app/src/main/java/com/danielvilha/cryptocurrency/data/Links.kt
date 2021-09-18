package com.danielvilha.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    val website: List<String>,
    val youtube: List<String>
)
