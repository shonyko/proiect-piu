package com.example.proiect.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proiect.R
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.model.Person

class ActivitiesAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {
    inner class ActivityViewHolder(
        val binding: FragmentPeopleListItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(activityId: Int)
    }

    private val dataSource = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = FragmentPeopleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val person = dataSource[position]

        with(holder) {
            binding.title.text = person.name
            binding.subtitle.text = person.description
            if(person.image == null)
                binding.image.setImageResource(R.drawable.ic_baseline_person_24)
            else
                binding.image.load(
                    data = person.image
                )
            binding.root.setOnClickListener {
                clickListener.itemSelected(person.id)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(characters: List<Person>) {
        dataSource.clear()
        dataSource.addAll(characters)
        this.notifyDataSetChanged()
    }
}