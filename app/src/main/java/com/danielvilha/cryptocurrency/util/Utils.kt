package com.danielvilha.cryptocurrency.util

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */

fun textIsActive(isActive: Boolean): String =
    if (isActive) "active" else "inactive"

fun toDateString(date: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val output = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)

    if (date.isEmpty())
        return "No date"

    val dateFormatter = input.parse(date)
    return output.format(dateFormatter!!)
}