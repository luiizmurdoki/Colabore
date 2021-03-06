package com.example.colabore.ui.ngoList

import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface NgoListContract {

        interface View : BaseView<Presenter> {
            fun displayError(msg: String?)
            fun  displayCards(items: List<CardModel>)
            fun displayLoading(close : Boolean)
        }

        interface Presenter : BasePresenter<View> {
            fun loadBanners()
        }

}