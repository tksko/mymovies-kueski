package com.tksko.mymovies.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import java.util.Calendar
import java.util.Date

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.grayScale() {
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    val colorFilter = ColorMatrixColorFilter(colorMatrix)
    this.colorFilter = colorFilter
}

fun Toolbar.setScrollBehavior(isScrollable: Boolean) {
    (layoutParams as? AppBarLayout.LayoutParams)?.scrollFlags = if (isScrollable) {
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
    } else {
        AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
    }
}

fun Date.yearOnly(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR).toString()
}