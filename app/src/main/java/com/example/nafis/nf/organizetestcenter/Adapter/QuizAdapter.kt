package com.example.nafis.nf.organizetestcenter.Adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.QuizModel

class QuizAdapter(private var context: Context, private var list :ArrayList<QuizModel>):
    RecyclerView.Adapter<QuizViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}