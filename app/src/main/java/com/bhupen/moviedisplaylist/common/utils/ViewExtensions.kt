package com.bhupen.moviedisplaylist.common.utils

import android.view.View
import android.widget.ImageView
import com.bhupen.moviedisplaylist.R
import com.bumptech.glide.Glide



fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(string: String?) {
    Glide.with(context).load(string ?: R.drawable.ic_not_found).into(this)
}