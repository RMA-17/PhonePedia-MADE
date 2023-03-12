package com.rmaprojects.favorite.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.favorite.R
import com.rmaprojects.favorite.databinding.FragmentFavoriteBinding
import com.rmaprojects.favorite.di.DaggerFavoriteComponent
import com.rmaprojects.phonepedia.di.FavoriteModuleDependencies
import com.rmaprojects.phonepedia.presentation.details.DetailsFragment
import com.rmaprojects.phonepedia.presentation.ui.ProductListAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireActivity().applicationContext)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().application,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favProductList.observe(viewLifecycleOwner) { favList ->
            if (favList.isNullOrEmpty()) {
                binding.txtEmptyFav.isVisible = true
                return@observe
            }
            binding.txtEmptyFav.isVisible = false
            binding.rvFavoriteList.adapter = ProductListAdapter(favList) { favorite ->
                val bundle = bundleOf(DetailsFragment.PRODUCT_ID to favorite.productId)
                findNavController().navigate(R.id.action_nav_favorite_to_nav_detail, bundle)
            }
        }
    }
}