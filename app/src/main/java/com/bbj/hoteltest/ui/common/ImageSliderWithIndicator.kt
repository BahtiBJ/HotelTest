package com.bbj.hoteltest.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.bbj.hoteltest.R
import com.bbj.hoteltest.ui.extensions.dpToPx

class ImageSliderWithIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imagePager: ViewPager2
    private val indicators: LinearLayout

    private val onPageChange = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            selectPoint(position)
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_slider_with_indicator, this, true)
        imagePager = findViewById(R.id.vpImages)
        imagePager.registerOnPageChangeCallback(onPageChange)
        indicators = findViewById(R.id.llPoints)
    }

    fun setImagePaths(imagePaths : List<String>){
        val adapter = AdapterImageSlider()
        adapter.items = imagePaths
        imagePager.adapter = adapter
        createPoints(imagePaths.size)
    }

    private val points = arrayListOf<View>()

    private fun createPoints(count: Int) {
        if (count <= 0)
            return

        for (i in 1 .. count) {
            val bgRes =
                if (i == 1) R.drawable.shape_circle_black else R.drawable.shape_circle_inactive
            val marginEnd =
                if (i == count) 0 else context.dpToPx(5)

            val point = View(context).apply {
                val params = LinearLayout.LayoutParams(context.dpToPx(7),context.dpToPx(7))
                params.setMargins(0,0,marginEnd,0)
                layoutParams = params
                setBackgroundResource(bgRes)
            }
            points.add(point)
            indicators.addView(point)
        }
    }

    private fun selectPoint(position: Int){
        if (points.isEmpty())
            return
        points.forEachIndexed { index, view ->
            if(index == position){
                view.background =
                    ResourcesCompat.getDrawable(resources,R.drawable.shape_circle_black,null)
            } else {
                view.background =
                    ResourcesCompat.getDrawable(resources,R.drawable.shape_circle_inactive,null)
            }
        }
    }
    }