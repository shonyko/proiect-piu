package com.example.proiect.people.addPerson.sourceSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proiect.databinding.FragmentPeopleListItemBinding
import com.example.proiect.databinding.TitleSubtitleCellBinding
import com.example.proiect.model.ActionOption

class SelectSourceAdapter (private val clickListener: OptionClickListener
) : RecyclerView.Adapter<SelectSourceAdapter.SourceViewHolder>() {
    inner class SourceViewHolder(
        val binding: TitleSubtitleCellBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface OptionClickListener {
        fun itemSelected(id: Int)
    }

    var dataSource:List<ActionOption> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding = TitleSubtitleCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        with(holder) {
            binding.title.text = dataSource[position].name
            binding.subtitle.text = dataSource[position].description
            binding
            binding.root.setOnClickListener {
                clickListener.itemSelected(position)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.count()
}