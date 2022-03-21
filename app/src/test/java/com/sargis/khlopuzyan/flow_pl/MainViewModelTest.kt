package com.sargis.khlopuzyan.flow_pl

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

/**
 * Created by Sargis Khlopuzyan on 3/21/2022.
 */
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        viewModel = MainViewModel(testDispatchers)
    }

    @Test
    fun `countDownFlow, properly counts down from 5 to 0`() = runBlocking {
        viewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                testDispatchers.testDispatcher.advanceTimeBy(1000L)
                val emission = awaitItem()
                assertThat(emission).isEqualTo(i)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `squareNumber, number properly squared`() = runBlocking {

        val job = launch {
            viewModel.shareFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(9)
            }
        }

//        viewModel.shareFlow.test {
//            val emission = awaitItem()
//            assertThat(emission).isEqualTo(9)
//        }

        viewModel.squareNumber(3)

        job.cancelAndJoin()
    }
}