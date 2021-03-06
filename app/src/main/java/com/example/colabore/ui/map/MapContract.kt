package com.example.colabore.ui.map

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface MapContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getDataUser(cpf: String)
    }
}