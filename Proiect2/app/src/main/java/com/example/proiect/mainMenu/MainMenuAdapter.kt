package com.example.proiect.mainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.databinding.FragmentMenuButtonBinding
import com.example.proiect.model.MainMenuOption

class MainMenuAdapter(private val listener: ClickListener):
    RecyclerView.Adapter<MainMenuAdapter.OptionViewHolder>() {
    inner class OptionViewHolder(
        val binding:FragmentMenuButtonBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(direction: NavDirections)
    }

    private val dataSource = mutableListOf<MainMenuOption>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val binding = FragmentMenuButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val item = dataSource[position]
        with(holder) {
            binding.buttonIcon.setImageResource(item.imageId)
            binding.optionName.text = item.name
            binding.button.setBackgroundColor(item.colorId)
            binding.button.setOnClickListener {
                listener.itemSelected(item.direction)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(items: List<MainMenuOption>) {
        dataSource.clear()
        dataSource.addAll(items)
        this.notifyDataSetChanged()
    }
}

data class OptionPair(val option1: MainMenuOption, val option2: MainMenuOption?)
