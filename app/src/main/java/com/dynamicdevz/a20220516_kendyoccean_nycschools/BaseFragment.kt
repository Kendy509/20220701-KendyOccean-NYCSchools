package com.dynamicdevz.a20220516_kendyoccean_nycschools;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider
import com.dynamicdevz.a20220516_kendyoccean_nycschools.viewmodel.ViewModelSchools
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    protected val viewModelSchool: ViewModelSchools by lazy {
        ViewModelProvider(requireActivity())[ViewModelSchools::class.java]
    }
}
