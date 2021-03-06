package com.example.colabore.ui.descriptionEvent

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface DescriptionEventContract {
        interface View : BaseView<Presenter> {
            fun displayError(msg: String?)
        }

        interface Presenter : BasePresenter<View> {
        }
}