package com.bbj.hoteltest.ui.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import com.bbj.hoteltest.R
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    val r = resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        r.displayMetrics
    ).roundToInt()
}

fun FlexboxLayout.createPeculiarity(text: String) {
    val peculiarityView = TextView(context).apply {
        setText(text)
        setPadding(
            context.dpToPx(10),
            context.dpToPx(5),
            context.dpToPx(10),
            context.dpToPx(5),
        )
        setTextAppearance(R.style.TextMedium16_Addition)
        setBackgroundResource(R.drawable.shape_rounded_gray_5)
    }
    addView(peculiarityView)
}

fun TextInputLayout.getTextString() : String{
    return editText?.text.toString()
}

fun TextInputLayout.setIsValueValid(isValueValid : Boolean){
    boxBackgroundColor = if (isValueValid)
        resources.getColor(R.color.background)
    else
        resources.getColor(R.color.error)
}