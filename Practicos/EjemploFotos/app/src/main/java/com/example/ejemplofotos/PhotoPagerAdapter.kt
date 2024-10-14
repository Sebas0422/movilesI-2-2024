package com.example.ejemplofotos

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class PhotoPagerAdapter(private val photos: List<Int>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.setImageResource(photos[position])
        container.addView(imageView)
        return imageView
    }

    override fun getCount(): Int = photos.size
    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as ImageView)
    }
}
