package com.example.colabore.ui.eventUser

import android.os.Bundle
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.EventModel
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.eventUser.adapter.EventAdapter
import com.example.colabore.ui.ngoList.adapter.NgoAdapter
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ngo_list.*
import kotlinx.android.synthetic.main.activity_ngo_list.toolbar
import kotlinx.android.synthetic.main.activity_user_event.*

class EventUserActivity :  BaseActivity(), EventUserContract.View {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()


    private val presenter: EventUserContract.Presenter by lazy {
        EventUserPresenter().apply { attachView(this@EventUserActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_event)
        presenter.loadBanners()
        setListeners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    private fun setListeners(){
        toolbarUserEvent.setOnClickListener { onBackPressed() }
    }

    private val cardAdapter by lazy {
        val adapter = EventAdapter(this)
        bannersUserRv.setup(adapter)
        bannersUserRv.isNestedScrollingEnabled = false
        adapter
    }

    override fun displayCards(items: List<EventModel>) {
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