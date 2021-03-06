package com.example.colabore.ui.profile

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.UserObject
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class ProfilePresenter : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null


    override fun attachView(mvpView: ProfileContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun getDataUser() {
        view?.displayLoading(false)
        db.collection("usuarios").document(cpf()).get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val document = it.data
                val user = it.toObject<UserObject>(UserObject ::class.java)
                view?.displayImage(imageUrl = user?.imageUrl )
                Log.w(ContentValues.TAG, "$user")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com Dados do usuario", exception)
            }

    }



    override fun detachView() {
        view = null
    }

}