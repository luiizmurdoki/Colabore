package com.example.colabore.ui.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.colabore.model.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class BaseActivityconst val PERMISSION_REQUEST = 190

// ...
// Initialize Firebase Auth

abstract class BaseActivity : AppCompatActivity() {



    private lateinit var actionToExecute: (() -> Unit)

    val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FIX ORIENTATION TO PORTRAIT
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onPause() {
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        super.onPause()
    }

    override fun onResume() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //TOOLBAR METHODS
    fun setToolbar(displayHomeAsUpEnabled: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
    }

    //ACTION BAR METHODS
    fun setToolbar(title: String) {
        /*val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)*/
        setTitle(title)
    }

    //MENU METHODS
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                //this is used because when user hits home button the previous view is reconstructed
                //and when back button (at navbar) is pressed this doesn't happen,
                //so this makes the previous view never reconstructed when home is hit.
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

fun Context.createAppSettingsIntent() = Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.fromParts("package", packageName, null)
}