package com.example.proiect.quiz.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proiect.databinding.FragmentCreateQuizBinding
import com.example.proiect.model.Quiz
import com.example.proiect.navigation.addLocation.AddLocationViewModel
import com.example.proiect.quiz.QuizAdapter
import com.example.repo.QuizRepoImpl

class CreateQuizFragment : Fragment() {
    private lateinit var binding: FragmentCreateQuizBinding

    val viewModel: AddLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateQuizBinding.inflate(inflater, container, false)
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

        binding.addBtn.setOnClickListener {
            QuizRepoImpl.addQuiz(Quiz(binding.nameValue.text.toString(), mutableListOf()))

            val direction = CreateQuizFragmentDirections.createQuizToQuestions()
            findNavController().navigate(direction)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->

            }
        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}