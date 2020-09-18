package com.example.colabore.ui.dialogmessage

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

class MessageBottomDialog(context: Context,
                          private val title: String?,
                          private val subtitle: String?,
                          private val positiveText: String,
                          private val positiveAction: (() -> Unit)?,
                          private val negativeText: String? = null,
                          private val negativeAction: (() -> Unit)? = null,
                          private val dismissAction: (() -> Unit)? = null,
                          private val hyperlinkMessage: String? = null,
                          private val hyperlinkAction: (() -> Unit)? = null,
                          private val isCancelable: Boolean = true) : BaseBottomSheetDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bottom_message)

        setView()
        setParams()
        setCancelable(isCancelable)


        setOnShowListener {
            val sheet = findViewById<FrameLayout>(R.id.design_bottom_sheet)
            BottomSheetBehavior.from(sheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setParams() {
        val params = WindowManager.LayoutParams(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        params.copyFrom(window?.attributes)
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window?.attributes = params
    }

    override fun onBackPressed() {
        if (isCancelable) {
            super.onBackPressed()
        }
    }

    override fun dismiss() {
        dismissAction?.invoke()
        super.dismiss()
    }

    private fun setView() {
        dialogTitleTv.text = title
        dialogSubtitleTv.text = subtitle

        if (negativeText != null) {
            dialogNegativeBtn.setVisible(true)
            dialogNegativeBtn.setText(negativeText)
            dialogNegativeBtn.setOnClickListener {
                negativeAction?.invoke()
                dismiss()
            }
        }

        dialogPositiveBtn.setText(positiveText)
        dialogPositiveBtn.setOnClickListener {
            positiveAction?.invoke()
            dismiss()
        }

        hyperlinkMessage?.run {
            dialogHyperlink.paintFlags = dialogHyperlink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            dialogHyperlink.text = this
            dialogHyperlink.setOnClickListener {
                hyperlinkAction?.invoke()
                dismiss()
            }
        }
    }
}