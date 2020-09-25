package com.example.colabore.ui.main

import android.app.Activity
import android.content.ContentValues.TAG
import android.graphics.ColorSpace
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.UserObject
import com.example.colabore.utils.extension.unmask
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


    override fun getDataUser(cpf: String) {
        db.collection("usuarios").document(cpf.unmask()).get()
            .addOnSuccessListener{
                val document = it.data
                val user = it.toObject<UserObject>(UserObject ::class.java)
                view?.displayName(name = user?.nome)
                saveUserModel(user)
                Log.w(TAG, "$user")
            }
            .addOnFailureListener{ exception->
                Log.w(TAG, "Erro com Dados do usuario", exception)
            }

    }

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
        db.collection("ongs").get()
            .addOnSuccessListener{
                val banners = it.toObjects<CardModel>(CardModel ::class.java)
                view?.displayCards(banners)
                Log.w(TAG, "$banners")
            }
            .addOnFailureListener{ exception->
                Log.w(TAG, "Erro com Dados do usuario", exception)
            }


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