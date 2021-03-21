package com.example.colabore.ui.map

import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface MapContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayLoading(close : Boolean)
        fun handleLocation(location: List<CardModel>)
    }

    interface Presenter : BasePresenter<View> {
        fun getDataUser(cpf: String)
    }
}