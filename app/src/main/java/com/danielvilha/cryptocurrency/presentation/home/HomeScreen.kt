package com.danielvilha.cryptocurrency.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.theme.CryptocurrencyTheme
import com.danielvilha.cryptocurrency.util.ExcludeFromJacocoGeneratedReport
import com.danielvilha.cryptocurrency.util.LightDarkPreview
import com.danielvilha.cryptocurrency.util.textIsActive
import com.danielvilha.models.Coin

/**
 * Created by Daniel Ferreira de Lima Vilha 25/11/2023.
 */
@Composable
@LightDarkPreview
@ExcludeFromJacocoGeneratedReport
private fun Preview(
    @PreviewParameter(HomeScreenProvider::class)
    state: HomeUiState,
) {
    CryptocurrencyTheme {
        Content(PaddingValues(0.dp), NavController(LocalContext.current), state)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    navController: NavController,
    state: HomeUiState,
) {
    CryptocurrencyTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = {/* Do Something*/ }) {
                            Icon(Icons.Filled.Menu, null)
                        }
                    }, actions = {
                        IconButton(onClick = {
                            /* Do Something*/
                        }) {
                            Icon(Icons.Filled.MoreVert, null)
                        }
                    }
                )
            },
            content = { innerPadding ->
                Content(innerPadding, navController, state)
            }
        )
    }
}

@Composable
private fun Content(
    innerPadding: PaddingValues,
    navController: NavController,
    state: HomeUiState,
) {
    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            itemsIndexed(state.coins) {_, coin ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(route = "CoinScreen/${coin.id}")
                        }
                        .fillMaxWidth(),
                ) {
                    Row {
                        Text(
                            text = "${coin.rank}.",
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = coin.name,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "(${coin.symbol})",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight())
                        Text(
                            text = textIsActive(coin.isActive),
                            color = if (coin.isActive)
                                Color(0xFF1A6B52)
                            else
                                Color(0xFFBA1A1A),
                        )
                    }
                }
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
private class HomeScreenProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState>
        get() = sequenceOf(
            HomeUiState(
                mutableListOf(
                    Coin(
                        id = "btc-bitcoin",
                        isActive = true,
                        isNew = true,
                        name = "Bitcoin",
                        rank = 1,
                        symbol = "BTC",
                        type = "coin"
                    ),
                    Coin(
                        id = "eth-ethereum",
                        isActive = true,
                        isNew = true,
                        name = "Ethereum",
                        rank = 2,
                        symbol = "ETH",
                        type = "coin"
                    ),
                    Coin(
                        id = "usdt-tether",
                        isActive = false,
                        isNew = false,
                        name = "Tether",
                        rank = 3,
                        symbol = "USD",
                        type = "token"
                    ),
                    Coin(
                        id = "btc-bitcoin",
                        isActive = true,
                        isNew = true,
                        name = "Binance Coin",
                        rank = 4,
                        symbol = "BNB",
                        type = "coin"
                    ),
                    Coin(
                        id = "eth-ethereum",
                        isActive = true,
                        isNew = true,
                        name = "Solana",
                        rank = 5,
                        symbol = "SOL",
                        type = "coin"
                    ),
                    Coin(
                        id = "usdt-tether",
                        isActive = false,
                        isNew = false,
                        name = "XRP",
                        rank = 6,
                        symbol = "XRP",
                        type = "token"
                    ),
                    Coin(
                        id = "btc-bitcoin",
                        isActive = true,
                        isNew = true,
                        name = "USDC",
                        rank = 7,
                        symbol = "USDC",
                        type = "coin"
                    ),
                    Coin(
                        id = "eth-ethereum",
                        isActive = true,
                        isNew = true,
                        name = "Lido Staked Ether",
                        rank = 8,
                        symbol = "STETH",
                        type = "coin"
                    )
                )
            )
        )
}