package com.example.colabore.ui.signUp

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.PersistUserInformation.birthDate
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.model.PersistUserInformation.email
import com.example.colabore.model.PersistUserInformation.name
import com.example.colabore.model.PersistUserInformation.phone
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.finish.FinishActivity
import com.example.colabore.ui.password.PasswordActivity
import com.example.colabore.ui.widget.DefaultEditText
import com.example.colabore.utils.extension.validateFields
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*
import java.net.PasswordAuthentication


class SignUpActivity:  BaseActivity(), SignUpContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var fields: List<DefaultEditText>
    private val presenter: SignUpContract.Presenter by lazy {
        SignupPresenter().apply { attachView(this@SignUpActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setListeners()
        auth = FirebaseAuth.getInstance()
        fields = listOf(pfNameDt,pfCpfDt,pfPhoneDt,pfBirthdayDt,pfEmailDt)
        FirebaseApp.initializeApp(this)
    }


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    private fun setListeners(){
        toolbarSingup.setOnClickListener { onBackPressed() }
        nextUserBtn.setOnClickListener {
            if (fields.validateFields()){
                 name(pfNameDt.text)
                cpf(pfCpfDt.text)
                phone(pfPhoneDt.text)
                birthDate(pfBirthdayDt.text)
                email(pfEmailDt.text)
                startPassword()
            }
        }
    }

    private fun startPassword(){
        val intent = Intent(this, PasswordActivity::class.java).apply{}
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
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

