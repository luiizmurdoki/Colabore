package com.example.colabore.ui.signUpNgo

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.password.PasswordActivity
import com.example.colabore.ui.widget.DefaultEditText
import com.example.colabore.utils.extension.validateFields
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_ngo_signup.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.toolbarSingup

class SignUpNgoActivity :  BaseActivity(), SignUpNgoContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var fields: List<DefaultEditText>
    private val presenter: SignUpNgoContract.Presenter by lazy {
        SignUpNgoPresenter().apply { attachView(this@SignUpNgoActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_signup)
        setListeners()
        auth = FirebaseAuth.getInstance()
        fields = listOf(NameNgoEt,CnpjEt,PhoneEt,addressNgoEt,categoryEt,imgUrlEt,infoEt)
        FirebaseApp.initializeApp(this)
    }


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    private fun setListeners(){
        toolbarSingup.setOnClickListener { onBackPressed() }
        nextNgoBtn.setOnClickListener {
            if (fields.validateFields()){
                PersistUserInformation.name(NameNgoEt.text)
                PersistUserInformation.cnpj(CnpjEt.text)
                PersistUserInformation.phone(PhoneEt.text)
                PersistUserInformation.endereco(addressNgoEt.text)
                PersistUserInformation.categoria(categoryEt.text)
                PersistUserInformation.imageUrl(imgUrlEt.text)
                PersistUserInformation.info(infoEt.text)
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
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

}
