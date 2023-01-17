package com.example.proiect.mainMenu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.R
import com.example.proiect.databinding.FragmentMainMenuBinding
import com.example.proiect.databinding.FragmentPeopleListBinding
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.Role

class MainMenuFragment: Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MainMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        adapter = MainMenuAdapter(
            object : MainMenuAdapter.ClickListener {
                override fun itemSelected(direction: NavDirections) {
                    findNavController().navigate(direction)
                }
            }
        )
        binding.menuGrid.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = this@MainMenuFragment.adapter
        }
        if(LoggedInUser.role == Role.PACIENT){
            binding.banner.visibility = View.VISIBLE
        } else binding.banner.visibility = View.GONE

        adapter.refreshData(LoggedInUser.menuOptions())
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}