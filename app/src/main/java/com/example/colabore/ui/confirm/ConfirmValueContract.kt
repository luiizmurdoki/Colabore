package com.example.colabore.ui.confirm

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface ConfirmValueContract {
        interface View : BaseView<Presenter> {
            fun displayError(msg: String?)
        }

        interface Presenter : BasePresenter<View> {
        }
}
