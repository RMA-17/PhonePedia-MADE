package com.rmaprojects.phonepedia.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun triggerSnackbar(
    rootView: View,
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT
): Snackbar {
    return Snackbar.make(rootView, message, duration)
}