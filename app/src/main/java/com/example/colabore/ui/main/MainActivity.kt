package com.example.colabore.ui.main

import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.UserObject
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialogmessage.MessageBottomDialog
import com.example.colabore.ui.login.LoginContract
import com.example.colabore.ui.login.LoginPresenter
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_header_view.*

class MainActivity :  BaseActivity(), MainContract.View {
    private lateinit var auth: FirebaseAuth


    private val presenter: MainContract.Presenter by lazy {
        MainPresenter().apply { attachView(this@MainActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getNameUser()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getNameUser()
    }

    override fun displayName(name:String?){
        homeHeaderNameTv.text = name
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }
}
