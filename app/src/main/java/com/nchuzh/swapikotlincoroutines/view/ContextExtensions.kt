package com.nchuzh.swapikotlincoroutines.view

import android.app.ProgressDialog
import android.content.Context
import com.nchuzh.swapikotlincoroutines.R

fun Context.showProgressDialog(): ProgressDialog {
    val dialog = ProgressDialog(this)
    dialog.setMessage(getString(R.string.loading))
    dialog.setCancelable(false)
    dialog.show()

    return dialog
}