package com.example.proiect.people.addPerson.personSelection

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.R
import com.example.proiect.databinding.FragmentAddPersonListBinding


class PersonSelectionFragment : Fragment() {
    private var _binding: FragmentAddPersonListBinding? = null
    private val binding get() = _binding!!

    val viewModel: PersonSelectionViewModel by viewModels()

    private lateinit var selectionAdapter: PersonSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.noResults.visibility = View.GONE
        selectionAdapter = PersonSelectionAdapter(
            object : PersonSelectionAdapter.ClickListener {
                override fun itemSelected(id: Int) {
                    val dialogBuilder = AlertDialog.Builder(requireContext())

                    dialogBuilder
                        .setTitle("Adauga persoana")
                        .setMessage("Sunteti siguri ca doriti sa-l/o adaugati pe"+viewModel.getPersonName(id).name+"in lista de contacte?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.addPerson(id)
                            val direction = PersonSelectionFragmentDirections.confirmPerson()
                            findNavController().navigate(direction)
                        }
                        .setNegativeButton("No", null)
                        .create()
                        .show()

                }
            },
            viewModel.showPhone
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@PersonSelectionFragment.selectionAdapter
        }
        binding.searchInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.searchByName(text.toString())
        }
        binding.searchInput.editText?.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                binding.searchIcon.setImageResource(if(!hasFocus) R.drawable.ic_baseline_search_24
                else R.drawable.ic_baseline_cancel_24)
                if(!hasFocus){
                    val imm = this@PersonSelectionFragment.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        binding.searchIcon.setOnClickListener {
            binding.searchInput.editText?.clearFocus()
        }
        binding.noResults.text = if(viewModel.showPhone) "Nu exista persoane in telefonul tau cu acest nume"
                else "Utilizatorul cautat nu exista"
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                binding.noResults.visibility = if(!state.isLoading && state.characters.isEmpty()) View.VISIBLE
                else View.GONE
                binding.searchBarAndButton.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                binding.loading.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.list.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                selectionAdapter.refreshData(state.characters)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}