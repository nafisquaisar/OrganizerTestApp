package com.example.nafis.nf.organizetestcenter.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nafis.nf.organizetestcenter.HomeFragment
import com.example.nafis.nf.organizetestcenter.Model.HomeModel
import com.example.nafis.nf.organizetestcenter.R
import com.example.nafis.nf.organizetestcenter.SubFragment
import com.example.nafis.nf.organizetestcenter.databinding.HomeItemBinding

class AdapterClass(var context: Context,var list:ArrayList<HomeModel>) :Adapter<classViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): classViewHolder {
         val view: HomeItemBinding =HomeItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return classViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: classViewHolder, position: Int) {
        val model=list[position]
        holder.bind(model)
        holder.itemView.setOnClickListener{
            // Navigate to the subFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val subFragment = SubFragment(model.sub) // Replace with your actual subFragment class

            // You can pass data using arguments if needed
            val bundle = Bundle()
            bundle.putString("key", model.sub) // Example of passing data
            subFragment.arguments = bundle

            // Replace the current fragment with the new one
            transaction.replace(R.id.wrapper, subFragment)
            transaction.addToBackStack(null) // Optional: to add this transaction to the back stack
            transaction.commit()
        }

    }
}