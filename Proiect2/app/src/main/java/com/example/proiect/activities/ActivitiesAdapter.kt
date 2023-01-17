package com.example.proiect.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proiect.R
import com.example.proiect.databinding.FragmentActivityItemBinding
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.model.Activity
import com.example.proiect.model.Person
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActivitiesAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {
    inner class ActivityViewHolder(
        val binding: FragmentActivityItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(activityId: Int)
    }

    private val dataSource = mutableListOf<Activity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = FragmentActivityItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val item = dataSource[position]

        with(holder) {
            binding.name.text = item.name
            binding.description.text = item.description
            binding.time.text = "La data de "+LocalDate.parse(item.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

            binding.root.setOnClickListener {
                //clickListener.itemSelected(person.id)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(characters: List<Activity>) {
        dataSource.clear()
        dataSource.addAll(characters)
        this.notifyDataSetChanged()
    }
}