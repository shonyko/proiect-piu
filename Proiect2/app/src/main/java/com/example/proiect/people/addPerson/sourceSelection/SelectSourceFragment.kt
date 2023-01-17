package com.example.proiect.people.addPerson.sourceSelection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.databinding.FragmentAddPersonSourceBinding

class SelectSourceFragment: Fragment() {
    private var _binding: FragmentAddPersonSourceBinding? = null
    private val binding get() = _binding!!

    val viewModel: SelectSourceViewModel by viewModels()
    private lateinit var optionsAdapter: SelectSourceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonSourceBinding.inflate(inflater, container, false)
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
                    val direction = SelectSourceFragmentDirections.actionAddPersonSourceToPersonList(id==1)
                    findNavController().navigate(direction)
                }
            }
        )
        optionsAdapter.dataSource = viewModel.getOptions()
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@SelectSourceFragment.optionsAdapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}