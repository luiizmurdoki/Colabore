package com.example.colabore.ui.main

import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface MainContract{
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayName( name:String?, imageUrl:String?)
        fun  displayCards(items: List<CardModel>)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getDataUser(cpf: String)
        fun loadBanners()
    }
}