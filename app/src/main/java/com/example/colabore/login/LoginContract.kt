package com.example.colabore.login

import com.example.colabore.base.BasePresenter
import com.example.colabore.base.BaseView


interface LoginContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {
    }
}