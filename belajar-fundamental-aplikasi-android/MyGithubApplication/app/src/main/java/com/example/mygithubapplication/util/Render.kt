package com.example.mygithubapplication.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Render {

    fun showLoading(isLoading: Boolean, view: View) {
        if (isLoading) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    fun showSnackbar(
        view: View,
        message: String,
        backgroundColor: Int,
        textColor: Int,
        anchorView: View
    ) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(backgroundColor)
        snackbar.setTextColor(textColor)
        snackbar.setAnchorView(anchorView)
        snackbar.show()
    }

    fun showSnackbar(view: View, message: String, backgroundColor: Int, textColor: Int) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(backgroundColor)
        snackbar.setTextColor(textColor)
        snackbar.show()
    }

}