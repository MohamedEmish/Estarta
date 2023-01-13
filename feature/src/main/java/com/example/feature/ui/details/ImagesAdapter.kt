package com.example.feature.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.base.BaseRecyclerAdapter
import com.example.base.BaseViewHolder
import com.example.feature.databinding.ImagesHolderBinding

class ImagesAdapter :
    BaseRecyclerAdapter<String, ImagesHolderBinding, ImagesAdapter.ImageViewHolder>(
        ImageItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImagesHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding = binding)
    }

    inner class ImageViewHolder(
        private val binding: ImagesHolderBinding,
    ) : BaseViewHolder<String, ImagesHolderBinding>(binding) {
        override fun bind() {
            getRowItem()?.let {
                with(binding) {
                    Glide.with(this.images)
                        .load(it)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(this.images)
                }
            }
        }
    }
}

class ImageItemDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}