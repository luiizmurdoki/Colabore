package com.example.colabore.ui.widget

import android.app.Activity
import android.content.Context
import androidx.annotation.StyleRes
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheetDialog : BottomSheetDialog {

    constructor(context: Context) : super(context)

    constructor(context: Context, @StyleRes styleRes: Int) : super(context, styleRes)

    override fun show() {
        val context = context
        if (context !is Activity || !context.isFinishing) {
            super.show()
        }
    }

}