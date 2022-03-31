package com.example.flickagram.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.flickagram.R

@BindingAdapter("loadImage")
fun loadImageInImageView(imageView: ImageView, url : String?) {
    if (url!= null)
        Glide.with(imageView).load(url).centerCrop().error(R.drawable.img).into(imageView)
    else
        Glide.with(imageView).load(R.drawable.img).into(imageView)

}
