package com.example.proiect.quiz.take

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.proiect.databinding.FragmentTakeQuizBinding
import com.example.proiect.navigation.addLocation.AddLocationViewModel
import com.example.repo.QuizRepoImpl

class TakeQuizFragment : Fragment() {
    private lateinit var binding: FragmentTakeQuizBinding

    val viewModel: AddLocationViewModel by viewModels()

    private var questionIdx: Int = 0
    private var correctAnswers: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTakeQuizBinding.inflate(inflater, container, false)
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

        binding.form.setOnCheckedChangeListener { _, checkedId ->
            binding.nextBtn.isEnabled = checkedId != -1
        }

        binding.nextBtn.setOnClickListener {
            if (questionIdx >= QuizRepoImpl.getSelectedQuiz().questions.size) {
                goBack()
                return@setOnClickListener
            }

            checkAnswer()
            questionIdx++

            if (questionIdx >= QuizRepoImpl.getSelectedQuiz().questions.size) {
                showScore()
                return@setOnClickListener
            }

            setupQuestion()
        }

        setupQuestion()
    }

    private fun setupQuestion() {
        binding.form.clearCheck()

        val question = QuizRepoImpl.getSelectedQuiz().questions[questionIdx]
        val list = question.answers.toList().shuffled()
        binding.raspuns1.text = list[0]
        binding.raspuns2.text = list[1]
        binding.raspuns3.text = list[2]
        binding.raspuns4.text = list[3]

        if (question.imageUrl.isNullOrEmpty()) {
            binding.image.visibility = View.GONE
        } else {
            binding.image.load(question.imageUrl)
            binding.image.visibility = View.VISIBLE
        }

        binding.question.text = question.text

        if (questionIdx == QuizRepoImpl.getSelectedQuiz().questions.size - 1)
            binding.nextBtn.text = "Submit Quiz"

        binding.nextBtn.isEnabled = false
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {

        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun checkAnswer() {
        val btn = requireActivity().findViewById<RadioButton>(binding.form.checkedRadioButtonId)
        val ans = btn.text

        correctAnswers += if (ans == QuizRepoImpl.getSelectedQuiz().questions[questionIdx].correctAnswer) 1 else 0
    }

    private fun showScore() {

        binding.apply {
            question.visibility = View.GONE
            image.visibility = View.GONE
            form.visibility = View.GONE

            nextBtn.text = "Finish Quiz"

            score.text = String.format(
                "%d / %d",
                correctAnswers,
                QuizRepoImpl.getSelectedQuiz().questions.size
            )
            scoreView.visibility = View.VISIBLE
        }
    }
}