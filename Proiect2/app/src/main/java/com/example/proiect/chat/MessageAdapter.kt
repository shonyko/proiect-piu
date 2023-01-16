package com.example.proiect.chat

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.R
import com.example.proiect.databinding.FragmentChatMessageBinding
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.Message

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    inner class MessageViewHolder(
        val binding: FragmentChatMessageBinding
    ): RecyclerView.ViewHolder(binding.root)

    private val dataSource = mutableListOf<Message>()
    private var color1: Int = 0
    private var color2: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = FragmentChatMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        color1 = ContextCompat.getColor(parent.context,R.color.mainColor)
        color2 = ContextCompat.getColor(parent.context,R.color.secondaryColor)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = dataSource[position]

        with(holder) {
            if(item.author.id != LoggedInUser.id){
                binding.author.text = item.author.name
                binding.view.gravity = Gravity.LEFT
                binding.text.setBackgroundColor(color1)
            }
            else {
                binding.author.text = ""
                binding.view.gravity= Gravity.RIGHT
                binding.text.setBackgroundColor(color2)
            }
            binding.text.text = item.message
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(items: List<Message>) {
        dataSource.clear()
        dataSource.addAll(items)
        this.notifyDataSetChanged()
    }
}