package com.example.colabore.ui.login

import android.app.Dialog
import android.app.ProgressDialog
import android.app.ProgressDialog.*
import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.main.MainActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.progressDialog


class LoginActivity :  BaseActivity(), LoginContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter().apply { attachView(this@LoginActivity) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setText()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        setListeners()
    }

    private fun setText(){
        loginCpfEt.setText("25316577035")
        loginPasswordEt.setText("Teste123")
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners(){
        loginSigninBtn.setOnClickListener { setLoginBtnClick() }
    }


    private fun setLoginBtnClick() {
        if (allFieldsValid()) {
           presenter.getUser(loginCpfEt.text, loginPasswordEt.text)
        } else {
            displayError(msg = getString(R.string.fields_error))
        }
    }

    override fun openHome(){
        val intent = Intent(this, MainActivity::class.java).apply{}
        startActivity(intent)
        finish()
    }

    private fun allFieldsValid(): Boolean {
        return loginCpfEt.text.isNotBlank() && loginPasswordEt.text.isNotBlank()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override  fun displayLoading(close : Boolean){
            if(close) progressDialog.dialog.dismiss()
            else progressDialog.show(this)
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg                              ,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

}

