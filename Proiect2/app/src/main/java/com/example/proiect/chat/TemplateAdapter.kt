package com.example.proiect.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.databinding.FragmentChatMessageBinding
import com.example.proiect.databinding.SimpleOptionCellBinding
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.Message

class TemplateAdapter(private val listener: ClickListener):
    RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {
    inner class TemplateViewHolder(
        val binding: SimpleOptionCellBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(template: String)
    }

    private val dataSource = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val binding = SimpleOptionCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TemplateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val item = dataSource[position]

        with(holder) {
            binding.text.text = item
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(items: List<String>) {
        dataSource.clear()
        dataSource.addAll(items)
        this.notifyDataSetChanged()
    }
}