package com.example.colabore.ui.confirm

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.ticket.TicketActivity
import com.example.colabore.ui.value.ValueActivity
import com.example.colabore.utils.extension.addCurrencyMask
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.acitvity_donation_value.*
import kotlinx.android.synthetic.main.acitvity_donation_value.toolbar
import kotlinx.android.synthetic.main.activity_confirm_value.*
import kotlinx.android.synthetic.main.activity_ngo_description.*
import kotlinx.android.synthetic.main.item_card.view.*

class ConfirmValueActivity :  BaseActivity(), ConfirmValueContract.View{

    private lateinit var auth: FirebaseAuth
    private lateinit var url: String
    private lateinit var title: String
    private lateinit var desc: String
    private lateinit var value: String


    private val presenter: ConfirmValueContract.Presenter by lazy {
        ConfirmValuePresenter().apply { attachView(this@ConfirmValueActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_value)
        auth = FirebaseAuth.getInstance()
        setListerners()
        getExtras()
        setView()
        FirebaseApp.initializeApp(this)

    }

    fun setListerners(){
        toolbar.setOnClickListener {
            onBackPressed()
        }
        confirmValueNextBtn.setOnClickListener {
            val intent = Intent(context, TicketActivity::class.java)
            intent.putExtra("valor", value)
            context.startActivity(intent)
        }
    }

    private fun setView() {
        valueTv.text = value
        nameNgoTv.text = title
        Glide.with(this).load(url).apply(
            RequestOptions().error(R.drawable.ic_default_empty).placeholder(
                R.drawable.ic_default_empty
            )
        ).into(ngoImageIv)
    }

    private fun getExtras(){
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("nome")
        desc = intent.getStringExtra("info")
        value = intent.getStringExtra("valor")
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