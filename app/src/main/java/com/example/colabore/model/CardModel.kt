package com.example.colabore.model

import com.google.firebase.firestore.DocumentId

data class CardModel(
    var categoria : String = "",
    var cnpj: String = "",
    var dataRegistro : String = "",
    var endereco: String = "",
    var idDocument: String = "",
    var imageUrl : String = "",
    var info: String = "",
    var latitude: Float = 0F,
    var longitude: Float = 0F,
    var nome: String = "",
    var senha : String = "",
    var telefone: String = "",
    var valorRecebido: Float = 0F,
    @DocumentId
    var idOng: String = ""

)