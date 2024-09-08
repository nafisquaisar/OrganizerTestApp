package com.example.nafis.nf.organizetestcenter

import AnsShowFragment
import android.graphics.Color
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.airbnb.lottie.LottieAnimationView
import com.example.nafis.nf.organizetestcenter.databinding.FragmentResultBinding

class ResultFragment(
    private var right: Int,
    private var total: Int,
    private var clasname: String?,
    private var subname: String?,
    private var chap: String?,
    private var id: Int?,
    private var correctans: HashMap<Int, Int>
) : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var animationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        animationView = binding.animationView

        val totalQuestions = total
        val percentage = ((right.toFloat() / totalQuestions.toFloat()) * 100).toInt()

        binding.apply {
            correct.text = "$right Correct"
            incorrect.text = "${total - right} Incorrect"
            resultscore.text = "You got $right out of $total"
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"


                    when {
                        percentage > 90 -> {
                            progresstext.text = "Excellent"
                            progresstext.setTextColor(ContextCompat.getColor(requireContext(), R.color.Excellent))
                        }

                        percentage in 80..90 -> {
                            progresstext.text = "Outstanding"
                            progresstext.setTextColor(ContextCompat.getColor(requireContext(), R.color.Outstanding))
                        }

                        percentage in 70..79 -> {
                            progresstext.text = "Very Good"
                            progresstext.setTextColor(ContextCompat.getColor(requireContext(), R.color.VeryGood))
                        }

                        percentage in 50..69 -> {
                            progresstext.text = "Good"
                            progresstext.setTextColor(ContextCompat.getColor(requireContext(), R.color.Good))
                        }

                        else -> {
                            progresstext.text = "Oops! You have failed"
                            progresstext.setTextColor(ContextCompat.getColor(requireContext(), R.color.Bad))
                        }
                    }

            // Set animation based on percentage
            updateAnimation(
                if (percentage >= 50) "https://lottie.host/a8820a63-e95f-4a2e-84af-36d4912c42d2/Xb2VVh8YRT.json"
                else "https://lottie.host/811f7a8d-c6f3-43e8-b2b8-9383cd77ef0b/QzA9Q3KgaQ.json"
            )
        }

        binding.ExploreMore.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.let { fm ->
                val backStackEntryCount = fm.backStackEntryCount
                for (i in backStackEntryCount - 1 downTo 0) {
                    val backStackEntry = fm.getBackStackEntryAt(i)
                    if (backStackEntry.name == "NoOfTestFragmentTag") {
                        fm.popBackStack(backStackEntry.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        break
                    }
                }
                fm.beginTransaction()
                    .replace(R.id.wrapper, NoOfTest(clasname, subname, chap))
                    .commit()
            }
        }

        binding.replay.setOnClickListener {
            activity?.supportFragmentManager?.let { fm ->
                val backStackEntryCount = fm.backStackEntryCount
                for (i in backStackEntryCount - 1 downTo 0) {
                    val backStackEntry = fm.getBackStackEntryAt(i)
                    if (backStackEntry.name == "NoOfTestFragmentTag") {
                        fm.popBackStack(backStackEntry.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        break
                    }
                }
                fm.beginTransaction()
                    .replace(R.id.wrapper, QuizFragment(clasname, subname, chap, id))
                    .addToBackStack("QuizFragmentTag")
                    .commitAllowingStateLoss()
            }
        }

        binding.ShowAns.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.wrapper, AnsShowFragment(clasname, subname, chap, id, correctans))
                ?.addToBackStack(null)
                ?.commit()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fm = activity?.supportFragmentManager
                if (fm != null && fm.backStackEntryCount > 1) {
                    fm.popBackStack() // Pops ResultFragment
                    fm.popBackStack() // Pops QuizFragment
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })

        return binding.root
    }

    private fun updateAnimation(url: String) {
        animationView.setAnimationFromUrl(url)
    }
}
