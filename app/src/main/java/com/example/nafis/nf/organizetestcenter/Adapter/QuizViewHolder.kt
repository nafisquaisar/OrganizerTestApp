package com.example.nafis.nf.organizetestcenter.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.QuizModel
import com.example.nafis.nf.organizetestcenter.databinding.FragmentQuizBinding

class QuizViewHolder (private val binding: FragmentQuizBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(item : QuizModel){
        binding.apply {
            Question.text=item.quizQues

        }
    }
}
