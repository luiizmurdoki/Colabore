package com.example.colabore.ui.dialog

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Paint
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import com.example.colabore.R
import com.example.colabore.utils.extension.setVisible
import com.example.colabore.ui.widget.BaseBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.dialog_bottom_message.*

class LoadingDialog(context: Context, private val dismissAction: Boolean) : BaseBottomSheetDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        dismissLoading(dismissAction)
        setOnShowListener {
            val sheet = findViewById<FrameLayout>(R.id.design_bottom_sheet)
            BottomSheetBehavior.from(sheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
    private fun dismissLoading(close : Boolean) {
        if(close) super.dismiss()
    }

}