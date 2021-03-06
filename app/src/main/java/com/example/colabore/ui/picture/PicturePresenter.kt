package com.example.colabore.ui.picture

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.model.PictureProfileModel
import com.example.colabore.model.UserObject
import com.example.colabore.ui.profile.ProfileContract
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.reflect.typeOf

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class PicturePresenter : PictureContract.Presenter {

    private var view: PictureContract.View? = null

    override fun loadPicture() {
        view?.displayLoading(false)
        db.collection("pictures").get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val pictures = it.toObjects<PictureProfileModel>(PictureProfileModel ::class.java)
                view?.displayPictures(pictures)
                Log.w(ContentValues.TAG, "$pictures")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com dados", exception)
            }

    }

    override fun setPicture(urlImage : String , face: String) {
        view?.displayLoading(false)
        db.collection("usuarios").document(PersistUserInformation.cpf().unmask()).update("imageUrl",urlImage , "face", face)
            .addOnSuccessListener{
                view?.displayLoading(true)
                view?.startHome()
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(ContentValues.TAG, "Erro com dados", exception)
            }

    }

    override fun attachView(mvpView: PictureContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}