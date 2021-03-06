package com.example.colabore.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colabore.R
import com.example.colabore.model.PreferencesHelper
import com.example.colabore.ui.login.LoginActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        PreferencesHelper.init(this)
        startLogin()

    }
    private fun startLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply{}
        startActivity(intent)
        finish()
        }
    }
