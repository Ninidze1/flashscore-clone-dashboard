package com.example.shemajamebeli4redo.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shemajamebeli4redo.R

fun ImageView.loadImg(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .placeholder(R.mipmap.placeholder)
        .error(R.drawable.ic_info_button)
        .into(this)
}

fun View.mirrorView() {
    layoutDirection = View.LAYOUT_DIRECTION_RTL

}
