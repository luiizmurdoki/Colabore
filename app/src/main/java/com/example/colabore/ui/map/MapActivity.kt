package com.example.colabore.ui.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialog.LoadingDialog
import com.example.colabore.ui.dialog.MessageBottomDialog
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

class MapActivity :  BaseActivity(), MapContract.View , OnMapReadyCallback {
    private lateinit var auth: FirebaseAuth
    private val progressDialog = LoadingDialog()
    private lateinit var locations : List<CardModel>
    private lateinit var locationManager : LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap

    private val presenter: MapContract.Presenter by lazy {
        MapPresenter().apply { attachView(this@MapActivity) }
    }

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

    override fun handleLocation(location: List<CardModel>){
        locations = location
        setMapper()
    }


    private fun setMapper(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFull) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locations.run {
            locations.forEach {
                mMap = googleMap
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            it.latitude.toDouble(),
                            it.longitude.toDouble()
                        )
                    ).title(it.nome)
                )
            }
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)  == PackageManager.PERMISSION_DENIED) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object :
                LocationListener {
                override fun onLocationChanged(p0: Location) {
                    mMap.addMarker(MarkerOptions().position(LatLng(p0.latitude, p0.longitude)).title(getString(R.string.current_location)))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0.latitude, p0.longitude), 12f))
                }
                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String?) {}
                override fun onProviderDisabled(provider: String?) {}
            })
        }
    }
}