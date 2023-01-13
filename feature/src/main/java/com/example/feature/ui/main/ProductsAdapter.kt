package com.example.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.base.BaseRecyclerAdapter
import com.example.base.BaseViewHolder
import com.example.feature.databinding.RowProductItemLayoutBinding
import com.example.feature.model.ProductUiModel
import com.google.android.material.shape.CornerFamily

/**
 * Adapter class for RecyclerView
 */
class ProductsAdapter constructor(
    private val clickFunc: ((ProductUiModel?) -> Unit)? = null,
) : BaseRecyclerAdapter<ProductUiModel, RowProductItemLayoutBinding, ProductsAdapter.ProductViewHolder>(
    ProductItemDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = RowProductItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding = binding, click = clickFunc)
    }

    inner class ProductViewHolder(
        private val binding: RowProductItemLayoutBinding,
        private val click: ((ProductUiModel?) -> Unit)? = null,
    ) : BaseViewHolder<ProductUiModel, RowProductItemLayoutBinding>(binding) {

        override fun bind() {
            getRowItem()?.let {
                with(binding) {
                    root.setOnClickListener {
                        click?.invoke(getRowItem())
                    }
                    ivImage.apply {
                        shapeAppearanceModel = binding.ivImage.shapeAppearanceModel
                            .toBuilder()
                            .setTopRightCorner(CornerFamily.ROUNDED, 36f)
                            .setTopLeftCorner(CornerFamily.ROUNDED, 36f)
                            .build()

                        val imageUrl = when {
                            it.imageUrls.isNotEmpty() -> it.imageUrls[0]
                            it.imageUrlsThumbnails.isNotEmpty() -> it.imageUrlsThumbnails[0]
                            else -> ""
                        }
                        Glide.with(this)
                            .load(imageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(this)
                    }

                    tvTitle.text = it.name
                }
            }
        }
    }
}

class ProductItemDiffUtil : DiffUtil.ItemCallback<ProductUiModel>() {
    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
        return oldItem.uid == newItem.uid
    }
}