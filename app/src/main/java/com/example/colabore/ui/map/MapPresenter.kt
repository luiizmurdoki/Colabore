package com.example.colabore.ui.map

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.UserObject
import com.example.colabore.ui.description.DescriptionContract
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class MapPresenter : MapContract.Presenter {

    private var view: MapContract.View? = null


    override fun getDataUser(cpf: String) {
        view?.displayLoading(false)
        com.example.colabore.ui.main.db.collection("ongs").get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val localization = it.toObjects<CardModel>(CardModel ::class.java)
                 view?.handleLocation(localization)
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com Dados", exception)
            }

    }

    override fun attachView(mvpView: MapContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}