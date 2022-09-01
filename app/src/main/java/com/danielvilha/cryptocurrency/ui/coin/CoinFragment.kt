package com.danielvilha.cryptocurrency.ui.coin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.danielvilha.cryptocurrency.databinding.FragmentCoinBinding

/**
 * Created by danielvilha on 18/09/21
 * https://github.com/danielvilha
 */
class CoinFragment : Fragment() {

    private var binding: FragmentCoinBinding? = null

    /**
     * Lazily initialize our [CoinViewModel]
     */
    private val coinViewModel: CoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCoinBinding.inflate(inflater, container, false)
        .apply {
            binding = this
            // I get this bundle from the last argument
            val coinId =  arguments?.getString("id")
            coinViewModel.getCoin(requireContext(), binding!!, coinId!!)
    }.root

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