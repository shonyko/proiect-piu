package com.example.proiect.people.contacts

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.proiect.R
import com.example.proiect.databinding.FragmentPeopleListBinding
import com.example.proiect.people.PeopleViewModel

class PeopleFragment :Fragment() {
    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = _binding!!

    val viewModel: PeopleViewModel by viewModels()

    private lateinit var charactersAdapter: PeopleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
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
        charactersAdapter = PeopleAdapter(
            object : PeopleAdapter.PersonClickListener {
                override fun itemSelected(id: Int) {
                    val direction = PeopleFragmentDirections.actionPersonToDetails(id)
                    findNavController().navigate(direction)
                }
            }
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@PeopleFragment.charactersAdapter
        }
        binding.searchInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.searchByName(text.toString())
        }
        binding.searchInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                binding.searchIcon.setImageResource(if(!hasFocus) R.drawable.ic_baseline_search_24
                else R.drawable.ic_baseline_cancel_24)
                if(!hasFocus){
                    val imm = this@PeopleFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        binding.searchIcon.setOnClickListener {
            binding.searchInput.editText?.clearFocus()
        }
        binding.addPerson.setOnClickListener {
            val direction = PeopleFragmentDirections.initiateAddPerson()
            findNavController().navigate(direction)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                binding.noResults.visibility = if(!state.isLoading && state.characters.isEmpty()) View.VISIBLE
                else View.GONE
                binding.loading.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.searchBarAndButton.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                binding.list.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                binding.addPerson.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                charactersAdapter.refreshData(state.characters)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}