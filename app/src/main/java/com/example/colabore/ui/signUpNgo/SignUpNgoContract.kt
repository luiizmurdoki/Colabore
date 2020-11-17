package com.example.colabore.ui.signUpNgo

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface SignUpNgoContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {

    }
}