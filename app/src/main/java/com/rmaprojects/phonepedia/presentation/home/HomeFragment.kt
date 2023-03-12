package com.rmaprojects.phonepedia.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.phonepedia.R
import com.rmaprojects.phonepedia.databinding.FragmentHomeBinding
import com.rmaprojects.phonepedia.presentation.details.DetailsFragment
import com.rmaprojects.phonepedia.presentation.home.event.HomeUiEvent
import com.rmaprojects.phonepedia.presentation.ui.ProductListAdapter
import com.rmaprojects.phonepedia.utils.triggerSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allProducts.observe(viewLifecycleOwner) { status ->
            var isLoading = true
            var isMessageShown = false
            when (status) {
                is HomeUiEvent.Error -> {
                    triggerSnackbar(binding.root, status.message).show()
                    isLoading = false
                    isMessageShown = true
                    binding.txtEventMessage.text = status.message
                }
                is HomeUiEvent.Loading -> {
                    isLoading = true
                    isMessageShown = false
                }
                is HomeUiEvent.Success -> {
                    binding.rvListProduct.adapter = ProductListAdapter(status.data) { item ->
                        val bundle = bundleOf(
                            DetailsFragment.PRODUCT_ID to item.productId
                        )
                        findNavController().navigate(R.id.action_nav_home_to_nav_detail, bundle)
                    }
                    isLoading = false
                    isMessageShown = false
                }
                else -> {}
            }
            binding.progressCircular.isVisible = isLoading
            binding.rvListProduct.isVisible = !isLoading
            binding.txtEventMessage.isVisible = isMessageShown
        }
    }
}