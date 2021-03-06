package com.example.colabore.ui.description

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface DescriptionContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
    }

    interface Presenter : BasePresenter<View> {
    }
}
