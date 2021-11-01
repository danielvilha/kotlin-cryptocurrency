package com.danielvilha.cryptocurrency.ui.coin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.cryptocurrency.data.Links
import com.danielvilha.cryptocurrency.data.Site
import com.danielvilha.cryptocurrency.databinding.ViewLinkItemBinding

/**
 * Created by danielvilha on 24/10/21
 * https://github.com/danielvilha
 */
class LinkAdapter: ListAdapter<Site, LinkAdapter.LinkViewHolder>(DiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [String]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        return LinkViewHolder(ViewLinkItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class LinkViewHolder(private var binding: ViewLinkItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(site: Site) {
            Log.v(LinkAdapter::class.java.simpleName, "Name: $site")
            binding.textSite.text = site.links
        }
    }
}