package com.example.test_task_numbers_facts.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_numbers_facts.data.model.NumberFactModel
import com.example.test_task_numbers_facts.databinding.ItemViewBinding


class FactsListAdapter(
    private val onItemClicked: (NumberFactModel) -> Unit
) :
    ListAdapter<NumberFactModel, FactsListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(private var binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: NumberFactModel) {
            binding.tvDate.text = fact.number
            binding.tvTemperature.text = fact.numberFact

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewHolder = ListViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NumberFactModel>() {
        override fun areItemsTheSame(oldItem: NumberFactModel, newItem: NumberFactModel): Boolean {
            return oldItem.number == newItem.number
        }
        override fun areContentsTheSame(oldItem: NumberFactModel, newItem: NumberFactModel): Boolean {
            return oldItem == newItem
        }
    }
}