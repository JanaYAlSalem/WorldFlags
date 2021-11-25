package com.example.worldflags.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.worldflags.databinding.GridViewItemBinding
import com.example.worldflags.network.FlagPhoto


class PhotoGridAdapter : androidx.recyclerview.widget.ListAdapter<FlagPhoto, PhotoGridAdapter.FlagPhotoViewHolder>(DiffCallback) {

    class FlagPhotoViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoOfFlag : FlagPhoto ) { // FlagPhoto(name: String,flag: String
            binding.photo = photoOfFlag
            binding.executePendingBindings()
        }
    } // end FlagPhotoViewHolder



    companion object DiffCallback : DiffUtil.ItemCallback<FlagPhoto>() {
        override fun areItemsTheSame(oldItem: FlagPhoto, newItem: FlagPhoto): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: FlagPhoto, newItem: FlagPhoto): Boolean {
            return oldItem.name == newItem.name
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlagPhotoViewHolder {
            return FlagPhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
            )
        }


        override fun onBindViewHolder(holder: FlagPhotoViewHolder, position: Int) {
            val marsPhoto = getItem(position)
            holder.bind(marsPhoto)
        }

    } // end PhotoGridAdapter