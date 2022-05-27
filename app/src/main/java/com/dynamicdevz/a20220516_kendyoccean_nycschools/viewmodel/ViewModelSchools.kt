package com.dynamicdevz.a20220516_kendyoccean_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.SchoolRepository
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.StateAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ViewModelSchools @Inject constructor(
    private val repository: SchoolRepository,
    private val ioScheduler: Scheduler,
    private val disposables: CompositeDisposable
) : ViewModel() {

    private val _schools: MutableLiveData<StateAnswer> = MutableLiveData(StateAnswer.LOADING)
    val schools: LiveData<StateAnswer> get() = _schools

    private val _scores: MutableLiveData<StateAnswer> = MutableLiveData(StateAnswer.LOADING)
    val scores: LiveData<StateAnswer> get() = _scores

    fun getAllSchools() {
        repository.schools
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _schools.postValue(StateAnswer.SCHOOLS(it)) },
                { _schools.postValue(StateAnswer.ERROR(it)) }
            ).also { disposables.add(it) }
    }

    fun getScoreByDbn(schoolDbn: String) {
        repository.getScores(schoolDbn)
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _scores.postValue(StateAnswer.SCORES(it)) },
                { _scores.postValue(StateAnswer.ERROR(it)) }
            ).also { disposables.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}