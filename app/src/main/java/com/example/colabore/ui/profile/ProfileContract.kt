package com.example.colabore.ui.profile

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface ProfileContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayImage(imageUrl: String?)
        fun displayLoading(close : Boolean)

    }

    interface Presenter : BasePresenter<View> {
        fun getDataUser()
    }

}