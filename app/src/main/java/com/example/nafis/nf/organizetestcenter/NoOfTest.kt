package com.example.nafis.nf.organizetestcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.nafis.nf.organizetestcenter.Adapter.TotalTestAdapter
import com.example.nafis.nf.organizetestcenter.Model.TotalNoTestModel
import com.example.nafis.nf.organizetestcenter.databinding.FragmentNoOfTestBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NoOfTest(private var clasname:String?,private var subname:String?,private var chap:String?) : Fragment() {
    private lateinit var binding: FragmentNoOfTestBinding
    private lateinit var adapter:TotalTestAdapter
    private lateinit var list: ArrayList<TotalNoTestModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNoOfTestBinding.inflate(LayoutInflater.from(context))
        binding.backarrowbtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.wrapper,ChapterFragment(subname,clasname))?.commit()
            activity?.supportFragmentManager?.popBackStack()
        }
        val title = arguments?.getString("key")
        binding.chaptername.text = chap // Example: setting the text of a TextView

//        LoadData()
        loadDataFromFirebase()

        return binding.root
    }

//    private fun LoadData() {
//        list=ArrayList()
//
//        when(clasname){
//               "Class 12"->{
//                   when(subname){
//                       "Physics"->{
//                           when(chap){
//                               "Electrostatics"->{
//                                   list.add(TotalNoTestModel(1,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                   list.add(TotalNoTestModel(2,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                   list.add(TotalNoTestModel(3,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                   list.add(TotalNoTestModel(4,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                               }
//                           }
//                       }
//                   }
//               }
//               "Class 11"->{
//                      when(subname){
//                          "Physics"->{
//                              when(chap){
//                                  "Vector"->{
//                                      list.add(TotalNoTestModel(1,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                      list.add(TotalNoTestModel(1,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                      list.add(TotalNoTestModel(1,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                      list.add(TotalNoTestModel(1,"Test Paper 1","${30} Question |","${30} marks |","${60}  minute ", classname = clasname, subname = subname, chapname = chap))
//                                  }
//                              }
//                          }
//                      }
//               }
//
//        }
//
//
//
//        adapter=TotalTestAdapter(requireContext(),list)
//        binding.totaltestAdapter.adapter=adapter
//
//
//    }

    private fun loadDataFromFirebase() {
        binding.progressbar.visibility = View.VISIBLE
        binding.helping.visibility = View.GONE
        list = ArrayList()

        val dbReference = clasname?.let { FirebaseDatabase.getInstance().getReference("Class").child(it) }
            ?.child(subname ?: "")
            ?.child(chap ?: "")

        dbReference?.addListenerForSingleValueEvent(object : ValueEventListener {   
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val testPaper = snap.getValue(TotalNoTestModel::class.java)
                        if (testPaper != null) {
                            list.add(testPaper)
                        }
                    }

                    if (isAdded) { // Ensure the fragment is still attached to an activity
                        adapter = context?.let { TotalTestAdapter(it, list) }!!
                        binding.progressbar.visibility = View.GONE
                        binding.totaltestAdapter.adapter = adapter
                    }
                    if(list.isEmpty()){
                        binding.apply {
                            progressbar.visibility = View.GONE
                            totaltestAdapter.visibility = View.GONE
                            helping.visibility = View.VISIBLE
                        }
                    }

                } else {
                    if (isAdded) { // Ensure the fragment is still attached to an activity
                        binding.apply {
                            progressbar.visibility = View.GONE
                            totaltestAdapter.visibility = View.GONE
                            helping.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors
            }
        })
    }




}