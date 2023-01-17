package com.example.proiect.people.patients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proiect.R
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.model.Person

class PatientsAdapter(
    private val personClickListener: PersonClickListener
) : RecyclerView.Adapter<PatientsAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(
        val binding: FragmentPeopleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    interface PersonClickListener {
        fun itemSelected(id: Int)
    }

    private val dataSource = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = FragmentPeopleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = dataSource[position]

        with(holder) {
            binding.title.text = person.name
            binding.subtitle.text = person.description
            if (person.image == null)
                binding.image.setImageResource(R.drawable.ic_baseline_person_24)
            else
                binding.image.load(
                    data = person.image
                )
            binding.root.setOnClickListener {
                personClickListener.itemSelected(person.id)
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