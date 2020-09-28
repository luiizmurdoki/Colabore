package com.example.colabore.ui.chooseOne

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.login.LoginActivity
import com.example.colabore.ui.main.MainActivity
import com.example.colabore.ui.signUp.SignUpActivity
import com.example.colabore.ui.splash.SplashActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_choose_one.*


class ChooseOneActivity :  BaseActivity(), ChooseOneContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private val presenter: ChooseOneContract.Presenter by lazy {
        ChooseOnePresenter().apply { attachView(this@ChooseOneActivity) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_one)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        setListeners()
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
        donorCv.setOnClickListener { openDonorSignUp() }
        ngoCv.setOnClickListener { openNgoSignUp() }
        include2.setOnClickListener { onBackPressed() }
    }

    private fun openDonorSignUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun openNgoSignUp(){
        /*val intent2 = Intent(this, SignUpActivity::class.java)
        startActivity(intent)*/
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

