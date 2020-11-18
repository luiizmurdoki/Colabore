package com.example.colabore.ui.descriptionEvent


import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()
class DescriptionEventPresenter : DescriptionEventContract.Presenter {

    private var view: DescriptionEventContract.View? = null


    override fun attachView(mvpView: DescriptionEventContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}