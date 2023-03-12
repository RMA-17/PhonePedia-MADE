package com.rmaprojects.phonepedia.presentation.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.phonepedia.R
import com.rmaprojects.phonepedia.databinding.FragmentSearchBinding
import com.rmaprojects.phonepedia.presentation.details.DetailsFragment
import com.rmaprojects.phonepedia.presentation.search.event.FilterCategory
import com.rmaprojects.phonepedia.presentation.search.event.SearchEvent
import com.rmaprojects.phonepedia.presentation.search.event.SearchUiEvent
import com.rmaprojects.phonepedia.presentation.ui.ProductListAdapter
import com.rmaprojects.phonepedia.utils.triggerSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiEvent.observe(viewLifecycleOwner) { event ->
            var isLoading = true
            var isMessageShown = false
            when (event) {
                is SearchUiEvent.Error -> {
                    isLoading = false
                    isMessageShown = true
                    binding.txtEventMessage.text = event.message
                    triggerSnackbar(binding.root, event.message).show()
                }
                is SearchUiEvent.Loading -> {
                    isLoading = true
                    isMessageShown = false
                }
                is SearchUiEvent.Success -> {
                    isLoading = false
                    isMessageShown = false
                    viewModel.productList.observe(viewLifecycleOwner) { itemList ->
                        binding.rvListProduct.adapter = ProductListAdapter(itemList) { item ->
                            val bundle = bundleOf(
                                DetailsFragment.PRODUCT_ID to item.productId
                            )
                            findNavController().navigate(R.id.action_nav_search_to_nav_detail, bundle)
                        }
                    }
                }
                is SearchUiEvent.EmptyQuery -> {
                    isLoading = false
                    isMessageShown = true
                    binding.txtEventMessage.text = getString(R.string.txt_query_empty)
                    binding.rvListProduct.isVisible = false
                }
            }
            binding.progressCircular.isVisible = isLoading
            binding.rvListProduct.isVisible = !isLoading
            binding.txtEventMessage.isVisible = isMessageShown
        }

        binding.inputQuery.editText?.apply {
            addTextChangedListener { text ->
                viewModel.onEvent(SearchEvent.TypingQueries(text.toString()))
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.onEvent(SearchEvent.BeginSearch)
                }
                true
            }
        }

        binding.searchToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setupChips()
    }

    private fun setupChips() {
        binding.chipCategoryGroup.setOnCheckedStateChangeListener { _, _ ->
            val checkedCategory = when {
                binding.chipSmartphone.isChecked -> 1
                binding.chipTablet.isChecked -> 2
                binding.chipLaptop.isChecked -> 3
                binding.chipSmartwatch.isChecked -> 4
                else -> throw Exception("Unknown Filter")
            }
            val category = when (checkedCategory) {
                1 -> FilterCategory.SMARTPHONE
                2 -> FilterCategory.TABLETS
                3 -> FilterCategory.LAPTOPS
                4 -> FilterCategory.SMARTWATCHES
                else -> throw Exception("Unknown Filter")
            }
            viewModel.onEvent(SearchEvent.ChangeCategory(category))
        }
    }
}