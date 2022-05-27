package com.dynamicdevz.a20220516_kendyoccean_nycschools

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dynamicdevz.a20220516_kendyoccean_nycschools.databinding.FragmentScoreBinding
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.StateAnswer

class ScoreFragment : BaseFragment() {

    private lateinit var binding: FragmentScoreBinding

    private var school: Schools? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            school = it.getSerializable("school_item") as Schools
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false)

        viewModelSchool.scores.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateAnswer.LOADING -> {
                    Log.d("SCORES LOADING", "LOADING....")
                }
                is StateAnswer.SCORES -> {
                    Log.d("SCORES SUCCESS", state.scores.toString())

                    val score = state.scores.firstOrNull()

                    if (state.scores.isEmpty()) {
                        binding.schReadScore.visibility = View.GONE
                        binding.schWriteScore.visibility = View.GONE
                        binding.mathScore.visibility = View.GONE
                    } else {
                        binding.schReadScore.text = String.format("Read Score: " + score?.satCriticalReadingAvgScore)
                        binding.schWriteScore.text = String.format("Write Score: " + score?.satWritingAvgScore)
                        binding.mathScore.text = "Math Score: " + score?.satMathAvgScore
                    }
                }
                is StateAnswer.ERROR -> {
                    Log.e("ERROR", state.error.localizedMessage)

                    AlertDialog.Builder(activity)
                        .setTitle("Error happened")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {}
            }
        }

        school?.let {
            binding.schName.text = it.schoolName
            binding.schOverview.text = it.overviewParagraph
            binding.schWeb.text = it.website
            binding.schPhoneNumber.text = it.phoneNumber
            binding.schLocation.text = it.location

            it.dbn?.let { id ->
                viewModelSchool.getScoreByDbn(id)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }
}