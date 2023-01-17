package com.example.proiect.people.addPerson.personSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proiect.R
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.model.Person
import com.example.proiect.model.Role


class PersonSelectionAdapter(
    private val clickListener: ClickListener, private val showNumber: Boolean
) : RecyclerView.Adapter<PersonSelectionAdapter.PersonSelectionViewHolder>() {
    inner class PersonSelectionViewHolder(
        val binding: FragmentPeopleListItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(id: Int)
    }

    private val dataSource = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonSelectionViewHolder {
        val binding = FragmentPeopleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonSelectionViewHolder, position: Int) {
        val person = dataSource[position]

        with(holder) {
            binding.title.text = person.name
            if(showNumber)
                binding.subtitle.text = person.phone
            else
                if (person.role == Role.MEDIC)
                    binding.subtitle.text = "Medic"
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