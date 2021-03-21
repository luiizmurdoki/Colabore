package com.example.colabore.ui.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.description.DescriptionContract
import com.example.colabore.ui.description.DescriptionPresenter
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
import com.example.colabore.ui.value.ValueActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ngo_description.*

class MapActivity :  BaseActivity(), MapContract.View , OnMapReadyCallback {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var locations : List<CardModel>

    private val presenter: MapContract.Presenter by lazy {
        MapPresenter().apply { attachView(this@MapActivity) }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        auth = FirebaseAuth.getInstance()
        setListerners()
        FirebaseApp.initializeApp(this)
        presenter.getDataUser(PersistUserInformation.cpf())
    }

    fun setListerners(){
        include2.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
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

    private fun setMapper(){
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFull) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun handleLocation(location: List<CardModel>){
        locations = location
        setMapper()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        locations.run {
            locations.forEach {
                mMap = googleMap
                mMap.addMarker(MarkerOptions().position(LatLng(it.latitude.toDouble(), it.longitude.toDouble())).title(it.nome))
            }
            mMap.addMarker(MarkerOptions().position(LatLng(-23.6090687,-46.7693424)).title("Voce esta aqui"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.6090687,-46.7693424), 12f))
        }
    }
}