package com.example.colabore.ui.value

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.confirm.ConfirmValueActivity
import com.example.colabore.ui.description.DescriptionContract
import com.example.colabore.ui.description.DescriptionPresenter
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.widget.DefaultEditText
import com.example.colabore.utils.extension.addCurrencyMask
import com.example.colabore.utils.extension.getFloatValue
import com.example.colabore.utils.extension.validateFields
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.acitvity_donation_value.*
import kotlinx.android.synthetic.main.activity_ngo_description.*
import kotlinx.android.synthetic.main.activity_ngo_description.include2

class ValueActivity:  BaseActivity(), DescriptionContract.View {
    private lateinit var auth: FirebaseAuth
    private lateinit var url: String
    private lateinit var title: String
    private lateinit var desc: String
    private lateinit var fields: List<DefaultEditText>


    private val presenter: DescriptionContract.Presenter by lazy {
        DescriptionPresenter().apply { attachView(this@ValueActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_donation_value)
        auth = FirebaseAuth.getInstance()
        setListerners()
        fields = listOf(transferValueEt)
        setView()
        getExtras()
        FirebaseApp.initializeApp(this)

    }

    fun setListerners() {
        toolbar.setOnClickListener {
            onBackPressed()
        }
        transferValueNextBtn.setOnClickListener {
            if (fields.validateFields()) if (transferValueEt.text.getFloatValue() > 20) {
                val value = transferValueEt.text
                val intent = Intent(context, ConfirmValueActivity::class.java)
                intent.putExtra("nome", title)
                intent.putExtra("info", desc)
                intent.putExtra("url", url)
                intent.putExtra("valor", value)
                context.startActivity(intent)
            }
        }
    }

    private fun setView() {
        transferValueEt.getEditText().addCurrencyMask(true)
    }

    private fun getExtras(){
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("nome")
        desc = intent.getStringExtra("info")
        url = intent.getStringExtra("url")
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