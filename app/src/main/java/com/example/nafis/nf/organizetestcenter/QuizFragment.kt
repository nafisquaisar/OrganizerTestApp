package com.example.nafis.nf.organizetestcenter

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.nafis.nf.organizetestcenter.Model.QuizModel
import com.example.nafis.nf.organizetestcenter.Model.TestPaper
import com.example.nafis.nf.organizetestcenter.databinding.FragmentQuizBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizFragment(private val clasname: String?, private val subname: String?, private val chap: String?, private val id: Int?) : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private var list = ArrayList<QuizModel>()
    private var totalQes = 0
    private var position = 0
    private var right = 0
    private lateinit var quizModel: QuizModel
    private lateinit var countDownTimer: CountDownTimer
    private var totalTimeInMillis: Long = 60 * 1000 // 60 seconds for demo; adjust as needed
    private val selectedOptionsMap = HashMap<Int, Int>() // Store selected options
    private val correctAnswersMap = HashMap<Int, Boolean>() // Store correctness of each answer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        loadDataFromFirebase()
        setupQuiz()
        binding.quizchapName.text=chap
        binding.backarrowbtn.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.wrapper, NoOfTest(clasname, subname, chap))
                    .commit()
            }
            activity?.supportFragmentManager?.popBackStack()
        }


        binding.noFoundChapterName.text=chap
        return binding.root
    }

    private fun checkCorrectOption(ans: String): Boolean {
        return ans.equals(quizModel.correctop, ignoreCase = true)
    }

    private fun calculateScore() {
        right = correctAnswersMap.values.count { it } // Count the number of correct answers
    }

    private fun setupQuiz() {
        binding.questionPreviousbtn.setOnClickListener {
            saveSelectedOption()
            position--
            SetQuiz()
        }

        binding.questionNextSkipbtn.setOnClickListener {
            saveSelectedOption()
            position++
            SetQuiz()
        }

        binding.questionSubmitbtn.setOnClickListener {
            saveSelectedOption()
            calculateScore()
            // Navigate to result fragment
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.wrapper, ResultFragment(right, totalQes, clasname, subname, chap, id, selectedOptionsMap))
                ?.addToBackStack(null)
                ?.commit()
            // Clear data
            list.clear()
            position = 0
            countDownTimer.cancel()
        }
    }

    private fun SetQuiz() {
        totalQes = list.size // Ensure totalQes is set
        position = Math.max(0, Math.min(position, totalQes - 1)) // Clamp position

        quizModel = list[position]

        binding.Question.text = quizModel.quizQues
        binding.option1radio.text = quizModel.quizop1
        binding.option2radio.text = quizModel.quizop2
        binding.option3radio.text = quizModel.quizop3
        binding.option4radio.text = quizModel.quizop4

        binding.totalQuestion.text = "/${totalQes.toString()}"
        binding.currQuestionNo.text = (position + 1).toString()
        binding.questionProgressIndicator.progress =
            ( position.toFloat() / totalQes.toFloat() * 100 ).toInt()
        restoreSelectedOption()
        checkOption()
        updateNavigationButtons()
    }

    private fun updateNavigationButtons() {
        binding.questionPreviousbtn.visibility = if (position <= 0) View.GONE else View.VISIBLE
        binding.questionNextSkipbtn.visibility = if (position >= totalQes - 1) View.GONE else View.VISIBLE
    }


    private fun loadDataFromFirebase() {
        notVisibile()
        list = ArrayList()

        try {
            val dbReference = FirebaseDatabase.getInstance().getReference("Class")
                .child(clasname ?: "")
                .child(subname ?: "")
                .child(chap ?: "")

            dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var foundTestPaper = false
                    for (testPaperSnapshot in snapshot.children) {
                        val testPaper = testPaperSnapshot.getValue(TestPaper::class.java)
                        if (testPaper != null && testPaper.id == id) {
                            list.addAll(testPaper.questions)
                            totalTimeInMillis = testPaper.totalTime.toLong() * 60 * 1000 // Convert minutes to milliseconds
                            foundTestPaper = true
                            break // Exit loop once the correct test paper is found
                        }
                    }

                    if (foundTestPaper) {
                        totalQes = list.size
                        if (totalQes > 0) {
                            position = 0
                            SetQuiz() // Only call SetQuiz if there are questions to display
                            visibile()
                            startTimer(totalTimeInMillis)
                        } else {
                            // Handle the case when there are no questions
                            binding.progredssll.visibility=View.GONE
                            binding.notQuestionFound.visibility=View.VISIBLE
                            binding.notQuestionFoundHeader.visibility=View.VISIBLE
                        }
                    } else {
                        // Handle the case when the test paper ID is not found
                        binding.progredssll.visibility=View.GONE
                        binding.notQuestionFound.visibility=View.VISIBLE
                        binding.notQuestionFoundHeader.visibility=View.VISIBLE
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    binding.progredssll.visibility=View.GONE
                    binding.notQuestionFound.visibility=View.VISIBLE
                    binding.notQuestionFoundHeader.visibility=View.VISIBLE
                }
            })
        } catch (e: Exception) {
            binding.progredssll.visibility=View.GONE
            binding.notQuestionFound.visibility=View.VISIBLE
            binding.notQuestionFoundHeader.visibility=View.VISIBLE
        }
    }

    fun visibile(){
        binding.apply {
            progressbar.visibility=View.GONE
            headerTitle.visibility=View.VISIBLE
            progredssll.visibility=View.VISIBLE
            Question.visibility=View.VISIBLE
            optionsRadioGroup.visibility=View.VISIBLE
            btnll.visibility=View.VISIBLE
            notQuestionFoundHeader.visibility=View.GONE
            notQuestionFound.visibility=View.GONE
        }
    }
    fun notVisibile(){
        binding.apply {
            progressbar.visibility=View.VISIBLE
            headerTitle.visibility=View.GONE
            progredssll.visibility=View.GONE
            Question.visibility=View.GONE
            optionsRadioGroup.visibility=View.GONE
            btnll.visibility=View.GONE
            notQuestionFoundHeader.visibility=View.GONE
            notQuestionFound.visibility=View.GONE
        }
    }


    private fun checkOption() {
        binding.optionsRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val selectedText = selectedRadioButton?.text.toString()
            correctAnswersMap[position] = checkCorrectOption(selectedText)
        }
    }

    private fun saveSelectedOption() {
        val selectedId = binding.optionsRadioGroup.checkedRadioButtonId
        if (selectedId != -1) {
            selectedOptionsMap[position] = selectedId
        }
    }

    private fun restoreSelectedOption() {
        val savedOptionId = selectedOptionsMap[position]
        if (savedOptionId != null) {
            binding.optionsRadioGroup.check(savedOptionId)
        } else {
            binding.optionsRadioGroup.clearCheck()
        }
    }

    private fun startTimer(millisInFuture: Long) {
        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Convert milliseconds to minutes and seconds
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                // Format the time as MM:SS
                binding.timer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.wrapper,
                        ResultFragment(right, totalQes, clasname, subname, chap, id, selectedOptionsMap)
                    )
                    ?.addToBackStack(null)
                    ?.commit()
                list.clear()
                position = 0
            }
        }
        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (::countDownTimer.isInitialized) {
                countDownTimer.cancel() // Cancel only if initialized
            }
        } catch (e: UninitializedPropertyAccessException) {
            // Handle the case where the timer wasn't initialized (this should not happen with isInitialized check)
            Toast.makeText(context, "Error loading data: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}
