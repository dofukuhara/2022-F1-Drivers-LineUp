package com.fukuhara.douglas.lib.common.services

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageLoader {
    fun loadWithSize(url: String, width: Int, height: Int, imageView: ImageView)
}

internal class PicassoImageLoader : ImageLoader {
    override fun loadWithSize(url: String, width: Int, height: Int, imageView: ImageView) {
        Picasso.get().load(url).resize(width, height).into(imageView)
    }
}
