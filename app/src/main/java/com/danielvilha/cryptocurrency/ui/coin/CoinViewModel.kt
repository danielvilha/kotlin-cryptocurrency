package com.danielvilha.cryptocurrency.ui.coin

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.data.CoinDetailDto
import com.danielvilha.cryptocurrency.data.Links
import com.danielvilha.cryptocurrency.data.TeamMember
import com.danielvilha.cryptocurrency.databinding.FragmentCoinBinding
import com.danielvilha.cryptocurrency.network.CryptocurrencyApi
import com.danielvilha.cryptocurrency.ui.home.adapter.CoinStatus
import com.danielvilha.cryptocurrency.ui.home.adapter.toDateString
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

    fun getCoin(context: Context, binding: FragmentCoinBinding, coinId: String) {
        _status.value = CoinStatus.LOADING

        viewModelScope.launch {
            val callback = CryptocurrencyApi.retrofitService.getCoinById(coinId)
            callback.enqueue(object: Callback<CoinDetailDto> {
                @SuppressLint("InflateParams")
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

                    binding.textTeamMembers.text = formatTeamMembers(_coin.value?.team, context.resources)
                    binding.textLinks.text = formatSiteList(_coin.value?.links, context.resources)
                }

                override fun onFailure(call: Call<CoinDetailDto>, t: Throwable) {
                    _status.value = CoinStatus.ERROR
                }
            })
        }
    }

    /**
     * Takes a list of Links and converts and formats it into one string for display.
     *
     * For display in a TextView, we have to supply one string, and styles are per TextView, not
     * applicable per word. So, we build a formatted string using HTML. This is handy, but we will
     * learn a better way of displaying this data in a future lesson.
     *
     * @param   links - List of all Links.
     * @param   resources - Resources object for all the resources defined for our app.
     *
     * @return  Spanned - An interface for text that has formatting attached to it.
     *           See: https://developer.android.com/reference/android/text/Spanned
     */
    fun formatSiteList(links: Links?, resources: Resources): Spanned {
        val sb = StringBuilder()

        sb.apply {
            if (links != null) {
                if (links.explorer.isNotEmpty()) {
                    append("<b>Explorer</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }

                if (links.facebook.isNotEmpty()) {
                    append("<br>")
                    append("<br><b>Facebook</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }

                if (links.reddit.isNotEmpty()) {
                    append("<br>")
                    append("<br><b>Reddit</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }

                if (links.sourceCode.isNotEmpty()) {
                    append("<br>")
                    append("<br><b>Source Code</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }

                if (links.website.isNotEmpty()) {
                    append("<br>")
                    append("<br><b>Website</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }

                if (links.youtube.isNotEmpty()) {
                    append("<br>")
                    append("<br><b>YouTube</b>")
                    links.explorer.forEach {
                        append("<br>\t${it}")
                    }
                }
            } else
                append("<br>${resources.getString(R.string.empty_sites_list)}<br>")
        }


        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    /**
     * Takes a list of TeamMember and converts and formats it into one string for display.
     *
     * For display in a TextView, we have to supply one string, and styles are per TextView, not
     * applicable per word. So, we build a formatted string using HTML. This is handy, but we will
     * learn a better way of displaying this data in a future lesson.
     *
     * @param   teamMembers - List of all TeamMember.
     * @param   resources - Resources object for all the resources defined for our app.
     *
     * @return  Spanned - An interface for text that has formatting attached to it.
     *           See: https://developer.android.com/reference/android/text/Spanned
     */
    fun formatTeamMembers(teamMembers: List<TeamMember>?, resources: Resources): Spanned {
        val sb = StringBuilder()
        sb.apply {
            if (teamMembers == null || teamMembers.isEmpty()) {
                append("<br>${resources.getString(R.string.empty_team_members)}")
            } else {
                teamMembers.forEach {
                    append("<b>${it.name}</b>")
                    append("<br>\t${it.position}<br>")
                }
            }
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}