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

        }
    }

   /* private fun detectRoot(): Boolean {
        val rootBeer = RootBeer(applicationContext)
        var rootDetected = false

        if (rootBeer.detectRootManagementApps()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_MANAGEMENT_APPS)
        }
        if (rootBeer.detectPotentiallyDangerousApps()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_DANGEROUS_APPS)
        }
        if (rootBeer.checkForBinary(Const.BINARY_SU)) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_BINARY_SU)
        }
        if (rootBeer.checkForDangerousProps()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_DANGEROUS_PROPS)
        }
        if (rootBeer.checkForRWPaths()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_RW_PATHS)
        }
        if (rootBeer.detectTestKeys()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_TEST_KEYS)
        }
        if (rootBeer.checkSuExists()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_SU_FILE)
        }
        if (rootBeer.checkForRootNative()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_ROOT_NATIVE)
        }
        if (rootBeer.checkForMagiskBinary()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_MAGISK_BINARY)
        }
        if (rootBeer.checkForBusyBoxBinary()) {
//            rootDetected = true
            BBCAnalytics.logEvent(ROOT_DETECTED, ROOT_INDICATOR_BUSYBOX_BINARY)
        }

        return false
    }*/
