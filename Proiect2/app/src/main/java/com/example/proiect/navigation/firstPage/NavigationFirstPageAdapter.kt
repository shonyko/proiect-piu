package com.example.proiect.navigation.firstPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.databinding.FragmentLocationItemBinding
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.model.Location

class NavigationFirstPageAdapter (private val clickListener: ClickListener
) : RecyclerView.Adapter<NavigationFirstPageAdapter.LocationsViewHolder>() {
    inner class LocationsViewHolder(
        val binding: FragmentLocationItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun itemSelected(id: Int)
    }
    private val dataSource = mutableListOf<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding = FragmentLocationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LocationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = dataSource[position]

        with(holder) {
            binding.title.text = item.name
            binding.subtitle.text = item.address
            binding.description.text = item.description
            binding.root.setOnClickListener {
                clickListener.itemSelected(item.id)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.count()

    fun refreshData(items: List<Location>) {
        dataSource.clear()
        dataSource.addAll(items)
        this.notifyDataSetChanged()
    }
}