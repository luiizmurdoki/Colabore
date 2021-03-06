package com.example.colabore.ui.chooseOne

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class ChooseOnePresenter : ChooseOneContract.Presenter {

    private var view: ChooseOneContract.View? = null


    override fun attachView(mvpView: ChooseOneContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

/*    override fun getUser(cpf: String, password:String) {
        view?.displayLoading(false)
        auth.signInWithEmailAndPassword(cpf.unmask()+ Constants.RESQUEST, password)
            .addOnCompleteListener(context) { }
            .addOnSuccessListener(context) {
                PersistUserInformation.cpf(cpf.unmask())
                view?.displayLoading(true)
                view?.openHome()
            }
            .addOnFailureListener(context) {
                view?.displayLoading(true)
                view?.displayError(null)
            }
    }*/



    override fun detachView() {
        view = null
    }

}