package com.example.colabore.ui.main

import android.app.Activity
import android.content.ContentValues.TAG
import android.graphics.ColorSpace
import android.util.Log
import com.example.colabore.model.UserObject
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
private  lateinit var query :DatabaseReference
var db = FirebaseFirestore.getInstance()
private  var user : UserObject = UserObject()
private var context = Activity()

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null


    override  fun getNameUser() {
        view?.displayName(name = user.nome)
    }

    override fun attachView(mvpView: MainContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}