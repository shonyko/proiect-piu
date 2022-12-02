package com.example.proiect.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.Role
import com.google.android.material.tabs.TabLayoutMediator
import com.example.proiect.databinding.PagerFragmentBinding
class PagerFragment:Fragment() {

    private var _binding: PagerFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: PagerAdapter
    private var session: LoggedInUser = LoggedInUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun setupViews() {
        pagerAdapter = PagerAdapter(this)
        session.role = Role.PACIENT
        pagerAdapter.options = session.menuOptions()
        binding.pager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = pagerAdapter.options[position].name
        }.attach()

        binding.toolbar.menu.getItem(0).setOnMenuItemClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder
                .setTitle("Sign out?")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes") { _, _ ->
                    val direction = PagerFragmentDirections.actionSignOut()
                    findNavController().navigate(direction)
                }
                .setNegativeButton("No", null)
                .create()
                .show()
            true
        }
    }

}