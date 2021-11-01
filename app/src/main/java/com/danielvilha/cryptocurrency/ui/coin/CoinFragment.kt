package com.danielvilha.cryptocurrency.ui.coin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielvilha.cryptocurrency.databinding.FragmentCoinBinding

/**
 * Created by danielvilha on 18/09/21
 * https://github.com/danielvilha
 */
class CoinFragment : Fragment() {

    private var binding: FragmentCoinBinding? = null

    /**
     * Lazily initialize our [CoinViewModelFactory]
     */
    private val viewModelFactory: CoinViewModelFactory by lazy {
        CoinViewModelFactory()
    }

    /**
     * Lazily initialize our [CoinViewModel]
     */
    private val coinViewModel: CoinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CoinViewModel::class.java)
    }

    private var coinId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        val coinBinding = FragmentCoinBinding.inflate(inflater, container, false)
        binding = coinBinding

        // I get this bundle from the last argument
        coinId =  arguments?.getString("id")

        coinViewModel.getCoin(requireContext(), coinBinding, coinId!!)

        return coinBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            viewModel = coinViewModel
        }
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}