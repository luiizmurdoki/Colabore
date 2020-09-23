package com.example.colabore.utils

import android.content.Context


class CustomLinearLayoutManager(context: Context) : androidx.recyclerview.widget.LinearLayoutManager(context) {
    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}

