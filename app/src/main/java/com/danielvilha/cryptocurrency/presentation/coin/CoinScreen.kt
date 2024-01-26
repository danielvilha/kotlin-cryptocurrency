package com.danielvilha.cryptocurrency.presentation.coin

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.theme.CryptocurrencyTheme
import com.danielvilha.cryptocurrency.util.ExcludeFromJacocoGeneratedReport
import com.danielvilha.cryptocurrency.util.LightDarkPreview
import com.danielvilha.cryptocurrency.util.toDateString
import com.danielvilha.models.CoinDetail
import com.danielvilha.models.CoinStatus
import com.danielvilha.models.Links
import com.danielvilha.models.LinksExtended
import com.danielvilha.models.Stats
import com.danielvilha.models.Tag
import com.danielvilha.models.TeamMember
import com.danielvilha.models.Whitepaper

/**
 * Created by Daniel Ferreira de Lima Vilha 29/11/2023.
 */

private const val TAG = "CoinScreen"

@LightDarkPreview
@Composable
@ExcludeFromJacocoGeneratedReport
private fun Preview(
    @PreviewParameter(CoinsScreenProvider::class)
    state: CoinUiState,
) {
    CryptocurrencyTheme {
        Content(state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinScreen(
    navController: NavController,
    state: CoinUiState
) {
    CryptocurrencyTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = state.coinDetail?.name ?: stringResource(id = R.string.about))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            state.status = CoinStatus.LOADING
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding
                ) {
                    item {
                        Content(state)
                    }
                }
            }
        )
    }
}

@Composable
private fun Content(
    state: CoinUiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when(state.status) {
            CoinStatus.LOADING -> LoadingContent()
            CoinStatus.ERROR -> ErrorContent()
            CoinStatus.DONE -> state.coinDetail?.let {
                CoinDetailContent(detail = it)
            }

            else -> ErrorContent()
        }
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
            text = stringResource(id = R.string.coin_not_found),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun CoinDetailContent(detail: CoinDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        Box(modifier = Modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${detail.rank}. ${detail.name} (${detail.symbol})",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = if (detail.isNew) "New" else "Not new",
                    color = if (detail.isNew)
                        Color(0xFF1A6B52)
                    else
                        Color(0xFFBA1A1A),
                    fontSize = 20.sp,
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        TitleValue(value = detail.description)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            TitleName(title = stringResource(id = R.string.started_date))
            Spacer(modifier = Modifier.padding(4.dp))
            TitleValue(value = toDateString(detail.startedAt))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            TitleName(title = stringResource(id = R.string.development_status))
            Spacer(modifier = Modifier.padding(4.dp))
            TitleValue(value = detail.developmentStatus)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            TitleName(title = stringResource(id = R.string.open_source))
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${detail.openSource}",
                color = if (detail.openSource)
                    Color(0xFF1A6B52)
                else
                    Color(0xFFBA1A1A)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        SuggestionChipLayout(detail.tags)
        Spacer(modifier = Modifier.height(16.dp))
        TeamMemberList(teams = detail.team)
        Spacer(modifier = Modifier.height(16.dp))
        LinkList(links = detail.links)
    }
}

@Composable
private fun TitleName(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun TitleValue(value: String?) {
    Text(
        text = value ?: stringResource(id = R.string.no_information),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun SuggestionChipLayout(tags: List<Tag>?) {
    Column {
        TitleName(title = stringResource(id = R.string.tags))
        Spacer(modifier = Modifier.height(4.dp))

        tags?.let { chipList ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(chipList) { tag ->
                    SuggestionChip(
                        onClick = { },
                        label = {
                            Text(text = tag.name, color = Color(0xFF1A6B52))
                        },
                        modifier = Modifier.padding(4.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color.Transparent
                        ),
                        border = SuggestionChipDefaults.suggestionChipBorder(
                            borderWidth = 1.dp,
                            borderColor = Color(0xFF1A6B52)
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun TeamMemberList(teams: List<TeamMember>?) {
    TitleName(title = stringResource(id = R.string.team_members))
    teams?.forEach {
        Text(
            text = it.position,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = it.name,
            modifier = Modifier.padding(start = 24.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun LinkList(links: Links?) {
    val context = LocalContext.current
    val linkTypes = listOf(
        "Website" to links?.website,
        "YouTube" to links?.youtube,
        "Explorer" to links?.explorer,
        "Facebook" to links?.facebook,
        "Reddit" to links?.reddit,
        "Source Code" to links?.sourceCode
    )
    Column {
        TitleName(title = stringResource(id = R.string.links))

        linkTypes.forEach { (linkType, links) ->
            if (!links.isNullOrEmpty()) {
                Text(
                    text = "$linkType:",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                links.forEach { link ->
                    Text(
                        text = link,
                        color = Color.Blue,
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                                val canResolveIntent =
                                    intent.resolveActivity(context.packageManager) != null

                                if (canResolveIntent) {
                                    context.startActivity(intent)
                                } else {
                                    // There is no browser available
                                    Log.d(
                                        TAG,
                                        "${context.getString(R.string.link_list)} $link"
                                    )
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.the_intent_cannot_be_resolved),
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                }
                            }
                            .padding(start = 24.dp)
                    )
                }
            }
        }
    }
}

@ExcludeFromJacocoGeneratedReport
private class CoinsScreenProvider : PreviewParameterProvider<CoinUiState> {
    override val values: Sequence<CoinUiState>
        get() = sequenceOf(
            CoinUiState(
                status = CoinStatus.DONE,
                coinDetail = CoinDetail(
                    description = "Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.",
                    developmentStatus = "Working product",
                    firstDataAt = "03 Jan, 2009",
                    hardwareWallet = true,
                    hashAlgorithm = "SHA256",
                    id = "btc-bitcoin",
                    isActive = true,
                    isNew = false,
                    lastDataAt = "2010-07-17T00:00:00Z",
                    links = Links(
                        website = listOf(
                            "https://bitcoin.org/"
                        )
                    ),
                    linksExtended = listOf(LinksExtended(stats = Stats(1, 1, 1, 1), type = "", url = "")),
                    message = "",
                    name = "Bitcoin",
                    openSource = true,
                    orgStructure = "Decentralized",
                    proofType = "Proof of Work",
                    rank = 1,
                    startedAt = "2009-01-03T00:00:00Z",
                    symbol = "BTC",
                    tags = listOf(
                        Tag(
                            coinCounter = 10,
                            icoCounter = 0,
                            id = "segwit",
                            name = "Segwit",
                        )
                    ),
                    team = listOf(
                        TeamMember(
                            id = "satoshi-nakamoto",
                            name = "Satoshi Nakamoto",
                            position = "Founder"
                        )
                    ),
                    type = "coin",
                    whitePaper = Whitepaper(
                        link = "https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf",
                        thumbnail = "https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg"
                    )
                )
            ),

            CoinUiState(status = CoinStatus.LOADING),
            CoinUiState(status = CoinStatus.ERROR)
        )
}
