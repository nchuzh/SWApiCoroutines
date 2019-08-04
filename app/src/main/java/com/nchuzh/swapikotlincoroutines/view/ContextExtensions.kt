package com.nchuzh.swapikotlincoroutines.view

import android.app.ProgressDialog
import android.content.Context

fun showProgressDialog(context: Context): ProgressDialog {
    val dialog = ProgressDialog(context)
    dialog.setMessage("Loading...")
    dialog.setCancelable(false)
    dialog.show()

    return dialog
}