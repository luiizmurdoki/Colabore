package com.example.colabore.ui.ticket

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.main.MainActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_confirm_value.*
import kotlinx.android.synthetic.main.activity_ticket.*


class TicketActivity :  BaseActivity(), TicketContract.View{

        private lateinit var auth: FirebaseAuth

        private val presenter: TicketContract.Presenter by lazy {
            TicketPresenter().apply { attachView(this@TicketActivity) }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_ticket)
            auth = FirebaseAuth.getInstance()
            setListerners()
            FirebaseApp.initializeApp(this)

        }

        private fun setListerners(){
            homeBtn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java).apply{}
                startActivity(intent)
                finish()
            }
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(codeTicket.text, codeTicket.text)
            clipboard.setPrimaryClip(clip)
        }

        override fun displayError(msg: String?){
            MessageBottomDialog(this, getString(R.string.placeholder_error_title),
                if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
                getString(R.string.action_ok), {}, null, {}, isCancelable = true
            ).show()
        }
}