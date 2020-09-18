package com.example.colabore.ui.login

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import android.util.LogPrinter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.colabore.R
import com.example.colabore.utils.validations.IsCpf
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import io.reactivex.internal.util.HalfSerializer.onComplete


var database: DatabaseReference = Firebase.database.reference
private lateinit var auth: FirebaseAuth

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null


    override fun attachView(mvpView: LoginContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun getUser(cpf: String, password:String) {
        /* database.child("usuarios")
            .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view?.displayError("Deu bom carai")
            }

            override fun onCancelled(error: DatabaseError) {
                view?.displayError("Deu ruim")
            }
        })*/

/*
    Auth.signInWithEmailAndPassword(email, password)
    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull (Task<AuthResult>) task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        }
    })
*/
    }
    override fun detachView() {
        view = null
    }

}