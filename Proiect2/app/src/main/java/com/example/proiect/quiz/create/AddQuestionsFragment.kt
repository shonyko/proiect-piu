package com.example.proiect.quiz.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.proiect.databinding.FragmentAddQuestionsBinding
import com.example.proiect.model.Question
import com.example.repo.QuizRepoImpl

class AddQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentAddQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddQuestionsBinding.inflate(inflater, container, false)
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

        binding.addMoreBtn.setOnClickListener {
            addQuestion()
        }

        binding.finishBtn.setOnClickListener {
            addQuestion()
            goBack()
            goBack()
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {

        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun addQuestion() {
        val text = binding.nameValue.text.toString()
        val correctAnswer = binding.answerValue.text.toString()
        val answers = listOf(
            correctAnswer,
            binding.raspunsGresit1.text.toString(),
            binding.raspunsGresit2.text.toString(),
            binding.raspunsGresit3.text.toString()
        )

        QuizRepoImpl.addQuestion(Question(text, "", answers, correctAnswer))

        binding.nameValue.text.clear()
        binding.answerValue.text.clear()
        binding.raspunsGresit1.text.clear()
        binding.raspunsGresit2.text.clear()
        binding.raspunsGresit3.text.clear()

        Toast.makeText(
            context,
            "Intrebare adaugata !",
            Toast.LENGTH_LONG
        ).show()
    }
}