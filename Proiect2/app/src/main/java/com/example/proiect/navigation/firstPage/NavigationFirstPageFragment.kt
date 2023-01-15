package com.example.proiect.navigation.firstPage

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
import com.example.proiect.databinding.FragmentLocationOptionsBinding
import com.example.proiect.pager.PagerFragmentDirections

class NavigationFirstPageFragment : Fragment() {
    private var _binding: FragmentLocationOptionsBinding? = null
    private val binding get() = _binding!!

    val viewModel: NavigationFirstPageViewModel by viewModels()

    private lateinit var locationsAdapter: NavigationFirstPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentLocationOptionsBinding.inflate(inflater, container, false)
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
        locationsAdapter = NavigationFirstPageAdapter(
            object : NavigationFirstPageAdapter.ClickListener {
                override fun itemSelected(id: Int) {
                    val direction = PagerFragmentDirections.actionPersonToDetails(id)
                    findNavController().navigate(direction)
                }
            }
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@NavigationFirstPageFragment.locationsAdapter
        }
        binding.searchInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.searchByName(text.toString())
        }
        binding.searchInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                binding.searchIcon.setImageResource(if(!hasFocus) R.drawable.ic_baseline_search_24
                else R.drawable.ic_baseline_cancel_24)
                if(!hasFocus){
                    val imm = this@NavigationFirstPageFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
                binding.noResults.visibility = if(!state.isLoading && state.locations.isEmpty()) View.VISIBLE
                else View.GONE
                binding.loading.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.list.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                binding.addPerson.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                locationsAdapter.refreshData(state.locations)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}