package com.example.colabore.model

import android.os.SystemClock

object PersistUserInformation {
    private var persistedUser: UserObject? = UserObject()
    private var userLogin: String? = null
    private var userCnpj: String? = null
    private var userName: String? = null
    private var password: String = ""
    private var phone: String? = null

    fun cpf(): String = persistedUser?.cpf ?: PreferencesHelper.userCpf ?: ""
    fun cpf(cpf: String): String {
        persistedUser?.cpf = cpf
        return cpf()
    }

  /*  fun cnpj(): String = persistedUser?.cnpj ?: PreferencesHelper.userCnpj ?: ""
    fun cnpj(cnpj: String): String {
        persistedUser?.cnpj = cnpj
        return cnpj()
    }*/

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

    fun clear() {
        persistedUser = null
        userLogin = null
        password = ""
    }

}