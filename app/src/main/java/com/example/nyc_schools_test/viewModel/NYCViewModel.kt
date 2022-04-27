package com.example.nyc_schools_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyc_schools_test.model.Repository
import com.example.nyc_schools_test.model.UIState
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NYCViewModel(private val repository: Repository): ViewModel() {

    private val _schoolList = MutableLiveData<UIState>()
    val schoolList: LiveData<UIState>
    get() = _schoolList

    init{
        getSchoolList()
    }

    /**
     * Coroutine Scope defines a "container" of Coroutines.
     * Launch - create and forget
     * Async - create and "await" for a value
     *
     * Dispatchers.IO - Network calls, database transactions, java.io.File
     * Dispatchers.Main - Main thread reference.
     * Dispatchers.Default - Default thread pool.
     * Dispatchers.Unconfined - DON'T USE! ANR(Application Not Responding)
     */
    private fun getSchoolList() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.useCaseSchoolList()
                .collect {
                    _schoolList.value = it
                }
        }
    }
}