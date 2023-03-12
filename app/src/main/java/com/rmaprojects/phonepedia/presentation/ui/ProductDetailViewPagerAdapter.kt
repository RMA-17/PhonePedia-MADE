package com.rmaprojects.phonepedia.presentation.ui

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rmaprojects.phonepedia.R

class ProductDetailViewPagerAdapter(
    private val productImage: List<String>?
): RecyclerView.Adapter<ProductDetailViewPagerAdapter.ProductDetailViewpagerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductDetailViewpagerViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return ProductDetailViewpagerViewHolder(imageView)
    }

    override fun getItemCount(): Int {
        return productImage?.size ?: 1
    }

    override fun onBindViewHolder(holder: ProductDetailViewpagerViewHolder, position: Int) {
        val image = productImage?.get(position)
        holder.bind(image)
    }

    class ProductDetailViewpagerViewHolder(
        private val imageView: ImageView
    ): RecyclerView.ViewHolder(imageView.rootView) {
        fun bind(image: String?) {
            imageView.load(image) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
        }
    }
}