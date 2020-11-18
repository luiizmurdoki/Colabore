package com.example.colabore.ui.mainNgo

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.event.EventActivity
import com.example.colabore.ui.historyNgo.HistoryNgoActivity
import com.example.colabore.ui.login.LoginActivity
import com.example.colabore.ui.main.adapter.MainCardAdapter
import com.example.colabore.ui.map.MapActivity
import com.example.colabore.ui.ngoList.NgoListActivity
import com.example.colabore.ui.profile.ProfileActivity
import com.example.colabore.utils.extension.setVisible
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.donationCv
import kotlinx.android.synthetic.main.activity_main.exploreCv
import kotlinx.android.synthetic.main.activity_ngo_main.*
import kotlinx.android.synthetic.main.home_header_view.*
import kotlinx.android.synthetic.main.home_header_view.cardView

class MainNgoActivity :  BaseActivity(), MainNgoContract.View {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()


    private val presenter: MainNgoContract.Presenter by lazy {
        MainNgoPresenter().apply { attachView(this@MainNgoActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_main)
        setListerns()
        setDisplay()
        presenter.loadData(cpf())
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }

    private fun setDisplay(){
        cardView.setVisible(false)
        saudation.text = getText(R.string.home_header_saudation)
    }

    private fun setListerns(){
        eventCv.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java).apply{}
            startActivity(intent)
        }
        donationHistoryCv.setOnClickListener{
            val intent = Intent(this, HistoryNgoActivity::class.java).apply{}
            startActivity(intent)
        }
        logOutBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply{}
            startActivity(intent)
            finishAffinity()
        }

    }

    override fun displayData(image: String , name: String) {
        homeHeaderNameTv.text = name
        Glide.with(this).load(image).apply(
            RequestOptions().error(R.drawable.ic_face_nothing).placeholder(
                R.drawable.ic_face_nothing
            )
        ).into(homeNgoAvatarIv)
    }

    override  fun displayLoading(close : Boolean){
        if(close) progressDialog.dialog.dismiss()
        else progressDialog.show(this)
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }
}
