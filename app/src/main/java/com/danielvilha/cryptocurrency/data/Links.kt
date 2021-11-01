package com.danielvilha.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
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

data class Site(
    val site: String,
    val links: String
)