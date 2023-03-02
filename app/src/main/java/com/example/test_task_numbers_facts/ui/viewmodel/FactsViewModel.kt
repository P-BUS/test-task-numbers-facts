package com.example.test_task_numbers_facts.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_numbers_facts.data.model.NumberFactModel
import com.example.test_task_numbers_facts.data.repository.NumberFactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

const val TAG = "FactsViewModel"

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val repository: NumberFactsRepository
) : ViewModel() {

    val numberFacts: SharedFlow<List<NumberFactModel>> =
        repository.factsStream
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )

    private val _currentFact = MutableSharedFlow<NumberFactModel>(
        replay = 1,
        extraBufferCapacity = 0,
        BufferOverflow.DROP_OLDEST
    )
    val currentFact: SharedFlow<NumberFactModel> = _currentFact.asSharedFlow()

    fun retrieveFact(number: String) {
        viewModelScope.launch {
            try {
                repository.retrieveFact(number)
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }

    fun retrieveRandomFact() {
        viewModelScope.launch {
            try {
                repository.retrieveRandomFact()
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }

    fun updateCurrentFact(currentFact: NumberFactModel) {
        viewModelScope.launch {
            _currentFact.emit(currentFact)
        }
    }
}