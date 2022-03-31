package com.example.flickagram.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.safeNavigate(resId: Int, args: Bundle? = null) {
    findNavController().currentDestination?.getAction(resId)?.run {
        findNavController().navigate(resId, args)
    }
}

fun Activity.shareUrl(url: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }

    startActivity(Intent.createChooser(intent, "Share Image Link"))
}