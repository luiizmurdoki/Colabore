package com.example.colabore.ui.finish

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.login.LoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_finish_signup.*

class FinishActivity :  BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var value: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_signup)
        auth = FirebaseAuth.getInstance()
        setListerners()
        FirebaseApp.initializeApp(this)

    }

    private fun setListerners(){
        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply{}
            startActivity(intent)
            finishAffinity()
        }
    }

}