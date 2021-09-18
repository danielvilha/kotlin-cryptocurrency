package com.danielvilha.cryptocurrency.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.cryptocurrency.data.CoinDto
import com.danielvilha.cryptocurrency.databinding.ViewCoinItemBinding

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 */
class CryptocurrencyAdapter: ListAdapter<CoinDto, CryptocurrencyAdapter.CryptocurrencyViewHolder>(DiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [CoinDto]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<CoinDto>() {
        override fun areItemsTheSame(oldItem: CoinDto, newItem: CoinDto): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CoinDto, newItem: CoinDto): Boolean {
            return oldItem.id == newItem.id
        }

    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        return CryptocurrencyViewHolder(ViewCoinItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CryptocurrencyViewHolder(private var binding: ViewCoinItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinDto) {
            binding.textRank.text = String.format("${item.rank}.")
            binding.textName.text = item.name
            binding.textSymbol.text = String.format("(${item.symbol})")
            binding.textActive.text = if (item.isActive) "active" else "inactive"
            binding.textActive.setTextColor(if (item.isActive) Color.GREEN else Color.RED)
        }
    }
}