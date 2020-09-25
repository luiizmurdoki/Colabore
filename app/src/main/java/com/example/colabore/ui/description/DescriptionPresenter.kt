package com.example.colabore.ui.description

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import android.util.LogPrinter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.colabore.R
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.model.UserObject
import com.example.colabore.ui.login.LoginContract
import com.example.colabore.ui.main.db
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.unmask
import com.example.colabore.utils.validations.IsCpf
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import io.reactivex.internal.util.HalfSerializer.onComplete

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class DescriptionPresenter : DescriptionContract.Presenter {

    private var view: DescriptionContract.View? = null


    override fun attachView(mvpView: DescriptionContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}