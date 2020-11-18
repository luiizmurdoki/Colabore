package com.example.colabore.ui.eventUser

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.EventModel
import com.example.colabore.model.UserObject
import com.example.colabore.ui.ngoList.NgoListContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
private  lateinit var query : DatabaseReference
var db = FirebaseFirestore.getInstance()
private  var user : UserObject = UserObject()
private var context = Activity()

class EventUserPresenter : EventUserContract.Presenter {

    private var view: EventUserContract.View? = null


    override fun loadBanners() {
        view?.displayLoading(false)
        com.example.colabore.ui.main.db.collection("eventos").get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val events = it.toObjects<EventModel>(EventModel ::class.java)
                view?.displayCards(events)
                Log.w(ContentValues.TAG, "$events")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com Dados do usuario", exception)
            }


    }

    override fun attachView(mvpView: EventUserContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}