package com.example.colabore.ui.confirm

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.OngObject
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.PersistUserInformation.idOng
import com.example.colabore.model.UserObject
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class  ConfirmValuePresenter : ConfirmValueContract.Presenter {

    private var view: ConfirmValueContract.View? = null


    override fun attachView(mvpView: ConfirmValueContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}