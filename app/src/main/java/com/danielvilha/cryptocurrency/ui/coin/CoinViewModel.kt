package com.danielvilha.cryptocurrency.ui.coin

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.data.CoinDetailDto
import com.danielvilha.cryptocurrency.data.Links
import com.danielvilha.cryptocurrency.data.Site
import com.danielvilha.cryptocurrency.databinding.FragmentCoinBinding
import com.danielvilha.cryptocurrency.network.CryptocurrencyApi
import com.danielvilha.cryptocurrency.ui.binding.CoinStatus
import com.danielvilha.cryptocurrency.ui.binding.toDateString
import com.danielvilha.cryptocurrency.ui.coin.adapter.LinkAdapter
import com.danielvilha.cryptocurrency.ui.coin.adapter.TeamMemberAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by danielvilha on 18/09/21
 * https://github.com/danielvilha
 */
class CoinViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<CoinStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<CoinStatus>
        get() = _status

    // Internally, I use a MutableLiveData, because I will be updating the CoinDetailDto
    // with new values
    private val _coin = MutableLiveData<CoinDetailDto>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    val coin: LiveData<CoinDetailDto>
        get() = _coin

    fun getCoin(context: Context, binding: FragmentCoinBinding, coinId: String) {
        _status.value = CoinStatus.LOADING

        viewModelScope.launch {
            val callback = CryptocurrencyApi.retrofitService.getCoinById(coinId)
            callback.enqueue(object: Callback<CoinDetailDto> {
                override fun onResponse(
                    call: Call<CoinDetailDto>,
                    response: Response<CoinDetailDto>
                ) {
                    _status.value = CoinStatus.DONE
                    _coin.value = response.body()

                    binding.textRank.text = String.format("${_coin.value?.rank}.")
                    binding.textName.text = _coin.value?.name
                    binding.textSymbol.text = String.format("(${_coin.value?.symbol})")

                    binding.textActive.text = if (_coin.value?.isActive == true)
                        String.format("active")
                    else
                        String.format("inactive")

                    binding.textActive.setTextColor(if (_coin.value?.isActive == true)
                        Color.GREEN
                    else
                        Color.RED
                    )

                    binding.textDescription.text = _coin.value?.description
                    binding.textStartedDate.text = _coin.value?.startedAt?.let { toDateString(it) }
                    binding.textDevelopmentStatus.text = _coin.value?.developmentStatus

                    val inflater = LayoutInflater.from(context)
                    _coin.value?.tags?.forEach {
                        val child = inflater.inflate(R.layout.view_child, null, false) as Chip
                        child.text = it.name
                        binding.chipGroup.addView(child)
                    }

                    if (_coin.value?.team.isNullOrEmpty()) {
                        binding.textTeamMemberEmpty.visibility = View.VISIBLE
                        binding.recyclerTeam.visibility = View.GONE
                    } else {
                        binding.textTeamMemberEmpty.visibility = View.GONE
                        binding.recyclerTeam.visibility = View.VISIBLE

                        binding.recyclerTeam.adapter = TeamMemberAdapter()
                        (binding.recyclerTeam.adapter as TeamMemberAdapter).submitList(_coin.value?.team)
                    }

                    var list = toSiteList(_coin.value?.links)

                    binding.recyclerLinks.adapter = LinkAdapter()
//                    (binding.recyclerLinks.adapter as LinkAdapter).submitList(_coin.value?.links?.explorer)
                }

                override fun onFailure(call: Call<CoinDetailDto>, t: Throwable) {
                    _status.value = CoinStatus.ERROR
                }
            })
        }
    }

    fun toSiteList(links: Links?): List<Site> {
        val sites = mutableListOf<Site>()


        if (links != null) {

//            if (links.explorer.isNotEmpty()) {
//                sites.add(Site("Explorer", links.explorer))
//            }
//
//            if (links.facebook.isNotEmpty()) {
//                sites.add(Site("Facebook", links.facebook))
//            }
//
//            if (links.reddit.isNotEmpty()) {
//                sites.add(Site("Reddit", links.reddit))
//            }
//
//            if (links.sourceCode.isNotEmpty()) {
//                sites.add(Site("Source Code", links.sourceCode))
//            }
//
//            if (links.website.isNotEmpty()) {
//                sites.add(Site("Website", links.website))
//            }
//
//            if (links.youtube.isNotEmpty()) {
//                sites.add(Site("YouTube", links.youtube))
//            }
        }

        return sites
    }
}