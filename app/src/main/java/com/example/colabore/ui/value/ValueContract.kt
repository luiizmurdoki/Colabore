package com.example.colabore.ui.value

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface ValueContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {
    }
}