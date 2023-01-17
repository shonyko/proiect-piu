package com.example.proiect.activities.new

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.databinding.FragmentAddActivityOptionSelectionBinding
import com.example.proiect.model.ActionOption
import com.example.proiect.people.addPerson.personSelection.PersonSelectionViewModel
import com.example.proiect.people.addPerson.sourceSelection.SelectSourceAdapter

class ActivitySelectionFragment : Fragment() {
    private var _binding: FragmentAddActivityOptionSelectionBinding? = null
    private val binding get() = _binding!!

    val viewModel: PersonSelectionViewModel by viewModels()

    private lateinit var optionsAdapter: SelectSourceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddActivityOptionSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        optionsAdapter = SelectSourceAdapter(
            object : SelectSourceAdapter.OptionClickListener {
                override fun itemSelected(id: Int) {
                    when(id) {
                        0-> {}
                        1-> {}
                        2->{}
                    }
                }
            }
        )
        optionsAdapter.dataSource = listOf<ActionOption>(
            ActionOption("Notificare","Alege "),
            ActionOption("Eveniment","Creaza un eveniment repetabil"),
            ActionOption("Checklist","Creaza un eveniment repetabil")
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ActivitySelectionFragment.optionsAdapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}