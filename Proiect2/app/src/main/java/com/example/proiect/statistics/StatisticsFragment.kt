package com.example.proiect.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.proiect.databinding.FragmentStatisticsBinding
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            goBack()
        }

        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                // on below line we are adding
                // each point on our x and y axis.
                DataPoint(0.0, 4.0),
                DataPoint(1.0, 5.0),
                DataPoint(2.0, 3.0),
                DataPoint(3.0, 6.0),
                DataPoint(4.0, 6.0),
                DataPoint(5.0, 5.0),
                DataPoint(6.0, 0.0),
                DataPoint(7.0, 1.0),
                DataPoint(8.0, 1.0),
            )
        )

        val series2: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                // on below line we are adding
                // each point on our x and y axis.
                DataPoint(0.0, 1.0),
                DataPoint(1.0, 2.0),
                DataPoint(2.0, 3.0),
                DataPoint(3.0, 0.0),
                DataPoint(4.0, 6.0),
                DataPoint(5.0, 4.0),
                DataPoint(6.0, 5.0),
                DataPoint(7.0, 7.0),
                DataPoint(8.0, 6.0),
            )
        )

        series2.color = Color.RED

        with(binding.idGraphView) {
            animate()
            viewport.isScrollable = true
//            viewport.isScalable = true
//            viewport.setScalableY(true)
            viewport.setScrollableY(true)
            addSeries(series)
            addSeries(series2)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {

        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}