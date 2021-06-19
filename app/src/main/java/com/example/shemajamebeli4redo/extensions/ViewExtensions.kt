package com.example.shemajamebeli4redo.extensions

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shemajamebeli4redo.App
import com.example.shemajamebeli4redo.R
import org.w3c.dom.Text

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

fun TextView.changeColor(color: Int) {
    setTextColor(App.context.getColor(color))
}
