package com.example.colabore.ui.main

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
import com.example.colabore.ui.eventUser.EventUserActivity
import com.example.colabore.ui.login.LoginActivity
import com.example.colabore.ui.main.adapter.MainCardAdapter
import com.example.colabore.ui.map.MapActivity
import com.example.colabore.ui.ngoList.NgoListActivity
import com.example.colabore.ui.profile.ProfileActivity
import com.example.colabore.utils.extension.setup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_header_view.*
import kotlinx.android.synthetic.main.item_picture_grid.view.*

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
        setListerns()
        presenter.loadBanners()
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
    }


    private val cardAdapter by lazy {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = MainCardAdapter(this)
        bannersRv.setup(adapter, layoutManager)
        bannersRv.isNestedScrollingEnabled = false
        adapter
    }

    private fun setListerns(){
        donationCv.setOnClickListener {
            val intent = Intent(this, NgoListActivity::class.java).apply{}
            startActivity(intent)
        }
        homeHeaderAvatarIv.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java).apply{}
            startActivity(intent)
            finish()
        }
        exploreCv.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java).apply{}
            startActivity(intent)
        }
        eventHomeCv.setOnClickListener{
            val intent = Intent(this, EventUserActivity::class.java).apply{}
            startActivity(intent)
        }

    }

    override fun displayName(name:String?, imageUrl:String?){
        homeHeaderNameTv.text = name
        Glide.with(this).load(imageUrl).apply(
            RequestOptions().error(R.drawable.ic_face_nothing).placeholder(
                R.drawable.ic_face_nothing
            )
        ).into(homeHeaderAvatarIv)
    }

    override fun displayCards(items: List<CardModel>) {
        cardAdapter.list = items
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
