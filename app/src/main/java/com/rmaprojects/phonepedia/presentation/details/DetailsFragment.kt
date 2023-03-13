package com.rmaprojects.phonepedia.presentation.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.core.data.source.remote.response.ResponseStatus
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.phonepedia.R
import com.rmaprojects.phonepedia.databinding.FragmentDetailsBinding
import com.rmaprojects.phonepedia.presentation.details.event.DetailProductUiEvent
import com.rmaprojects.phonepedia.presentation.ui.ProductDetailViewPagerAdapter
import com.rmaprojects.phonepedia.utils.triggerSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarProductModel.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.viewPager.adapter = ProductDetailViewPagerAdapter(null)

        viewModel.isProductInsertedIntoFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            else binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        viewModel.uiEventFlow.observe(viewLifecycleOwner) { event ->
            when (event) {
                is DetailProductUiEvent.DeletedFromFavorite -> {
                    triggerSnackbar(
                        binding.root,
                        "Deleted from favorite"
                    ).show()
                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                }
                is DetailProductUiEvent.Error -> {
                    triggerSnackbar(
                        binding.root,
                        event.message
                    ).show()
                }
                is DetailProductUiEvent.SavedToFavorite -> {
                    triggerSnackbar(
                        binding.root,
                        "Saved to favorite"
                    ).show()
                    binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                }
            }
        }

        viewModel.getProductDetail().observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Error -> {
                    setLoading(false)
                    binding.txtErr.text = status.message
                    triggerSnackbar(
                        binding.root,
                        status.message
                    ).show()
                    binding.productInfo.isVisible = false
                }
                is ResponseStatus.Loading -> {
                    setLoading(true)
                }
                is ResponseStatus.Success -> {
                    setLoading(false)
                    binding.viewPager.adapter =
                        ProductDetailViewPagerAdapter(status.data.productImages)
                    setProductDetailText(status.data)
                    setExpandable()
                    binding.fabFavorite.setOnClickListener {
                        viewModel.addToFavorite(status.data)
                    }
                    binding.toolbarProductModel.title = status.data.productModel
                }
            }
        }
    }

    private fun setProductDetailText(item: ProductItemDetail) {

        binding.productInfo.text = item.productModel
        binding.productDate.text =
            requireContext().getString(R.string.product_date, item.productDate)
        binding.txtDesign.text = requireContext().getString(
            R.string.product_design,
            item.designHeight,
            item.designWidth,
            item.designWeight,
            item.designThickness,
            item.designIpRating,
            item.designColor,
        )
        binding.txtCamera.text = requireContext().getString(
            R.string.product_camera,
            item.cameraBack,
            item.cameraFront
        )
        binding.txtDisplay.text = requireContext().getString(
            R.string.product_display,
            item.displayRes,
            item.displayRefreshRate,
            item.displayPixelDensity
        )
        binding.txtSpecs.text = requireContext().getString(
            R.string.product_specs,
            item.specsOS,
            item.specsProcessor,
            item.specRAM,
            item.specStorage
        )
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.productInfo.text = getString(R.string.txt_loading)
            binding.containerSpecs.isVisible = false
        } else {
            binding.containerSpecs.isVisible = true
        }
    }

    private fun setExpandable() {
        var isExpCameraOpened = false
        var isExpDesignOpened = false
        var isExpDisplayOpened = false
        var isExpSpecsOpened = false

        binding.expDesign.setOnClickListener {
            if (isExpDesignOpened) {
                isExpDesignOpened = false
                binding.expDesignLayout.collapse(true)
                binding.expDesign.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_down_24,
                    0
                )
            } else {
                isExpDesignOpened = true
                binding.expDesignLayout.expand(true)
                binding.expDesign.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_up_24,
                    0
                )
            }
        }
        binding.expCamera.setOnClickListener {
            if (isExpCameraOpened) {
                isExpCameraOpened = false
                binding.expCameraLayout.collapse(true)
                binding.expCamera.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_down_24,
                    0
                )
            } else {
                isExpCameraOpened = true
                binding.expCameraLayout.expand(true)
                binding.expCamera.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_up_24,
                    0
                )
            }
        }
        binding.expDisplay.setOnClickListener {
            if (isExpDisplayOpened) {
                isExpDisplayOpened = false
                binding.expDisplayLayout.collapse(true)
                binding.expDisplay.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_down_24,
                    0
                )
            } else {
                isExpDisplayOpened = true
                binding.expDisplayLayout.expand(true)
                binding.expDisplay.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_up_24,
                    0
                )
            }
        }
        binding.expSpecs.setOnClickListener {
            if (isExpSpecsOpened) {
                isExpSpecsOpened = false
                binding.expSpecsLayout.collapse(true)
                binding.expSpecs.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_down_24,
                    0
                )
            } else {
                isExpSpecsOpened = true
                binding.expSpecsLayout.expand(true)
                binding.expSpecs.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_up_24,
                    0
                )
            }
        }
    }

    companion object {
        const val PRODUCT_ID = "productId"
    }
}