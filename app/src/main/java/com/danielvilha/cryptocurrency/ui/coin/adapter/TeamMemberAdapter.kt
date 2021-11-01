package com.danielvilha.cryptocurrency.ui.coin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.cryptocurrency.data.TeamMember
import com.danielvilha.cryptocurrency.databinding.ViewTeamMemberItemBinding

/**
 * Created by danielvilha on 10/10/21
 * https://github.com/danielvilha
 */
class TeamMemberAdapter: ListAdapter<TeamMember, TeamMemberAdapter.TeamMemberViewHolder>(DiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [TeamMember]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<TeamMember>() {
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        return TeamMemberViewHolder(ViewTeamMemberItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TeamMemberViewHolder(private var binding: ViewTeamMemberItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(teamMember: TeamMember) {
            binding.textName.text = teamMember.name
            binding.textPosition.text = teamMember.position
        }
    }
}