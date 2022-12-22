package com.pratik.imgurdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pratik.imgurdemo.R
import com.pratik.imgurdemo.databinding.ItemImageGridBinding
import com.pratik.imgurdemo.databinding.ItemImageListBinding
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ImageDTO
import com.pratik.imgurdemo.utility.ImageListener

private class DiffCallback : DiffUtil.ItemCallback<ImageDTO>() {

    override fun areItemsTheSame(oldItem: ImageDTO, newItem: ImageDTO) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageDTO, newItem: ImageDTO) =
        oldItem == newItem
}

class ImageAdapter:
    ListAdapter<ImageDTO, ImageAdapter.ImageViewHolder>(DiffCallback()) {
    companion object{
        const val LIST_MODE = 0
        const val GRID_MODE = 1
    }
    lateinit var listener: ImageListener
    private var mode = Companion.LIST_MODE

    fun setMode(newMode: Int = LIST_MODE){
        this.mode = newMode
    }
    class ImageViewHolder(var view: ViewBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = if(mode == LIST_MODE)
            DataBindingUtil.inflate<ItemImageListBinding>(inflater, R.layout.item_image_list, parent, false)
        else DataBindingUtil.inflate<ItemImageGridBinding>(inflater, R.layout.item_image_grid, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val data = getItem(position)
        if(mode == LIST_MODE) (holder.view as ItemImageListBinding).image = data
        else (holder.view as ItemImageGridBinding).image = data

        if (::listener.isInitialized) {
            holder.itemView.setOnClickListener {
                listener.onItemClick(position, data)
            }
        }
    }
}

