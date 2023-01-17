package com.example.proiect.activities.todaysActivities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.activities.ActivitiesAdapter
import com.example.proiect.databinding.FragmentActivitiesBinding
import com.example.proiect.mainMenu.MainMenuAdapter
import com.example.proiect.people.PeopleViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TodaysActivitiesFragment: Fragment() {
    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    val viewModel: TodaysActivitiesViewModel by viewModels()

    private lateinit var adapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        adapter = ActivitiesAdapter(
            object : ActivitiesAdapter.ClickListener {
                override fun itemSelected(activityId: Int) {
                    //findNavController().navigate(direction)
                }
            }
        )
        binding.noActivities.visibility = View.GONE
        binding.currDay.text = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
        binding.activities.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@TodaysActivitiesFragment.adapter
        }

        binding.calendarButton.setOnClickListener {
            val dir = TodaysActivitiesFragmentDirections.goToCalendarAct()
            findNavController().navigate(dir)
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