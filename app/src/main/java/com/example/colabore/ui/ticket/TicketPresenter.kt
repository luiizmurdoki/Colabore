package com.example.colabore.ui.ticket

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.example.colabore.model.OngObject
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.PersistUserInformation.name
import com.example.colabore.ui.confirm.ConfirmValueContract
import com.example.colabore.utils.Constants
import com.example.colabore.utils.Constants.CDT_TICKET_RESALES_DATE_FORMAT
import com.example.colabore.utils.Constants.ONG_DATE_FORMAT
import com.example.colabore.utils.extension.displayName
import com.example.colabore.utils.extension.getFloatValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var auth: FirebaseAuth
var db = FirebaseFirestore.getInstance()
private var context = Activity()

class  TicketPresenter : TicketContract.Presenter {

    private var view: TicketContract.View? = null



    override fun getDataOng(value: String) {
        db.collection("ongs").document(PersistUserInformation.idOng().toString()).get()
            .addOnSuccessListener{
                val doc = it.data
                val ong = it.toObject<OngObject>(OngObject ::class.java)
                if (ong != null) {
                    setONg(ong = ong, value = value)
                }
                Log.w(ContentValues.TAG, "$ong")
            }
            .addOnFailureListener{ exception->
                Log.w(ContentValues.TAG, "Erro com Dados do usuario", exception)
            }

    }

    private fun setONg(ong : OngObject, value: String) {
        val user = hashMapOf(
            "nome" to name(),
            "cpf" to cpf(),
            "valor" to  value.getFloatValue(),
            "nomeOng" to ong.nome,
            "cnpj" to ong.cnpj,
            "dataHora" to java.util.Calendar.getInstance().time.displayName(ONG_DATE_FORMAT)
        )

// Add a new document with a generated ID
        db.collection("transacoes")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                view?.displayError("Algo deu errado com seu boleto!")
            }

    }

    override fun attachView(mvpView: TicketContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}