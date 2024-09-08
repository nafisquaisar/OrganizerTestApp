//package com.example.nafis.nf.organizetestcenter
//
//import android.os.Bundle
//import android.os.CountDownTimer
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.CheckBox
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import com.example.nafis.nf.organizetestcenter.Model.QuizModel
//import com.example.nafis.nf.organizetestcenter.databinding.FragmentQuiz2Binding
//import com.example.nafis.nf.organizetestcenter.databinding.FragmentQuizBinding
//
//
//class QuizFragment2(private var clasname:String?,private var subname:String?,private var chap :String?,private var id:Int?) : Fragment() {
//     private lateinit var binding:FragmentQuiz2Binding
//    private lateinit var list:ArrayList<QuizModel>
//    private  var totalQes=0
//    var listsize:String?=null
//    private var position=0
//    private var right=0
//    private var ans:String?=null
//    var PositionNo:String?=null
//    private lateinit var quizModel: QuizModel
//    private lateinit var countDownTimer: CountDownTimer
//    private val totalTimeInMillis: Long =60 * 1000 // 20 minutes in milliseconds
//
//    private val userAnswers = mutableMapOf<Int, Int>() // To store user answers
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding= FragmentQuiz2Binding.inflate(inflater,container,false)
//
////        startTimer(totalTimeInMillis)
//        SetQuiz()
//        enableOption()
//
//        binding.questionNextSkipbtn.setOnClickListener{
//            position++
//            checkNext()
//            SetQuiz()
//            enableOption()
//            clearOption()
//        }
////        binding.questionSubmitbtn.setOnClickListener {
////            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.wrapper,ResultFragment(right,totalQes, clasname = clasname, subname = subname, chap = chap, id = id, correctans = ))?.addToBackStack(null)?.commit()
////            list.clear()
////            position=0
////            countDownTimer.cancel()
////        }
//
//
//        return binding.root
//    }
//
//
//
//
//
//
//
//    private fun checkNext() {
//        if (position >= totalQes - 1) {
//            binding.questionNextSkipbtn.visibility = View.GONE
//        } else {
//            binding.questionNextSkipbtn.visibility = View.VISIBLE
//        }
//    }
//
//    private fun enableOption()  {
//        val options = arrayOf(
//            binding.option1ll, binding.option1, binding.option1ans, binding.option1checkbox,
//            binding.option2ll, binding.option2, binding.option2ans, binding.option2checkbox,
//            binding.option3ll, binding.option3, binding.option3ans, binding.option3checkbox,
//            binding.option4ll, binding.option4, binding.option4ans, binding.option4checkbox
//        )
//        options.forEach { it.isEnabled = true }
//    }
//
//    private fun disableop() {
//        val options = arrayOf(
//            binding.option1ll, binding.option1, binding.option1ans, binding.option1checkbox,
//            binding.option2ll, binding.option2, binding.option2ans, binding.option2checkbox,
//            binding.option3ll, binding.option3, binding.option3ans, binding.option3checkbox,
//            binding.option4ll, binding.option4, binding.option4ans, binding.option4checkbox
//        )
//        options.forEach { it.isEnabled = false }
//    }
//
//    private fun clearOption() {
//        val options = arrayOf(
//            Triple(binding.option1ll, binding.option1, binding.option1ans),
//            Triple(binding.option2ll, binding.option2, binding.option2ans),
//            Triple(binding.option3ll, binding.option3, binding.option3ans),
//            Triple(binding.option4ll, binding.option4, binding.option4ans)
//        )
//
//        options.forEach { (ll, text, ans) ->
//            ll.setBackgroundResource(R.drawable.subitembg)
//            text.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//            ans.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//        }
//
//        binding.option1checkbox.isChecked = false
//        binding.option2checkbox.isChecked = false
//        binding.option3checkbox.isChecked = false
//        binding.option4checkbox.isChecked = false
//    }
//
//
//    private fun SetQuiz() {
//        list= ArrayList()
//
//        list=setQuestion()
//
//        // ========set timer=========
//        totalQes=5
//        listsize = totalQes.toString()
//        binding.totalQuestion.text="/${listsize}"
//        if(position!=totalQes){
//            PositionNo=(position+1).toString()
//        }else{
//            PositionNo=(position).toString()
//        }
//        binding.currQuestionNo.text=PositionNo
//
//        // initialize quiz model and set
//        quizModel= QuizModel()
//        quizModel=list.get(position)
//
//        binding.Question.setText(quizModel.quizQues)
//        binding.option1ans.setText(quizModel.quizop1)
//        binding.option2ans.setText(quizModel.quizop2)
//        binding.option3ans.setText(quizModel.quizop3)
//        binding.option4ans.setText(quizModel.quizop4)
//
//        checkOption()
//
//
//    }
//
//    //====================== Set Question on the Quiz with Class wise====================================
//    private fun setQuestion():ArrayList<QuizModel> {
//
//        when(clasname){
//            "Class 12"->{
//                when(chap){
//                    "Electrostatics"->{
//                        when(id) {
//                            1 -> {
//                                list.add(QuizModel("1", "What is the unit of electric charge?", "Coulomb", "Newton", "Volt", "Farad", "Coulomb"))
//                                list.add(QuizModel("2", "According to Coulomb's Law, the force between two point charges is directly proportional to:", "The product of the charges", "The distance between the charges", "The square of the distance between the charges", "The sum of the charges", "The product of the charges"))
//                                list.add(QuizModel("3", "What is the electric field inside a conductor in electrostatic equilibrium?", "Zero", "Constant", "Varies with charge", "Depends on the material", "Zero"))
//                                list.add(QuizModel("4", "Which of the following is a property of electric field lines?", "They never intersect", "They are always straight", "They start from negative charges", "They form closed loops", "They never intersect"))
//                                list.add(QuizModel("5", "The potential energy of a system of two point charges is directly proportional to:", "The distance between them", "The product of the charges", "The sum of the charges", "The square of the distance between them", "The product of the charges"))
//                            }
//                            2 -> {
//                                list.add(QuizModel("1", "What happens to the force between two charges if the distance between them is halved?", "It doubles", "It quadruples", "It remains the same", "It is halved", "It quadruples"))
//                                list.add(QuizModel("2", "Which of the following materials is an example of an insulator?", "Copper", "Silver", "Rubber", "Aluminum", "Rubber"))
//                                list.add(QuizModel("3", "What is the nature of the force between two like charges?", "Attractive", "Repulsive", "Neutral", "None of the above", "Repulsive"))
//                                list.add(QuizModel("4", "The electric field at a point due to a point charge is:", "Directly proportional to the distance from the charge", "Inversely proportional to the distance squared", "Directly proportional to the distance squared", "Independent of distance", "Inversely proportional to the distance squared"))
//                                list.add(QuizModel("5", "What is the work done in moving a charge between two points on the same equipotential surface?", "Maximum", "Minimum", "Zero", "Depends on the path", "Zero"))
//                            }
//                            3 -> {
//                                list.add(QuizModel("1", "What is the unit of electric charge?", "Volt", "Ampere", "Coulomb", "Ohm", "Coulomb"))
//                                list.add(QuizModel("2", "What is the direction of the electric field around a positive charge?", "Radially inward", "Radially outward", "Circular", "Tangential", "Radially outward"))
//                                list.add(QuizModel("3", "Which law states that the electric flux through any closed surface is proportional to the total charge enclosed by the surface?", "Coulomb’s Law", "Gauss’s Law", "Faraday’s Law", "Ampere’s Law", "Gauss’s Law"))
//                                list.add(QuizModel("4", "What happens to the potential energy of a system of two charges as they are brought closer together?", "It increases", "It decreases", "It remains the same", "It depends on the sign of the charges", "It depends on the sign of the charges"))
//                                list.add(QuizModel("5", "What is the capacitance of a capacitor if it holds a charge of 2 C at a potential difference of 1 V?", "1 F", "2 F", "0.5 F", "4 F", "2 F"))
//                            }
//                        }
//
//                    }
//                }
//            }
//            "Class 11"->{
//                when(chap){
//                    "Vector"->{
//                        when(id){
//                            1->{
//                                list.add(QuizModel("1", "Which of the following is a vector quantity?", "a) Speed", "b) Temperature", "c) Displacement", "d) Time", "c) Displacement"))
//                                list.add(QuizModel("2", "What is the resultant of two equal vectors acting at a right angle?", "a) Zero", "b) Equal to one of the vectors", "c) Sum of the magnitudes", "d) Square root of the sum of the squares of the magnitudes", "d) Square root of the sum of the squares of the magnitudes"))
//                                list.add(QuizModel("3", "Which operation is used to find the direction of a vector?", "a) Cross product", "b) Dot product", "c) Scalar multiplication", "d) Vector subtraction", "a) Cross product"))
//                                list.add(QuizModel("4", "The magnitude of the resultant vector of two vectors is maximum when the angle between them is:", "a) 0 degrees", "b) 90 degrees", "c) 180 degrees", "d) 45 degrees", "a) 0 degrees"))
//                                list.add(QuizModel("5", "What is the angle between two vectors if their dot product is zero?", "a) 0 degrees", "b) 90 degrees", "c) 180 degrees", "d) 45 degrees", "b) 90 degrees"))
//                            }
//                            2->{
//                                list.add(QuizModel("1", "What is the result of the cross product of two parallel vectors?", "a) A zero vector", "b) A vector perpendicular to the original vectors", "c) A vector parallel to the original vectors", "d) None of the above", "a) A zero vector"))
//                                list.add(QuizModel("2", "If a vector has components (3, 4), what is its magnitude?", "a) 5", "b) 7", "c) 12", "d) 25", "a) 5"))
//                                list.add(QuizModel("3", "Which operation is used to find the angle between two vectors?", "a) Dot product", "b) Cross product", "c) Scalar triple product", "d) Vector triple product", "a) Dot product"))
//                                list.add(QuizModel("4", "What is the direction of the resultant vector when two vectors of equal magnitude act at an angle of 90 degrees to each other?", "a) 45 degrees to either vector", "b) Along the bisector of the angle between them", "c) Parallel to one of the vectors", "d) Opposite to one of the vectors", "b) Along the bisector of the angle between them"))
//                                list.add(QuizModel("5", "If vector A = (2, 3, 4) and vector B = (1, -1, 2), what is the dot product A·B?", "a) 7", "b) 12", "c) 10", "d) 9", "a) 7"))
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//
//        return list
//    }
//
//    private fun checkOption() {
//        // Handle clicks on the first option (LinearLayout and CheckBox)
//        binding.option1ll.setOnClickListener {
//            handleOptionClick(quizModel.quizop1, binding.option1ll, binding.option1, binding.option1ans, binding.option1checkbox)
//        }
//        binding.option1checkbox.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                handleOptionClick(quizModel.quizop1, binding.option1ll, binding.option1, binding.option1ans, binding.option1checkbox)
//            }
//        }
//
//        // Handle clicks on the second option (LinearLayout and CheckBox)
//        binding.option2ll.setOnClickListener {
//            handleOptionClick(quizModel.quizop2, binding.option2ll, binding.option2, binding.option2ans, binding.option2checkbox)
//        }
//        binding.option2checkbox.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                handleOptionClick(quizModel.quizop2, binding.option2ll, binding.option2, binding.option2ans, binding.option2checkbox)
//            }
//        }
//
//        // Handle clicks on the third option (LinearLayout and CheckBox)
//        binding.option3ll.setOnClickListener {
//            handleOptionClick(quizModel.quizop3, binding.option3ll, binding.option3, binding.option3ans, binding.option3checkbox)
//        }
//        binding.option3checkbox.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                handleOptionClick(quizModel.quizop3, binding.option3ll, binding.option3, binding.option3ans, binding.option3checkbox)
//            }
//        }
//
//        // Handle clicks on the fourth option (LinearLayout and CheckBox)
//        binding.option4ll.setOnClickListener {
//            handleOptionClick(quizModel.quizop4, binding.option4ll, binding.option4, binding.option4ans, binding.option4checkbox)
//        }
//        binding.option4checkbox.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                handleOptionClick(quizModel.quizop4, binding.option4ll, binding.option4, binding.option4ans, binding.option4checkbox)
//            }
//        }
//    }
//
//    // =============handle the click option =================================
//    private fun handleOptionClick(option: String?, optionView: View, optionText: TextView, optionTextans: TextView, optionCheckbox: CheckBox) {
//        if (option.equals(quizModel.correctop)) {
//            optionView.setBackgroundResource(R.drawable.rightans)
//            right++
//        } else {
//            showRightAns()
//            optionView.setBackgroundResource(R.drawable.wrongans)
//        }
//        disableop()
//        optionText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//        optionTextans.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//        optionCheckbox.isChecked = true
//    }
//
//
//
//    private fun showRightAns() {
//        if(quizModel.quizop1.equals(quizModel.correctop)){
//            binding.option1ll.setBackgroundResource(R.drawable.rightans)
//            binding.option1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1ans.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1checkbox.isChecked=true
//        }else if(quizModel.quizop2.equals(quizModel.correctop)){
//            binding.option2ll.setBackgroundResource(R.drawable.rightans)
//            binding.option2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option2ans.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1checkbox.isChecked=true
//        }else if(quizModel.quizop3.equals(quizModel.correctop)){
//            binding.option3.setBackgroundResource(R.drawable.rightans)
//            binding.option1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1ans.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1checkbox.isChecked=true
//        }else if(quizModel.quizop4.equals(quizModel.correctop)){
//            binding.option4.setBackgroundResource(R.drawable.rightans)
//            binding.option1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1ans.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//            binding.option1checkbox.isChecked=true
//        }
//    }
//
////    // ================= TImer Function=====================
////    private fun startTimer(millisInFuture: Long) {
////        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
////            override fun onTick(millisUntilFinished: Long) {
////                binding.timer.text = (millisUntilFinished / 1000).toString()
////            }
////
////            override fun onFinish() {
////                activity?.supportFragmentManager?.beginTransaction()
////                    ?.replace(
////                        R.id.wrapper,
////                        ResultFragment(
////                            right, totalQes,
////                            clasname = clasname,
////                            subname = subname,
////                            chap = chap,
////                            id = id
////                        )
////                    )
////                    ?.addToBackStack(null)
////                    ?.commit()
////                list.clear()
////                position = 0
////            }
////        }
////        countDownTimer.start()
////    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        countDownTimer.cancel() // Cancel the timer if the activity is destroyed
//    }
//
//}