package com.example.colabore.ui.event

import android.app.Activity
import android.content.ContentValues
import android.text.Editable
import android.util.Log
import com.example.colabore.model.PersistUserInformation.name
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.displayName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class EventPresenter : EventContract.Presenter {

    private var view: EventContract.View? = null


    override fun attachView(mvpView: EventContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun setEvent(title: String, discription: Editable) {
        val event = hashMapOf(
            "data" to  java.util.Calendar.getInstance().time.displayName(Constants.TICKET_DATE_FORMAT),
            "mensagem" to discription.toString(),
            "ong" to name(),
            "titulo" to title
        )

      db.collection("eventos").add(event)
            .addOnSuccessListener { documentReference ->
                view?.displaySucess("")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
                view?.displayError("Algo deu errado!")
            }

    }

    override fun detachView() {
        view = null
    }

}