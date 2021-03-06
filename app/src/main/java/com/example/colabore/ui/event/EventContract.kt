package com.example.colabore.ui.event

import android.text.Editable
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface EventContract {
        interface View : BaseView<Presenter> {
            fun displayError(msg: String?)
            fun displaySucess(msg: String?)
        }

        interface Presenter : BasePresenter<View> {
            fun setEvent(title: String, discription: Editable)
        }
}