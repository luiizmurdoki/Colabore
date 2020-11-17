package com.example.colabore.ui.signUp

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.OngObject
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.displayName
import com.example.colabore.utils.extension.getFloatValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class SignupPresenter : SignUpContract.Presenter {

    private var view: SignUpContract.View? = null


    override fun attachView(mvpView: SignUpContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}