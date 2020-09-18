package com.example.colabore.ui.login

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialogmessage.MessageBottomDialog
import com.example.colabore.ui.main.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :  BaseActivity(), LoginContract.View {

    private lateinit var auth: FirebaseAuth

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
           getUser(loginCpfEt.text, loginPasswordEt.text)
        } else {
            displayError(msg = getString(R.string.fields_error))
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

    private fun getUser(cpf: String, password:String){

        auth.signInWithEmailAndPassword(cpf+"@colabore.com", password)
            .addOnCompleteListener(this, { task: Task<AuthResult> -> })
            .addOnSuccessListener(this) { authResult: AuthResult? ->
                val currentUser = FirebaseAuth.getInstance().currentUser
                displayError("Deu bom")
            }
            .addOnFailureListener(
                this
            ) { exception: Exception ->
                displayError("Deu ruim")
            }
    }

    override fun displayError(msg: String){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

/*    override fun openHome() {
        startActivitySlideTransition(createHomeIntent())
    }*/




}

