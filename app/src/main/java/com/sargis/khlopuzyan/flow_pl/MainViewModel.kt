package com.sargis.khlopuzyan.flow_pl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by Sargis Khlopuzyan on 3/20/2022.
 */
class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(500L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {

//        // The same as TODO ***1
//        countDownFlow.onEach {
//            println(it)
//        }.launchIn(viewModelScope)

//        viewModelScope.launch {
//
////            // TODO ***1
////            countDownFlow.collect {
////                log(it)
////            }
//
//            /**
//             * Flow Basics - The Ultimate Guide to Kotlin Flows (Part 1)
//             * */
////            countDownFlow
////                .filter { time ->
////                    time % 2 == 0
////                }
////                .map { time ->
////                    time * time
////                }
////                .onEach { time ->
////                    log(time)
////                }
////                .collect { time ->
////                    log("The current time is $time")
////                }
//
//            /**
//             * Flow Operators - The Ultimate Guide to Kotlin Flows (Part 2)
//             * */
//
//            // count
//            val count = countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    log(time)
//                }
//                .count {
//                    it % 2 == 0
//                }
//
//            log("The count is $count'")
//
//            // reduce
//            val reduceResult = countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    log(time)
//                }
//                .reduce { accumulator, value ->
//                    accumulator + value
//                }
//
//            log("The count is $reduceResult'")
//
//            // fold
//            val foldResult = countDownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach { time ->
//                    log(time)
//                }
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
//
//            log("The count is $foldResult'")
//        }


//        val flow1 = flow {
//            emit(1)
//            delay(500L)
//            emit(2)
//        }
//
//        viewModelScope.launch {
//            flow1.flatMapConcat { value ->
//                flow {
//                    emit(value + 1)
//                    delay(500L)
//                    emit(value + 2)
//                }
//            }.collect { value ->
//                log("The value is $value")
//            }
//        }

//        val flow1 = (1..5).asFlow()
//        viewModelScope.launch {
//            flow1.flatMapLatest { value ->
////            flow1.flatMapConcat { value ->
////            flow1.flatMapMerge { value ->
//                getRecipeById(value)
//            }.collect { value ->
//                println("The value is $value")
//            }
//        }

        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main dish")
            delay(100L)
            emit("Dessert")
        }

        viewModelScope.launch {
//            flow.onEach {
//                log("FLOW: $it is delivered")
//            }
//                .buffer()
//                .collect {
//                    log("FLOW: Now eating $it")
//                    delay(1500L)
//                    log("FLOW: Finish eating $it")
//                }

//            flow.onEach {
//                log("FLOW: $it is delivered")
//            }
//                .conflate()
//                .collect {
//                    log("FLOW: Now eating $it")
//                    delay(1500L)
//                    log("FLOW: Finish eating $it")
//                }

            flow.onEach {
                log("FLOW: $it is delivered")
            }
                .collectLatest {
                    log("FLOW: Now eating $it")
                    delay(1500L)
                    log("FLOW: Finish eating $it")
                }

        }

    }
}

fun log(msg: String) {
    println("___ $msg")
}

fun log(msg: Int) {
    println("___ $msg")
}