package com.example.colabore.ui.history

import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.HistoryModel
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity :  BaseActivity(), HistoryContract.View {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()


    private val presenter: HistoryContract.Presenter by lazy {
        HistoryPresenter().apply { attachView(this@HistoryActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        presenter.loadHistory()
        setListeners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    private fun setListeners(){
        toolbarHistory.setOnClickListener { onBackPressed() }
    }

    private val historyAdapter by lazy {
        val adapter = HistoryAdapter(this)
       historyDetails.setup(adapter)
       historyDetails.isNestedScrollingEnabled = false
        adapter
    }

    override fun displayHistory(items: List<HistoryModel>) {
        historyAdapter.list = items
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