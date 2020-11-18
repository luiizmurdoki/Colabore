package com.example.colabore.ui.eventUser

import com.example.colabore.model.CardModel
import com.example.colabore.model.EventModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface EventUserContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun  displayCards(items: List<EventModel>)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadBanners()
    }
}