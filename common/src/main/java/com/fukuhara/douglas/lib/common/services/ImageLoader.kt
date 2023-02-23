package com.fukuhara.douglas.lib.common.services

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageLoader {
    fun load(url: String, imageView: ImageView)
    fun loadWithSize(url: String, width: Int, height: Int, imageView: ImageView)
}

internal class PicassoImageLoader : ImageLoader {

    override fun load(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }
    override fun loadWithSize(url: String, width: Int, height: Int, imageView: ImageView) {
        Picasso.get().load(url).resize(width, height).into(imageView)
    }
}
