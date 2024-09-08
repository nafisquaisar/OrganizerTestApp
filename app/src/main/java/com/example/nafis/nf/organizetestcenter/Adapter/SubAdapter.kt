package com.example.nafis.nf.organizetestcenter.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.ChapterFragment
import com.example.nafis.nf.organizetestcenter.Model.SubModel
import com.example.nafis.nf.organizetestcenter.R
import com.example.nafis.nf.organizetestcenter.databinding.SubItemBinding

class SubAdapter (val context: Context,val list: ArrayList<SubModel>):RecyclerView.Adapter<SubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        val view=SubItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return SubViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
     }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        val model=list[position]
        holder.bind(model)

        holder.itemView.setOnClickListener{
            // Navigate to the subFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val chapterFragment = ChapterFragment(model.sub,model.clas) // Replace with your actual subFragment class

            // You can pass data using arguments if needed
            val bundle = Bundle()
            bundle.putString("key", model.sub) // Example of passing data
            chapterFragment.arguments = bundle

            // Replace the current fragment with the new one
            transaction.replace(R.id.wrapper, chapterFragment)
            transaction.addToBackStack(null) // Optional: to add this transaction to the back stack
            transaction.commit()
        }
    }
}