package com.example.colabore.ui.password

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.PersistUserInformation.cnpj
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.finish.FinishActivity
import com.example.colabore.ui.widget.DefaultEditText
import com.example.colabore.utils.extension.validateFields
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity :  BaseActivity(), PasswordContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var fields: List<DefaultEditText>
    private val presenter: PasswordContract.Presenter by lazy {
        PasswordPresenter().apply { attachView(this@PasswordActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        setListeners()
        auth = FirebaseAuth.getInstance()
        fields = listOf(signinPasswordEt,signinPasswordAgainEt)
        FirebaseApp.initializeApp(this)
    }


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    private fun setListeners(){
        toolbarPassword.setOnClickListener { onBackPressed() }
        nextPasswordBtn.setOnClickListener {
            if (fields.validateFields()){
                if(cnpj().isNotEmpty()){
                    presenter.setNgo(signinPasswordEt.text)
                }
                else presenter.setUser(signinPasswordEt.text)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun startFinish(){
        val intent = Intent(this, FinishActivity::class.java).apply{}
        startActivity(intent)
        finishAffinity()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg                              ,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

}