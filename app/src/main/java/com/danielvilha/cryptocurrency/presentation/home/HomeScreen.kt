package com.danielvilha.cryptocurrency.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.danielvilha.cryptocurrency.MainViewModel
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.theme.CryptocurrencyTheme
import com.danielvilha.cryptocurrency.util.ExcludeFromJacocoGeneratedReport
import com.danielvilha.cryptocurrency.util.LightDarkPreview
import com.danielvilha.cryptocurrency.util.textIsActive
import com.danielvilha.models.Coin
import com.danielvilha.models.CoinStatus

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
        Content(
            PaddingValues(0.dp),
            NavController(LocalContext.current),
            MainViewModel(),
            state
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel,
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
                Content(innerPadding, navController, viewModel, state)
            }
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.list_not_found),
            modifier = Modifier.height(250.dp)
        )
        Text(
            text = stringResource(id = R.string.list_not_found),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CoinsListContent(
    navController: NavController,
    viewModel: MainViewModel,
    coins: List<Coin>
) {
    val selectedActive by viewModel.selectedActive.collectAsState()
    val selectedInactive by viewModel.selectedInactive.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing == true,
        onRefresh = {
            viewModel.refresh()
        }
    )

    val filteredItems = if (selectedActive || selectedInactive) {
        coins.filter {
            it.isActive == selectedActive || !it.isActive == selectedInactive
        }
    } else {
        emptyList()
    }

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = selectedActive,
                    onCheckedChange = { viewModel.selectedActive(it) },
                )
                Text(
                    text = stringResource(id = R.string.active),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Checkbox(
                    checked = selectedInactive,
                    onCheckedChange = { viewModel.selectedInactive(it) },
                )
                Text(
                    text = stringResource(id = R.string.inactive),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed(items = filteredItems) { _, coin ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(route = "CoinScreen/${coin.id}")
                            }
                            .fillMaxWidth(),
                    ) {
                        Row {
                            Box(modifier = Modifier
                                .weight(4f)
                                .fillMaxHeight()
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
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    text = textIsActive(coin.isActive),
                                    textAlign = TextAlign.End,
                                    color = if (coin.isActive)
                                        Color(0xFF1A6B52)
                                    else
                                        Color(0xFFBA1A1A)
                                )
                            }
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing == true,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun Content(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel,
    state: HomeUiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state.status) {
            CoinStatus.LOADING -> {
                LoadingContent()
            }

            CoinStatus.ERROR -> {
                ErrorContent()
            }

            else -> {
                CoinsListContent(
                    navController = navController,
                    viewModel = viewModel,
                    coins = state.coins
                )
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
private class HomeScreenProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState>
        get() = sequenceOf(
            HomeUiState(
                status = CoinStatus.DONE,
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
                        name = "Lido Staked Ether text is to appear so large that it extends beyond a second line for screen testing",
                        rank = 8,
                        symbol = "STETH",
                        type = "coin"
                    )
                )
            ),

            HomeUiState(status = CoinStatus.LOADING),
            HomeUiState(status = CoinStatus.ERROR),
        )
}