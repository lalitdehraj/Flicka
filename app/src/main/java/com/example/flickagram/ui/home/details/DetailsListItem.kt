package com.example.flickagram.ui.home.details

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentDetailsListItemBinding
import com.example.flickagram.domain.model.Photo
import com.example.flickagram.utils.shareUrl
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class DetailsListItem : Fragment(R.layout.fragment_details_list_item) {
    private lateinit var binding : FragmentDetailsListItemBinding
    private lateinit var photo: Photo
    private var imageFilePath: String? = null

    companion object {
        fun getInstance(photo: Photo): DetailsListItem {
            val fragment = DetailsListItem()
            fragment.photo = photo
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDetailsListItemBinding.bind(view)
        binding.lifecycleOwner= this
        binding.photoItem=photo
        loadImage()
        binding.shareUrl.setOnClickListener {
            requireActivity().shareUrl(photo.url ?: "")
        }
        binding.shareImage.setOnClickListener { imageFilePath?.let { shareImage() } }
    }

    private fun loadImage() {
        Glide.with(binding.mainPreview)
            .asBitmap()
            .load(photo.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.img)
            .error(R.drawable.img)
            .into(object : BitmapImageViewTarget(binding.mainPreview) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    storeImageInCache(resource)
                }
            })
    }

    private fun storeImageInCache(bitmap: Bitmap) {
        try {
            val cacheDir = requireActivity().cacheDir
            var path = cacheDir.absolutePath + "/photos/"
            val photoDir = File(path)
            if (!photoDir.exists()) photoDir.mkdirs()

            path += "${photo.id}.jpg"
            val imageFile = File(path)

            val stream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()

            imageFilePath = imageFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getContentTypeImagePath(): Uri {
        return FileProvider.getUriForFile(
            requireContext(),
            "com.example.flickagram.fileprovider",
            File(imageFilePath!!)
        )
    }

    private fun shareImage() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, getContentTypeImagePath())
        requireContext().startActivity(intent)
    }


}