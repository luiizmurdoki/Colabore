package com.example.colabore.ui.historyNgo

import com.example.colabore.model.HistoryModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface HistoryNgoContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayHistory(items: List<HistoryModel>)
        fun displayLoading(close : Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadHistory()
    }
}