package com.example.colabore.ui.mainNgo

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation.name
import com.example.colabore.model.PersistUserInformation.picture
import com.example.colabore.model.UserObject
import com.example.colabore.utils.extension.unmask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore



private lateinit var auth: FirebaseAuth
private  lateinit var query :DatabaseReference
var db = FirebaseFirestore.getInstance()
private  var user : UserObject = UserObject()
private var context = Activity()

class MainNgoPresenter : MainNgoContract.Presenter {

    private var view: MainNgoContract.View? = null


    override fun loadData(cnpj: String) {
        view?.displayLoading(false)
        db.collection("ongs").document(cnpj).get()
            .addOnSuccessListener{
                view?.displayLoading(true)
                val data = it.toObject<CardModel>(CardModel ::class.java)
                saveOng(data)
                if (data != null) {
                    view?.displayData(data.imageUrl , data.nome)
                    name(data.nome)
                    picture(data.imageUrl)
                }
                Log.w(TAG, "$data")
            }
            .addOnFailureListener{ exception->
                view?.displayLoading(true)
                Log.w(TAG, "Erro com Dados do usuario", exception)
            }

    }

    private fun saveOng(ong : CardModel?){
        if (ong != null) {
            CardModel(

                categoria  = ong.categoria,
                cnpj = ong.cnpj,
                dataRegistro  = ong.dataRegistro,
                endereco = ong.endereco,
                idDocument = ong.idDocument,
                imageUrl  = ong.imageUrl,
                info = ong.info,
                latitude = ong.latitude,
                longitude = ong.longitude,
                nome = ong.nome,
                senha  = ong.senha,
                telefone = ong.telefone,
                valorRecebido = ong.valorRecebido

            )
        }

    }

    override fun attachView(mvpView: MainNgoContract.View?) {
        auth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}