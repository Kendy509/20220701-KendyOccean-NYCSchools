package com.dynamicdevz.a20220516_kendyoccean_nycschools.network

import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Scores

sealed class StateAnswer {
    object LOADING : StateAnswer()
    data class SCHOOLS(val schools: List<Schools>) : StateAnswer()
    data class SCORES(val scores: List<Scores>) : StateAnswer()
    data class ERROR(val error: Throwable) : StateAnswer()
}
