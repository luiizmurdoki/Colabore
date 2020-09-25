package com.example.colabore.ui.description

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ngo_description.*


class DescriptionActivity :  BaseActivity(), DescriptionContract.View {
    private lateinit var auth: FirebaseAuth
    private lateinit var url: String
    private lateinit var title: String
    private lateinit var desc: String


    private val presenter: DescriptionContract.Presenter by lazy {
        DescriptionPresenter().apply { attachView(this@DescriptionActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_description)
        auth = FirebaseAuth.getInstance()
        getExtras()
        displayDescription()
        FirebaseApp.initializeApp(this)

    }

    private fun getExtras(){
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("nome")
        desc = intent.getStringExtra("info")
    }


    private fun displayDescription(){
        Glide.with(this).load(url).apply(
            RequestOptions().error(R.drawable.ic_default_empty).placeholder(
                R.drawable.ic_default_empty
            )
        ).into(imageNgoIv)
        ngoName.text = title.toString()
        drescripitionTv.text = desc.toString()
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }
}
