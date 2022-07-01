package com.dynamicdevz.a20220516_kendyoccean_nycschools

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicdevz.a20220516_kendyoccean_nycschools.adapter.DetailsHandler
import com.dynamicdevz.a20220516_kendyoccean_nycschools.adapter.SchoolAdapter
import com.dynamicdevz.a20220516_kendyoccean_nycschools.databinding.FragmentSchoolBinding
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.StateAnswer

class SchoolFragment : BaseFragment(), DetailsHandler {

    private val binding by lazy {
        FragmentSchoolBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.schoolsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL,  false)
            adapter = schoolAdapter
        }

        viewModelSchool.schools.observe(viewLifecycleOwner) {
            when (it) {
                is StateAnswer.LOADING -> {
                    Log.d("LOADING", "LOADING...")
                }
                is StateAnswer.SCHOOLS -> {
                    schoolAdapter.changeDataSet(it.schools)
                    Log.d("SUCCESS", it.schools.first().dbn.toString())
                }
                is StateAnswer.ERROR -> {
                    Log.e("ERROR", it.error.localizedMessage)
                }
                else -> {
                    //no-op
                }
            }
        }

        viewModelSchool.getAllSchools()

        return binding.root
    }

    override fun handleDetailsClick(school: Schools?) {
        findNavController().navigate(R.id.action_schoolFragment_to_scoreFragment,
        bundleOf(Pair("school_item", school)))
    }
}