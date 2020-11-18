package com.example.colabore.ui.descriptionEvent

import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_description_event.*
import kotlinx.android.synthetic.main.activity_ngo_description.drescripitionTv

class DescrptionEventActivity :  BaseActivity(), DescriptionEventContract.View {
        private lateinit var auth: FirebaseAuth
        private lateinit var ong: String
        private lateinit var title: String
        private lateinit var mensagem: String


        private val presenter: DescriptionEventContract.Presenter by lazy {
            DescriptionEventPresenter().apply { attachView(this@DescrptionEventActivity) }
        }

        private lateinit var fusedLocationClient: FusedLocationProviderClient


        private lateinit var mMap: GoogleMap

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_description_event)
            auth = FirebaseAuth.getInstance()
            setListerners()
            getExtras()
            displayDescription()
            FirebaseApp.initializeApp(this)
        }

        fun setListerners(){
            includeEvent.setOnClickListener {
                onBackPressed()
            }
        }

        private fun getExtras(){
            ong = intent.getStringExtra("ong")
            title = intent.getStringExtra("titulo")
            mensagem = intent.getStringExtra("mensagem")
        }


        private fun displayDescription(){
            ngoNameEvent.text = ong
            titleNEvent.text = title
            drescripitionTv.text = mensagem
        }

        override fun onBackPressed() {
            super.onBackPressed()
        }

        override fun displayError(msg: String?){
            MessageBottomDialog(this, getString(R.string.placeholder_error_title),
                if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
                getString(R.string.action_ok), {}, null, {}, isCancelable = true
            ).show()
        }

}