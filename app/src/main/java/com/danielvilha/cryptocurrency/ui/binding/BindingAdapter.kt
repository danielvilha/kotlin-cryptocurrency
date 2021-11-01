package com.danielvilha.cryptocurrency.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.data.CoinDto
import com.danielvilha.cryptocurrency.ui.home.adapter.CryptocurrencyAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by danielvilha on 17/09/21
 * https://github.com/danielvilha
 */

/**
 * When there is no Coins data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CoinDto>?) {
    val adapter = recyclerView.adapter as CryptocurrencyAdapter
    adapter.submitList(data)
}

/**
 * Working with my API status in LOADING, ERROR or DONE
 */
@BindingAdapter("coinStatus")
fun bindStatus(statusImageView: ImageView, status: CoinStatus) {
    when (status) {
        CoinStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CoinStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_phone_error)
        }
        CoinStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

fun toDateString(date: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val output = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)

    if (date.isEmpty())
        return "No date"

    val dateFormatter = input.parse(date)
    return output.format(dateFormatter!!)
}

enum class CoinStatus { LOADING, ERROR, DONE }