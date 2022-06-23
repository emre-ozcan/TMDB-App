package com.emreozcan.tmdbapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.transform.CircleCropTransformation

/**
 * Created by @Emre Özcan on 22.06.2022
 */

fun ImageView.loadImage(url: String?){
    val placeholder = createPlaceHolder(this.context)
    this.load(url){
        crossfade(true)
        crossfade(500)
        placeholder(placeholder)
    }
}
fun ImageView.loadCircleImage(url: String?){
    val placeholder = createPlaceHolder(this.context)
    this.load(url){
        crossfade(true)
        crossfade(500)
        placeholder(placeholder)
        transformations(CircleCropTransformation())
    }
}

private fun createPlaceHolder(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}