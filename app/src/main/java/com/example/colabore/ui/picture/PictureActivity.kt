package com.example.colabore.ui.picture

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.colabore.R
import com.example.colabore.model.PictureProfileModel
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.main.MainActivity
import com.example.colabore.ui.picture.adapter.PictureAdapter
import com.example.colabore.ui.profile.ProfileActivity
import com.example.colabore.utils.AdapterOnClick
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.item_card.*

class PictureActivity : BaseActivity(), PictureContract.View , AdapterOnClick {

        private lateinit var auth: FirebaseAuth
        private val progressDialog = LoadingDialog()
        private val presenter: PictureContract.Presenter by lazy {
            PicturePresenter().apply { attachView(this@PictureActivity) }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_picture)
            auth = FirebaseAuth.getInstance()
            FirebaseApp.initializeApp(this)
            setListeners()
            presenter.loadPicture()
        }

        private val gridAdapter by lazy {
            val layoutManager = GridLayoutManager(this,3)
            val adapter = PictureAdapter(this , this)
            pictureRv.setup(adapter, layoutManager)
            pictureRv.isNestedScrollingEnabled = false
            adapter
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
            toolbar.setOnClickListener { startProfile() }
        }

        private fun startProfile() {
        val intent = Intent(this, ProfileActivity::class.java).apply{}
        startActivity(intent)
        finish()
        }

        override fun startHome() {
        val intent = Intent(this, MainActivity::class.java).apply{}
        startActivity(intent)
        finish()
        }


    override fun onClick(item: Any , item2: Any) {
        presenter.setPicture(item.toString() , face = item2.toString())
        Toast.makeText(context, "Foto registrada", Toast.LENGTH_LONG).show()
    }

        override fun displayPictures(items: List<PictureProfileModel>) {
            gridAdapter.list = items
        }

        override fun onBackPressed() {
            super.onBackPressed()
        }

        override  fun displayLoading(close : Boolean){
            if(close) progressDialog.dialog.dismiss()
            else progressDialog.show(this)
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