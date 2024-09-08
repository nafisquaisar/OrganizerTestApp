package com.example.nafis.nf.organizetestcenter.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.ChapterModel
import com.example.nafis.nf.organizetestcenter.NoOfTest
import com.example.nafis.nf.organizetestcenter.R
import com.example.nafis.nf.organizetestcenter.databinding.HomeItemBinding

class ChapterAdapter(private val context: Context,val list:ArrayList<ChapterModel>):
    RecyclerView.Adapter<ChapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view: HomeItemBinding =
            HomeItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ChapterViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val model=list[position]
        holder.bind(model)

        holder.itemView.setOnClickListener{
            val manager=(context as AppCompatActivity).supportFragmentManager
            val transition=manager.beginTransaction()
            val totaltest= NoOfTest(model.clasname,model.subname,model.chapname)

            var bundle=Bundle()
            bundle.putString("key",model.chapname)
            totaltest.arguments=bundle

            transition.replace(R.id.wrapper,totaltest).addToBackStack(null).commit()

        }

    }
}