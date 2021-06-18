package com.example.shemajamebeli4redo.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shemajamebeli4redo.R

fun ImageView.loadImg(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.ic_page_not_found)
        .into(this)

}