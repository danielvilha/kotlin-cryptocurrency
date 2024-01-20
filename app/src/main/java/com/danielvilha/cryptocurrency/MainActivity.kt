package com.danielvilha.cryptocurrency

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.danielvilha.cryptocurrency.presentation.coin.CoinScreen
import com.danielvilha.cryptocurrency.presentation.coin.CoinUiState
import com.danielvilha.cryptocurrency.presentation.home.HomeScreen
import com.danielvilha.cryptocurrency.presentation.home.HomeUiState
import com.danielvilha.cryptocurrency.presentation.splashscreen.SplashScreen
import com.danielvilha.cryptocurrency.theme.CryptocurrencyTheme
import com.danielvilha.cryptocurrency.util.Actions

private val TAG = MainActivity::class.simpleName

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val valid = viewModel.valid.collectAsState()

            CryptocurrencyTheme {
                NavHost(
                    navController = navController,
                    startDestination = "SplashScreen",
                ) {
                    composable(route = "SplashScreen") {
                        SplashScreen(
                            valid = valid,
                            onStart = { viewModel.trackSplashScreenStarted() },
                            onSplashEndedValid = {
                                navController.navigate("HomeScreen") {
                                    popUpTo("SplashScreen") { inclusive = true }
                                }
                            }
                        ) {
                            Log.d(TAG, ">>>> onCreate: $it")
                            Toast.makeText(
                                context,
                                "Something went wrong..",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    composable(route = "HomeScreen") {
                        HomeScreen(
                            navController,
                            state = HomeUiState(
                                coins = viewModel.coins.value?.toMutableList() ?: mutableListOf()
                            ),
                        )
                    }
                    composable(
                        route = "CoinScreen/{coin}",
                        arguments = listOf(
                            navArgument(name = "coin") {
                                type = NavType.StringType
                            }
                        )
                    ) { arguments ->
                        val coinArgument = arguments.arguments?.getString("coin") ?: ""
                        val coin = remember { viewModel.state.value }
                        viewModel.getCoinById(coinArgument)
                        CoinScreen(
                            navController,
                            state = CoinUiState(
                                coinDetail = coin.coinDetail
                            ),
                        )
                    }
                }
            }
        }
    }
}
