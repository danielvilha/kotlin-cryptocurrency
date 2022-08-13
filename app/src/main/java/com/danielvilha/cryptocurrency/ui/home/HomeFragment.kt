package com.danielvilha.cryptocurrency.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.danielvilha.cryptocurrency.R
import com.danielvilha.cryptocurrency.databinding.FragmentHomeBinding
import com.danielvilha.cryptocurrency.ui.home.adapter.CryptocurrencyAdapter

/**
 * Created by danielvilha on 16/09/21
 * https://github.com/danielvilha
 *
 * This [Fragment] will show the list of the cryptocurrency.
 */
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    /**
     * Lazily initialize our [HomeViewModelFactory]
     */
    private val viewModelFactory: HomeViewModelFactory by lazy {
        HomeViewModelFactory()
    }

    /**
     * Lazily initialize our [HomeViewModel]
     */
    private val homeViewModel: HomeViewModel by lazy {
         ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        val homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = homeBinding

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            viewModel = homeViewModel

            recycler.adapter = CryptocurrencyAdapter(CryptocurrencyAdapter.OnClickListener {
                val bundle = bundleOf("id" to it)
                findNavController().navigate(R.id.action_homeFragment_to_coinFragment, bundle)
            })

            swipe.setOnRefreshListener {
                swipe.isRefreshing = false
            }
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