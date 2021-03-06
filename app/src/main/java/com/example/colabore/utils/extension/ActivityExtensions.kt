package com.example.colabore.utils.extension

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.colabore.R
import com.example.colabore.ui.dialog.MessageBottomDialog
import java.io.Serializable

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
    }
}

fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Activity.startActivitySlideTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_close_scale, R.anim.slide_in_left, 1, requestCode)
}

fun Activity.startActivityFadeTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_fade_out, R.anim.anim_fade_in, 1, requestCode)
}

fun Activity.startActivityTransition(intent: Intent, idAnimationOut: Int,
                                     idAnimationIn: Int, delay: Long, requestCode: Int? = null) {
    if (requestCode == null) {
        Handler().postDelayed({
            this.startActivity(intent)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    } else {
        Handler().postDelayed({
            this.startActivityForResult(intent, requestCode)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    }
}

fun Activity.finishWithSlideTransition() {
    finish()
    overridePendingTransition(R.anim.anim_open_scale, R.anim.slide_out_right)
}

fun Activity.finishWithFadeTransition() {
    finish()
    overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
}

fun Activity.finishWithTransition(idAnimationOut: Int, idAnimationIn: Int, delay: Long) {
    Handler().postDelayed({
        this.finish()
        this.overridePendingTransition(idAnimationIn, idAnimationOut)
    }, delay)
}

fun <T : Serializable> Activity.getSerializable(key: String): T? {
    return intent.getSerializableExtra(key) as T?
}

fun Context.copyToClipboard(content: String) {
    val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val myClip = ClipData.newPlainText("message", content)

    clipBoard?.setPrimaryClip(myClip)
}

//ERROR
fun Context.showError(msg: String?,
                      title: String? = null,
                      positiveText: String? = null,
                      action: (() -> Unit)? = null,
                      isCancelable: Boolean = true) {

    fun showErrorDialog(){
        MessageBottomDialog(
                context = this,
                title = title ?: getString(R.string.placeholder_error_title),
                subtitle = msg ?: getString(R.string.placeholder_error_label),
                positiveText = positiveText ?: getString(R.string.action_ok),
                positiveAction = action,
                isCancelable = isCancelable
        ).show()
    }

    if (this is AppCompatActivity) {
        if (lifecycle.currentState != Lifecycle.State.DESTROYED)
            showErrorDialog()
    } else {
        showErrorDialog()
    }
}

//TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.showErrorToast(msg: String?) {
    showLongToast(msg ?: getString(R.string.placeholder_error_label))
}

fun Context.openVideoPlayer(video: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(video), "video/*")
    startActivity(intent)
}

/**
 * Try open an app using his package name,
 * if the app is not installed on device, it redirects to play store.
 *
 * @param packageName Package name of the app to open
 */
fun Activity.openExternalApp(packageName: String) {
    try {
        startActivity(packageManager.getLaunchIntentForPackage(packageName))
    } catch (e: Exception) {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
        startActivity(intent)
    }
}

fun Activity.openSettings(packageName: String? = null) {
    try {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", packageName ?: getPackageName(), null)
        })
    } catch (e: Exception) {
        Log.e("LOGHELPER", e.localizedMessage)
        showError(msg = getString(R.string.open_settings_error))
    }
}