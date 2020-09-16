package com.example.colabore.login

import android.content.Intent
import android.os.Bundle
import com.example.colabore.main.MainActivity
import com.example.colabore.R
import com.example.colabore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :  BaseActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter().apply { attachView(this@LoginActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners(){
        loginSigninBtn.setOnClickListener { openHome() }
    }


    private fun setLoginBtnClick() {
        if (allFieldsValid()) {
            TODO( "Mandar o que o user digitou para o presenter")
           /* presenter.checkCpf(loginCpfEt.text, loginPasswordEt.text)*/
        } else {
            displayError(getString(R.string.fields_error))
        }
    }

    private fun openHome(){
        val intent = Intent(this, MainActivity::class.java).apply{}
        startActivity(intent)
    }

    private fun allFieldsValid(): Boolean {
        return loginCpfEt.text.isNotBlank() && loginPasswordEt.text.isNotBlank()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun displayError(msg: String?) {
        displayError(msg)
    }

/*    override fun openHome() {
        startActivitySlideTransition(createHomeIntent())
    }*/




}

