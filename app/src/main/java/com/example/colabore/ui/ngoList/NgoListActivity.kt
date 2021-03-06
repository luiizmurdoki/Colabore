package com.example.colabore.ui.ngoList

import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.ngoList.adapter.NgoAdapter
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ngo_list.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.apache.commons.lang3.StringUtils

class NgoListActivity :  BaseActivity(), NgoListContract.View {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()


    private val presenter: NgoListContract.Presenter by lazy {
        NgoListPresenter().apply { attachView(this@NgoListActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_list)
        presenter.loadBanners()
        setListeners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    private fun setListeners(){
        toolbar.setOnClickListener { onBackPressed() }
        setTitle("Encontrar ONG")
    }

    private val cardAdapter by lazy {
        val adapter = NgoAdapter(this)
        ngoDetails.setup(adapter)
        ngoDetails.isNestedScrollingEnabled = false
        adapter
    }

    override fun displayCards(items: List<CardModel>) {
        cardAdapter.list = items
    }

    override  fun displayLoading(close : Boolean){
        if(close) progressDialog.dialog.dismiss()
        else progressDialog.show(this)
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