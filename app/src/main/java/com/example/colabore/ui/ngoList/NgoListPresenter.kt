package com.example.colabore.ui.ngoList

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.UserObject
import com.example.colabore.ui.main.MainContract
import com.example.colabore.ui.main.db
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

class NgoListPresenter : NgoListContract.Presenter {

    private var view: NgoListContract.View? = null



    private fun saveUserModel(user : UserObject?){
        UserObject(
            cpf = user?.cpf,
            nome = user?.nome,
            dataNascimento =user?.dataNascimento,
            email = user?.email ,
            face = user?.face ,
            telefone = user?.telefone ,
            senha = user?.senha
        )

    }

    override fun loadBanners() {
        view?.displayLoading(false)
        db.collection("ongs").get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val banners = it.toObjects<CardModel>(CardModel ::class.java)
                view?.displayCards(banners)
                Log.w(ContentValues.TAG, "$banners")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com Dados do usuario", exception)
            }


    }

    override fun attachView(mvpView: NgoListContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}