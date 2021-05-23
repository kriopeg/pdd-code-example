package ru.kriopeg.example

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(justInvisible: Boolean = false) {
    if (justInvisible) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.toggleVisibility(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}