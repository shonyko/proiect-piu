package com.example.proiect.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.databinding.FragmentQuestionListItemBinding
import com.example.proiect.model.Quiz

class QuizAdapter(
    private val clickListener: QuizAdapter.ClickListener
) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(
        val binding: FragmentQuestionListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var dataSource = mutableListOf<Quiz>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = FragmentQuestionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = dataSource[position]

        with(holder) {
            binding.title.text = quiz.name
            binding.description.text = String.format("%d questions", quiz.questions.size)
            binding.root.setOnClickListener {
                clickListener.itemSelected(quiz)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.size

    fun refreshData(quizzes: List<Quiz>) {
        dataSource.clear()
        dataSource.addAll(quizzes)
        this.notifyDataSetChanged()
    }

    fun setDataSource(quizzes: MutableList<Quiz>) {
        dataSource = quizzes
    }

    interface ClickListener {
        fun itemSelected(quiz: Quiz)
    }
}