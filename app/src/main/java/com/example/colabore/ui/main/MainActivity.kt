package com.example.colabore.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.main.adapter.MainCardAdapter
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_header_view.*

class MainActivity :  BaseActivity(), MainContract.View {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()


    private val presenter: MainContract.Presenter by lazy {
        MainPresenter().apply { attachView(this@MainActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getDataUser(cpf())
        presenter.loadBanners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    private val cardAdapter by lazy {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        val adapter = MainCardAdapter(this)
        bannersRv.setup(adapter, layoutManager)
        bannersRv.isNestedScrollingEnabled = false
        adapter
    }

    override fun displayName(name:String?){
        homeHeaderNameTv.text = name
    }

    override fun displayCards(items: List<CardModel>) {
        cardAdapter.list = items
    }

    override  fun displayLoading(close : Boolean){
        if(close) progressDialog.dialog.dismiss()
        else progressDialog.show(this,"Perai Carai...")
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }
}
