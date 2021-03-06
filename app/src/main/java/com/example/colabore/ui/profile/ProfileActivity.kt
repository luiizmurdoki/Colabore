package com.example.colabore.ui.profile

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.history.HistoryActivity
import com.example.colabore.ui.login.LoginActivity
import com.example.colabore.ui.main.MainActivity
import com.example.colabore.ui.picture.PictureActivity

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.homeHeaderAvatarIv
import kotlinx.android.synthetic.main.home_header_view.*

class ProfileActivity:  BaseActivity(), ProfileContract.View {

    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private val presenter: ProfileContract.Presenter by lazy {
        ProfilePresenter().apply { attachView(this@ProfileActivity) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        presenter.getDataUser()
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.getCurrentUser()
    }

    override  fun displayLoading(close : Boolean){
        if(close) progressDialog.dialog.dismiss()
        else progressDialog.show(this)
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners(){
        logOutBtn.setOnClickListener { startLogin() }
        editPictureIv.setOnClickListener {startPictures()  }
        historicBtn.setOnClickListener {startHistory()  }
        include3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply{}
            startActivity(intent)
            finish() }
    }

    override fun displayImage(imageUrl:String?){
        Glide.with(this).load(imageUrl).apply(
            RequestOptions().error(R.drawable.ic_face_nothing).placeholder(
                R.drawable.ic_face_nothing
            )
        ).into(homeHeaderAvatarIv)
    }


    private fun startLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply{}
        startActivity(intent)
        finishAffinity()
    }

    private fun startHistory() {
        val intent = Intent(this, HistoryActivity::class.java).apply{}
        startActivity(intent)
    }

    private fun startPictures() {
        val intent = Intent(this, PictureActivity::class.java).apply{}
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

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg                              ,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

}