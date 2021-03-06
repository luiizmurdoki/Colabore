package com.example.colabore.ui.signUp

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface SignUpContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {

    }
}