package com.example.colabore.ui.login

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView
import com.example.colabore.utils.validations.IsCpf


interface LoginContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun openHome(cpf: String)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getUser(cpf: String, password:String)
    }
}