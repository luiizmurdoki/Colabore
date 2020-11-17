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
    private lateinit var userLocation: Location

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
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFull) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    fun setListerners(){
        include2.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override  fun displayLoading(close : Boolean){
        if(close) progressDialog.dialog.dismiss()
        else progressDialog.show(this,"Perai Carai...")
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val user = LatLng(-23.6090687,-46.7693424)
        val ong = LatLng(-23.552787, -46.835511)
        val ong2 = LatLng(-15.75177, -47.886191)
        val ong3 = LatLng(-23.574594, -46.652353)
        val ong4 = LatLng(-23.597009, -46.651701)
        val ong5 = LatLng(-22.962284, -43.219116)
        val ong6 = LatLng(-22.981056, -43.199074)
        val ong7 = LatLng(-22.952295, -43.190975)
        val ong8 = LatLng(-23.596151, 46.67373)
        val ong9 = LatLng(-23.554154, -46.662237)


        mMap.addMarker(MarkerOptions().position(user).title("Voce esta aqui"))
        mMap.addMarker(MarkerOptions().position(ong).title("CIDAP"))
        mMap.addMarker(MarkerOptions().position(ong2).title("IPAM"))
        mMap.addMarker(MarkerOptions().position(ong3).title("Transparência Brasil"))
        mMap.addMarker(MarkerOptions().position(ong4).title("AACD"))
        mMap.addMarker(MarkerOptions().position(ong5).title("Instituto da Criança"))
        mMap.addMarker(MarkerOptions().position(ong6).title("Viva Rio"))
        mMap.addMarker(MarkerOptions().position(ong7).title("Saúde Criança"))
        mMap.addMarker(MarkerOptions().position(ong8).title("Fundação Abrinq"))
        mMap.addMarker(MarkerOptions().position(ong9).title("Vetor Brasil"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 12f))
    }
}