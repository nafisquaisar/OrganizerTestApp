package com.example.nafis.nf.organizetestcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.nafis.nf.organizetestcenter.Adapter.AdapterClass
import com.example.nafis.nf.organizetestcenter.Model.HomeModel
import com.example.nafis.nf.organizetestcenter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var list:ArrayList<HomeModel>
    private lateinit var adapter: AdapterClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use the provided inflater to inflate the binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Return the root view of the binding

        LoadData()

        return binding.root
    }

    private fun LoadData() {
        list =ArrayList()
        list.add(HomeModel("Class 12","math science test"))
        list.add(HomeModel("Class 11","math science test"))
        list.add(HomeModel("Class 10","math science test"))
        list.add(HomeModel("Class 9","math science test"))
        list.add(HomeModel("Class 8","math science test"))
        list.add(HomeModel("Class 7","math science test"))
        list.add(HomeModel("Class 6","math science test"))


        // Initialize the adapter with the list
        adapter = AdapterClass(requireContext(), list)

        // Set the adapter to the RecyclerView
        binding.homerecyclerview.adapter = adapter
    }


}


