package com.example.repo

import com.example.proiect.model.Question
import com.example.proiect.model.Quiz

interface QuizRepo {
    fun getQuizzes(): MutableList<Quiz>
    fun addQuiz(quiz: Quiz)
    fun addQuestion(question: Question)
    fun getSelectedQuiz(): Quiz
    fun selectQuiz(quiz: Quiz)
}

object QuizRepoImpl : QuizRepo {
    private val quizList = mutableListOf<Quiz>(
        Quiz(
            "test",
            mutableListOf(
                Question("merge?", null, listOf("da", "nu", "poate", "merge"), "da"),
                Question("ploua?", null, listOf("cu ananas", "cu pesti", "cu foi", "cu sfinti"), "cu sfinti")
            )
        ),
    )

    private var selectedQuiz: Quiz? = null

    override fun getQuizzes(): MutableList<Quiz> = quizList

    override fun addQuiz(quiz: Quiz) {
        quizList.add(quiz)
    }

    override fun addQuestion(question: Question) {
        quizList.last().questions.add(question)
    }

    override fun getSelectedQuiz(): Quiz {
        return selectedQuiz!!
    }

    override fun selectQuiz(quiz: Quiz) {
        selectedQuiz = quiz
    }


}