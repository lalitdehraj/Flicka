package com.example.flickagram.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImageInImageView(imageView: ImageView, url : String?) {
    Glide.with(imageView).load(url).into(imageView)
}