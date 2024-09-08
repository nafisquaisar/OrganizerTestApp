package com.example.nafis.nf.organizetestcenter.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.SubModel
import com.example.nafis.nf.organizetestcenter.databinding.SubItemBinding

class SubViewHolder(private val binding: SubItemBinding):RecyclerView.ViewHolder(binding.root) {
     fun bind(item: SubModel){
            binding.subsubjname.text=item.sub
            binding.subsubjdes.text=item.des
     }
}
