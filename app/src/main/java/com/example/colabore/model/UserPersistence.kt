package com.example.colabore.model

import android.os.SystemClock

object PersistUserInformation {
    private var persistedUser: UserObject? = UserObject()
    private var persistedOng: CardModel? = CardModel()
    private var userLogin: String? = null
    private var userCnpj: String? = null
    private var userName: String? = null
    private var password: String = ""
    private var phone: String? = null
    private var ongId: String? = null

    fun cpf(): String = persistedUser?.cpf ?: PreferencesHelper.userCpf ?: ""
    fun cpf(cpf: String): String {
        persistedUser?.cpf = cpf
        return cpf()
    }

   fun cnpj(): String = persistedOng?.cnpj ?: PreferencesHelper.cnpj ?: ""
    fun cnpj(cnpj: String): String {
        persistedOng?.cnpj = cnpj
        return cnpj()
    }

    fun categoria(): String = persistedOng?.categoria ?: PreferencesHelper.categoria ?: ""
    fun categoria(categoria: String): String {
        persistedOng?.categoria = categoria
        return categoria()
    }

    fun endereco(): String = persistedOng?.endereco ?: PreferencesHelper.endereco ?: ""
    fun endereco(endereco: String): String {
        persistedOng?.endereco = endereco
        return endereco()
    }

    fun imageUrl(): String = persistedOng?.imageUrl ?: PreferencesHelper.imageUrl ?: ""
    fun imageUrl(imageUrl: String): String {
        persistedOng?.imageUrl = imageUrl
        return imageUrl()
    }

    fun info(): String = persistedOng?.info ?: PreferencesHelper.info ?: ""
    fun info(info: String): String {
        persistedOng?.info = info
        return info()
    }


    fun name(): String = persistedUser?.nome ?: PreferencesHelper.userName ?: ""
    fun name(name: String): String {
        persistedUser?.nome = name
        return name()
    }

    fun picture(): String = persistedUser?.face ?: ""
    fun picture(newPicture: String): String {
        persistedUser?.face = newPicture
        return picture()
    }
    fun phone(): String = persistedUser?.telefone ?: phone ?: ""
    fun phone(newPhone: String): String {
        persistedUser?.telefone = newPhone
        this.phone = newPhone
        return phone()
    }


    fun birthDate(): String = persistedUser?.dataNascimento ?: ""
    fun birthDate(newBirthDate: String): String {
        persistedUser?.dataNascimento = newBirthDate
        return birthDate()
    }

    fun email(): String = persistedUser?.email ?: ""
    fun email(newEmail: String): String {
        persistedUser?.email = newEmail
        return email()
    }


    fun idOng(): String = persistedOng?.idOng ?: PreferencesHelper.idOng ?: ""
    fun idOng(ongId: String): String {
        persistedOng?.idOng = ongId
        return cpf()
    }

    fun clear() {
        persistedUser = null
        userLogin = null
        password = ""
    }

}