package com.example.colabore.model

data class CardModel(
    var imageUrl : String = "",
    var info: String = "",
    var nome: String = "",
    var cnpj: String = "",
    var idDocument: String = "",
    var endereco: String = "",
    var latitude: Float = 0F,
    var longitude: Float = 0F,
    var telefone: String = "",
    var valorRecebido: Float = 0F

)