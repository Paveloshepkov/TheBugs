package com.example.thebugs.ui.adaports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thebugs.database.Score
import com.example.thebugs.databinding.ScoreItemBinding


class ScoreListAdapter(private val onItemClicked: (Score) -> Unit) :
    ListAdapter<Score, ScoreListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ScoreItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ScoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Score) {
            binding.value.text = item.score

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Score>() {
            override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
                return oldItem.score == newItem.score
            }
        }
    }
}