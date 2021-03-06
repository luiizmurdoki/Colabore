package com.example.colabore.ui.picture

import com.example.colabore.model.PictureProfileModel
import com.example.colabore.ui.base.BasePresenter
import com.example.colabore.ui.base.BaseView

interface PictureContract {
    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayPictures(items: List<PictureProfileModel>)
        fun displayLoading(close : Boolean)
        fun startHome()

    }

    interface Presenter : BasePresenter<View> {
        fun loadPicture()
        fun setPicture (urlImage : String , face: String)
    }

}