package com.example.proiect.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.databinding.FragmentQuizBinding
import com.example.proiect.model.Quiz
import com.example.proiect.pager.PagerFragmentDirections
import com.example.repo.QuizRepoImpl

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    private lateinit var adapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.noResults.visibility = View.GONE

        this.adapter = QuizAdapter(object : QuizAdapter.ClickListener {
            override fun itemSelected(quiz: Quiz) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder
                    .setTitle("Take quiz?")
                    .setMessage(String.format("Are you sure you want to try %s?", quiz.name))
                    .setPositiveButton("Yes") { _, _ ->
                        QuizRepoImpl.selectQuiz(quiz)
                        val direction = PagerFragmentDirections.takeQuiz()
                        findNavController().navigate(direction)
                    }
                    .setNegativeButton("No", null)
                    .create()
                    .show()
            }
        })
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@QuizFragment.adapter
        }

        binding.addQuiz.setOnClickListener {
            val direction = PagerFragmentDirections.createQuiz()
            findNavController().navigate(direction)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            binding.loading.visibility = View.GONE
            binding.noResults.visibility =
                if (QuizRepoImpl.getQuizzes().isEmpty()) View.VISIBLE else View.GONE
            binding.list.visibility =
                if (QuizRepoImpl.getQuizzes().isNotEmpty()) View.VISIBLE else View.GONE

            adapter.refreshData(QuizRepoImpl.getQuizzes())
        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}