package com.example.proiect.people.patients

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.R
import com.example.proiect.databinding.FragmentPatientsListBinding
import com.example.proiect.pager.PagerFragmentDirections
import com.example.proiect.people.PeopleViewModel

class PatientsFragment : Fragment() {
    private var _binding: FragmentPatientsListBinding? = null
    private val binding get() = _binding!!

    val viewModel: PeopleViewModel by viewModels()

    private lateinit var charactersAdapter: PatientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.noResults.visibility = View.GONE
        binding.errView.visibility = View.GONE
        charactersAdapter = PatientsAdapter(
            object : PatientsAdapter.PersonClickListener {
                override fun itemSelected(id: Int) {
                    val direction = PagerFragmentDirections.statisticsFragment()
                    findNavController().navigate(direction)
                }
            }
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@PatientsFragment.charactersAdapter
        }
        binding.searchInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.searchByName(text.toString())
        }
        binding.searchInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                binding.searchIcon.setImageResource(
                    if (!hasFocus) R.drawable.ic_baseline_search_24
                    else R.drawable.ic_baseline_cancel_24
                )
                if (!hasFocus) {
                    val imm =
                        this@PatientsFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        binding.searchIcon.setOnClickListener {
            binding.searchInput.editText?.clearFocus()
        }
        binding.addPerson.setOnClickListener {
            val direction = PagerFragmentDirections.initiateAddPerson()
            findNavController().navigate(direction)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                binding.noResults.visibility =
                    if (!state.isLoading && state.characters.isEmpty()) View.VISIBLE
                    else View.GONE
                binding.loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                binding.searchBarAndButton.visibility =
                    if (state.isLoading) View.GONE else View.VISIBLE
                binding.list.visibility = if (state.isLoading) View.GONE else View.VISIBLE
                binding.addPerson.visibility = if (state.isLoading) View.GONE else View.VISIBLE
                charactersAdapter.refreshData(state.characters)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}