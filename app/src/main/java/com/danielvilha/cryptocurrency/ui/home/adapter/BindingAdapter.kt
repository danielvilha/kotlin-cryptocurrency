package com.danielvilha.cryptocurrency.ui.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.data.CoinDto
import com.danielvilha.cryptocurrency.ui.home.CoinStatus

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