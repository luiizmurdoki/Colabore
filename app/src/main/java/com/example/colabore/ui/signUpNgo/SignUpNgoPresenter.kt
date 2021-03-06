package com.example.colabore.ui.signUpNgo

import android.app.Activity
import com.example.colabore.ui.signUp.SignUpContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class SignUpNgoPresenter : SignUpNgoContract.Presenter {

    private var view: SignUpNgoContract.View? = null


    override fun attachView(mvpView: SignUpNgoContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}