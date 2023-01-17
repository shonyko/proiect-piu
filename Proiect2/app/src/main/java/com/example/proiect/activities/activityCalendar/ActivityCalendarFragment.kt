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
import com.example.proiect.people.PeopleViewModel

class ActivityCalendarFragment: Fragment() {
    private var _binding: FragmentActivityCalendarBinding? = null
    private val binding get() = _binding!!

    val viewModel: ActivityCalendarViewModel by viewModels()

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
                override fun itemSelected(activityId: Int) {
                    //findNavController().navigate(direction)
                }
            }
        )
        binding.activityList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ActivityCalendarFragment.adapter
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.reload(year,month+1,dayOfMonth)
        }

        binding.add.setOnClickListener {
            val direction = ActivityCalendarFragmentDirections.initiateAddActivity()
            findNavController().navigate(direction)
        }
        viewModel.reload()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                adapter.refreshData(state.activities)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}