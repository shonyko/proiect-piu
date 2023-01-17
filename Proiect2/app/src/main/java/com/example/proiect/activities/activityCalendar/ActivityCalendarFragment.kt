package com.example.proiect.activities.activityCalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.activities.ActivitiesAdapter
import com.example.proiect.databinding.FragmentActivityCalendarBinding
import com.example.proiect.pager.PagerFragmentDirections
import com.example.proiect.people.PeopleViewModel

class ActivityCalendarFragment: Fragment() {
    private var _binding: FragmentActivityCalendarBinding? = null
    private val binding get() = _binding!!

    val viewModel: PeopleViewModel by viewModels()

    private lateinit var adapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.noActivities.visibility = View.GONE
        adapter = ActivitiesAdapter(
            object : ActivitiesAdapter.ClickListener {
                override fun itemSelected(id: Int) {
                    val direction = PagerFragmentDirections.actionPersonToDetails(id)
                    findNavController().navigate(direction)
                }
            }
        )
        binding.activityList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ActivityCalendarFragment.adapter
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                binding.noActivities.visibility = if(!state.isLoading && state.characters.isEmpty()) View.VISIBLE
                else View.GONE
                binding.activityList.visibility = if(state.isLoading) View.GONE else View.VISIBLE
                adapter.refreshData(state.characters)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}