package com.example.colabore.ui.history

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.HistoryModel
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.UserObject
import com.example.colabore.ui.ngoList.NgoListContract
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
private  lateinit var query : DatabaseReference
var db = FirebaseFirestore.getInstance()
private  var user : UserObject = UserObject()
private var context = Activity()

class HistoryPresenter : HistoryContract.Presenter {

    private var view: HistoryContract.View? = null

    override fun loadHistory() {
        view?.displayLoading(false)
       db.collection("transacoes").whereEqualTo("cpf", cpf().unmask()).get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val history = it.toObjects<HistoryModel>(HistoryModel::class.java)
                view?.displayHistory(history)
                Log.w(ContentValues.TAG, "$history")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com Dados do usuario", exception)
            }


    }

    override fun attachView(mvpView: HistoryContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}