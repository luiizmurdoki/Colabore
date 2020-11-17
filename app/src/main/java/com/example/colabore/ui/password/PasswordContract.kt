package com.example.colabore.ui.password

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface PasswordContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun startFinish()
    }

    interface Presenter : BasePresenter<View> {
        fun setUser(password : String)
        fun setNgo(password : String)
    }
}