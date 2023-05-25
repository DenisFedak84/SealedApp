package com.example.sealedapp.extansions

import android.view.View

fun View.visibleOrGone(show: Boolean) {
    if (show) visible() else gone()
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}