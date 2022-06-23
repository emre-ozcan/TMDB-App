package com.emreozcan.tmdbapp.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.emreozcan.tmdbapp.utils.loadCircleImage
import com.emreozcan.tmdbapp.utils.loadImage

/**
 * Created by @Emre Özcan on 22.06.2022
 */
class CardItemBinding {
    companion object{

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, imgPath: String?){
            imgPath?.let {
                val imageUrl = "https://image.tmdb.org/t/p/w500$imgPath"
                imageView.loadImage(imageUrl)
            }
        }

        @BindingAdapter("load_circle_image")
        @JvmStatic
        fun loadCircleImage(imageView: ImageView, imgPath: String?){
            imgPath?.let {
                val imageUrl = "https://image.tmdb.org/t/p/w500$imgPath"
                imageView.loadCircleImage(imageUrl)
            }
        }
    }
}