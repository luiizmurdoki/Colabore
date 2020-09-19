package com.example.colabore.ui.main

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface MainContract{
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {
    }
}