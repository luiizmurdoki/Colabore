package com.example.colabore.ui.password

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.model.PersistUserInformation.birthDate
import com.example.colabore.model.PersistUserInformation.categoria
import com.example.colabore.model.PersistUserInformation.cnpj
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.PersistUserInformation.email
import com.example.colabore.model.PersistUserInformation.endereco
import com.example.colabore.model.PersistUserInformation.imageUrl
import com.example.colabore.model.PersistUserInformation.info
import com.example.colabore.model.PersistUserInformation.name
import com.example.colabore.model.PersistUserInformation.phone
import com.example.colabore.utils.Constants
import com.example.colabore.utils.extension.displayName
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()
class PasswordPresenter : PasswordContract.Presenter {

    private var view: PasswordContract.View? = null


    override fun attachView(mvpView: PasswordContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun setUser(password: String) {
        val user = hashMapOf(
            "cpf" to cpf().unmask(),
            "dataNascimento" to birthDate(),
            "email" to email(),
            "face" to "face0",
            "imageUrl" to "https://i.ibb.co/hm1BWwq/face0.jpg",
            "nome" to name(),
            "senha" to password,
            "telefone" to phone()
        )

// Add a new document with a generated ID
        com.example.colabore.ui.ticket.db.collection("usuarios")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                view?.startFinish()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
                view?.displayError("Algo deu errado!")
            }

    }


    override fun setNgo(password: String) {
        val ong = hashMapOf(
            "categoria" to categoria(),
            "cnpj" to cnpj().unmask(),
            "dataRegistro" to java.util.Calendar.getInstance().time.displayName(Constants.TICKET_DATE_FORMAT),
            "endereco" to endereco(),
            "idDocument" to null,
            "imageUrl" to imageUrl(),
            "info" to info(),
            "latitude" to 0f,
            "longitude" to 0f,
            "nome" to name(),
            "senha" to password,
            "telefone" to phone(),
            "valorRecebido" to 0
        )

// Add a new document with a generated ID
        com.example.colabore.ui.ticket.db.collection("ongs")
            .add(ong)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                view?.startFinish()
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