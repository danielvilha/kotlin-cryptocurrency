package com.danielvilha.cryptocurrency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.danielvilha.models.CoinDetail
import com.danielvilha.models.CoinStatus
import createCoinDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

/**
 * Created by Daniel Ferreira de Lima Vilha 21/01/2024.
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: MainViewModel
    private val coinId = "btc-bitcoin"

    @Before
    fun setUp() {
        viewModel = MainViewModel()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetCoinsList() {
        val viewModel = MainViewModel()
        val observer = Observer<CoinStatus> {}

        viewModel.status.observeForever(observer)

        viewModel.getCoinsList()

        assertEquals(CoinStatus.LOADING, viewModel.status.value)
    }

    @Test
    fun testRefresh() {
        val viewModel = MainViewModel()
        val observer = Observer<CoinStatus> {}

        viewModel.status.observeForever(observer)

        viewModel.refresh()

        assertEquals(CoinStatus.LOADING, viewModel.status.value)
    }

    @Test
    fun testGetCoinByIdSuccess() {
        val coinDetail = createCoinDetails()
        Response.success(coinDetail)

        viewModel.getCoinById(coinId)

        val newState = viewModel.state.value.copy(
            status = CoinStatus.DONE,
            coinDetail = coinDetail
        )
        viewModel.updateState(newState)

        assert(viewModel.state.value.status == CoinStatus.DONE)
        assert(viewModel.state.value.coinDetail == coinDetail)
    }

    @Test
    fun testGetCoinByIdFailure() {
        Response.error<CoinDetail>(
            404,
            ResponseBody.create(null, "Not Found")
        )

        viewModel.getCoinById(coinId)

        val newState = viewModel.state.value.copy(
            status = CoinStatus.ERROR,
        )
        viewModel.updateState(newState)

        assert(viewModel.state.value.status == CoinStatus.ERROR)
    }
}