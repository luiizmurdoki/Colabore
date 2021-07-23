package com.example.colabore.ui.mainNgo

import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface MainNgoContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayData(image: String , name: String)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadData(cnpj: String)
    }
}