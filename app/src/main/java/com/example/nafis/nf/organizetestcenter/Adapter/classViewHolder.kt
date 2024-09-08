package com.example.nafis.nf.organizetestcenter.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nafis.nf.organizetestcenter.Model.HomeModel
import com.example.nafis.nf.organizetestcenter.databinding.HomeItemBinding

class classViewHolder(private val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    var root=binding.root
    // You can now access the views directly through the binding object
    fun bind(item: HomeModel) {
        binding.miansubname.text=item.sub
        binding.maindesp.text=item.des

    }
}
