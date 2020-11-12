package com.example.colabore.ui.description

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.value.ValueActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ngo_description.*
import kotlinx.android.synthetic.main.partial_toolbar.*


class DescriptionActivity :  BaseActivity(), DescriptionContract.View , OnMapReadyCallback {
    private lateinit var auth: FirebaseAuth
    private lateinit var url: String
    private lateinit var title: String
    private lateinit var desc: String
    private lateinit var phone: String
    private lateinit var address: String
    private lateinit var lati: String
    private lateinit var longi: String


    private val presenter: DescriptionContract.Presenter by lazy {
        DescriptionPresenter().apply { attachView(this@DescriptionActivity) }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_description)
        auth = FirebaseAuth.getInstance()
        setListerners()
        getExtras()
        displayDescription()
        FirebaseApp.initializeApp(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    fun setListerners(){
        include2.setOnClickListener {
            onBackPressed()
        }
        ngoBtn.setOnClickListener {
            val intent = Intent(context, ValueActivity::class.java)
            intent.putExtra("nome", title)
            intent.putExtra("info", desc)
            intent.putExtra("url",url)
            context.startActivity(intent)
        }
    }

    private fun getExtras(){
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("nome")
        desc = intent.getStringExtra("info")
        phone = intent.getStringExtra("phone")
        address = intent.getStringExtra("address")
        lati = intent.getStringExtra("lati")
        longi = intent.getStringExtra("longi")
    }


    private fun displayDescription(){
        ngoName.text = title
        drescripitionTv.text = desc
        ngoPhone.text = phone
        ngoAddress.text = address
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val ong = LatLng(lati.toDouble(), longi.toDouble())
        mMap.addMarker(MarkerOptions().position(ong).title(title))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ong, 18f))
    }


}
