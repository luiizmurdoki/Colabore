package com.example.colabore.ui.login

import android.app.Activity
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null


    override fun attachView(mvpView: LoginContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun getUser(cpf: String, password: String) {
        view?.displayLoading(false)
            auth.signInWithEmailAndPassword(cpf.unmask()+ Constants.RESQUEST, password)
                .addOnCompleteListener(context) { }
                .addOnSuccessListener(context) {
                    PersistUserInformation.cpf(cpf.unmask())
                    view?.displayLoading(true)
                    view?.openHome(cpf.unmask())
                }
                .addOnFailureListener(context) {
                    view?.displayLoading(true)
                    view?.displayError(null)
                }
    }



    override fun detachView() {
        view = null
    }

}