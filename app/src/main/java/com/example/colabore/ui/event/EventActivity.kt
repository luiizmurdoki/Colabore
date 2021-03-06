package com.example.colabore.ui.event

import android.content.Intent
import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.mainNgo.MainNgoActivity
import com.example.colabore.ui.profile.ProfileActivity
import com.example.colabore.ui.widget.DefaultEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_event.*


class EventActivity :  BaseActivity(), EventContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var fields: List<DefaultEditText>

    private val presenter: EventContract.Presenter by lazy {
        EventPresenter().apply { attachView(this@EventActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setListeners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    private fun setListeners(){
        toolbarEvent.setOnClickListener { onBackPressed() }
        eventBtn.setOnClickListener {
             presenter.setEvent(eventTitleEt.text , descriptionEventEt.text)
            }
    }

    private fun starHome(){
        val intent = Intent(this, MainNgoActivity::class.java).apply{}
        startActivity(intent)
        finish()
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


    override fun displaySucess(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_sucess),
            if (msg.isNullOrEmpty()) getString(R.string.sucess_event) else msg                              ,
            getString(R.string.action_ok), {starHome()}, null, {}, isCancelable = true
        ).show()
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg                              ,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

}