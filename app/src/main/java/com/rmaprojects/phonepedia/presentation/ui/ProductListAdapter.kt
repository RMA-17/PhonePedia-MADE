package com.rmaprojects.phonepedia.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.phonepedia.databinding.ItemProductListBinding
import com.rmaprojects.phonepedia.presentation.ui.ProductListAdapter.ProductListViewHolder

class ProductListAdapter<T>(
    private val productList: List<T>,
    private val onClick: (T) -> Unit
) : RecyclerView.Adapter<ProductListViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder<T> {
        val binding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder<T>, position: Int) {
        val productItem = productList[position]
        holder.bind(productItem)
    }

    class ProductListViewHolder<T>(
        private val binding: ItemProductListBinding,
        val clickListener: (T) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem: T) {
            binding.root.setOnClickListener { clickListener(productItem) }
            if (productItem is ProductItemList) {
                with(binding) {
                    imgProduct.load(productItem.image)
                    categoryProduct.text = productItem.category
                    modelProduct.text = productItem.productModel
                }
                return
            }
            if (productItem is Favorite) {
                with(binding) {
                    imgProduct.load(productItem.productImage)
                    categoryProduct.text = productItem.productCategory
                    modelProduct.text = productItem.productName
                }
                return
            }
            throw Exception("This Adapter must be used for Product Listing only")
        }
    }
}