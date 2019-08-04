package com.nchuzh.swapikotlincoroutines.view

import android.app.ProgressDialog
import android.content.Context
import com.nchuzh.swapikotlincoroutines.R

fun showProgressDialog(context: Context): ProgressDialog {
    val dialog = ProgressDialog(context)
    dialog.setMessage(context.getString(R.string.loading))
    dialog.setCancelable(false)
    dialog.show()

    return dialog
}