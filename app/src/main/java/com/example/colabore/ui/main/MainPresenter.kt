package com.example.colabore.ui.main

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


private lateinit var auth: FirebaseAuth
private var context = Activity()

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null


    override fun attachView(mvpView: MainContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}