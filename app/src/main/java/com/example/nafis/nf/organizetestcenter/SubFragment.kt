package com.example.nafis.nf.organizetestcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nafis.nf.organizetestcenter.Adapter.AdapterClass
import com.example.nafis.nf.organizetestcenter.Adapter.SubAdapter
import com.example.nafis.nf.organizetestcenter.Model.SubModel
import com.example.nafis.nf.organizetestcenter.databinding.FragmentSubBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SubFragment(private val clas:String?) : Fragment() {
   private lateinit var binding: FragmentSubBinding
    private lateinit var list:ArrayList<SubModel>
    private lateinit var adapter:SubAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubBinding.inflate(inflater, container, false)
        binding.backarrowbtn.setOnClickListener{
             activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.wrapper,HomeFragment())?.commit()
             activity?.supportFragmentManager?.popBackStack()
        }
        val clas = arguments?.getString("key")
        if (clas != null) {
            binding.classname.text = clas
        }
        LoadData()
//        loadDatafromFirebase(clas)

        return binding.root
    }

    private fun LoadData() {
        list =ArrayList()

        when (clas) {
            "Class 12", "Class 11" -> {
                list.add(SubModel("Physics", "Master Physics principles", clas))
                list.add(SubModel("Chemistry", "Challenge Chemistry knowledge", clas))
                list.add(SubModel("Math", "Sharpen problem-solving skills", clas))
            }
            "Class 10", "Class 9" -> {
                list.add(SubModel("Physics", "Explore Physics fundamentals", clas))
                list.add(SubModel("Chemistry", "Enhance Chemistry understanding", clas))
                list.add(SubModel("Math", "Test Math prowess", clas))
                list.add(SubModel("Bio", "Dive into Biology", clas))
                list.add(SubModel("English Grammar", "Refine language skills", clas))
            }
            "Class 8", "Class 7", "Class 6" -> {
                list.add(SubModel("Science", "Explore Science wonders", clas))
                list.add(SubModel("Social Science", "Understand society and history", clas))
                list.add(SubModel("Math", "Build Math foundation", clas))
                list.add(SubModel("English Grammar", "Improve language abilities", clas))
            }
        }

        // Initialize the adapter and set it to the RecyclerView
        adapter = SubAdapter(requireContext(), list)
        binding.subAdapter.adapter = adapter
    }

//    private fun loadDatafromFirebase(clas: String?) {
//        list = ArrayList()
//        val db = FirebaseDatabase.getInstance().getReference("Class").child(clas ?: "")
//
//        db.child("subjects").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (!isAdded || isDetached) return  // Check if Fragment is still attached
//
//                for (snap in snapshot.children) {
//                    val currentSubject = snap.getValue(SubModel::class.java)
//                    if (currentSubject != null) {
//                        list.add(currentSubject)
//                    }
//                }
//

//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle any errors
//            }
//        })
//    }




}