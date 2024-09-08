package com.example.nafis.nf.organizetestcenter.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.ChapterModel
import com.example.nafis.nf.organizetestcenter.databinding.HomeItemBinding

class ChapterViewHolder(private val binding: HomeItemBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChapterModel){
        binding.miansubname.text=item.chapname
        binding.maindesp.text=item.des
    }
}
