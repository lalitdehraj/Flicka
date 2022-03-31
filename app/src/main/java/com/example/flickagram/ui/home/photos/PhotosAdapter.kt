package com.example.flickagram.ui.home.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickagram.databinding.ListItemPhotoBinding
import com.example.flickagram.domain.model.Photo

class PhotosAdapter(private val onItemClick:(position:Int)->Unit) : ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(DiffUtilsCallback) {
    object DiffUtilsCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem

    }

    inner class PhotoViewHolder(private val binding : ListItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo : Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
        init {
          binding.itemContainer.setOnClickListener{
              onItemClick(adapterPosition)
          }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        return PhotoViewHolder(ListItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}