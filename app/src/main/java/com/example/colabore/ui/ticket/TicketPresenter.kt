package com.example.colabore.ui.ticket

import android.app.Activity
import com.example.colabore.ui.confirm.ConfirmValueContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class  TicketPresenter : TicketContract.Presenter {

    private var view: TicketContract.View? = null


    override fun attachView(mvpView: TicketContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}