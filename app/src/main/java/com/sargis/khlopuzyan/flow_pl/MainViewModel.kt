package com.sargis.khlopuzyan.flow_pl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Sargis Khlopuzyan on 3/20/2022.
 */
class MainViewModel : ViewModel() {

    private val _liveDara = MutableLiveData("Hello World")
    val liveData: LiveData<String> = _liveDara

    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow: StateFlow<String> = _stateFlow

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow: SharedFlow<String> = _sharedFlow

    fun triggerLiveData() {
        _liveDara.value = "LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"

//        viewModelScope.launch {
//            _stateFlow.emit("StateFlow")
//        }
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(10) {
                emit("Item $it")
                delay(1300L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }
}