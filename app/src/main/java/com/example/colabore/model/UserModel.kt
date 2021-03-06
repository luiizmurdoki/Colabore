package com.example.colabore.model

import java.io.Serializable

data class UserObject (
    var cpf : String? ="",
    var dataNascimento: String? = "",
    var email: String? = "",
    var face: String? = "",
    var imageUrl: String? = "",
    var nome: String? = "",
    var senha:String? = "",
    var telefone: String? = ""

)