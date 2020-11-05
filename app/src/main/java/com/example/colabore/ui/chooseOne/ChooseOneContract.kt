package com.example.colabore.ui.chooseOne



import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface ChooseOneContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)

    }

    interface Presenter : BasePresenter<View> {

    }
}