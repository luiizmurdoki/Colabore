package com.example.colabore.ui.ticket

import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface TicketContract {
        interface View : BaseView<Presenter> {
            fun displayError(msg: String?)
        }

        interface Presenter : BasePresenter<View> {
        }
}