package com.example.colabore.utils.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Looper
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest

/**
 * Why I'm using Fused Location Provider API
 * https://bit.ly/2LdVejr
 *
 * @param listener The Activity that implements [OnLocationChangedListener],
 * If a fragment is using the location function, the parent activity must be pass.
 * @see [OnLocationChangedListener.getActivityContext]
 *
 * @param locationRequest A data object that contains quality of service parameters for requests to the [FusedLocationProviderClient],
 * It can be created using the [LocationHelper.createLocationRequest] method, that return 4 pre defined requests, based on Google Doc.
 * If none of the 4 are useful for the specific case, you can create a [LocationRequest] by your own.
 * The default request is 'high', if none is passed.
 */
class LocationHelper(private val listener: OnLocationChangedListener,
                     private val locationRequest: LocationRequest = createLocationRequest(
                         RequestType.High()
                     )
) {

    val context: Context = listener.getActivityContext()

    private val googleApiClient: GoogleApiClient by lazy {
        GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .build()
    }

    private val settingsClient: SettingsClient by lazy {
        LocationServices.getSettingsClient(context)
    }

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    private val locationSettingsRequest: LocationSettingsRequest by lazy {
        createLocationSettingRequest(locationRequest)
    }

    init {
        googleApiClient.connect()
    }

    private val locationCallback: LocationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)
                result?.let { r ->
                    r.lastLocation?.let {
                        Log.d(TAG, "Lat: ${it.latitude} && Long: ${it.longitude}")
                        listener.onLocationChanged(location = it)
                    }
                }
            }
        }
    }

    private fun createLocationSettingRequest(request: LocationRequest): LocationSettingsRequest {
        return LocationSettingsRequest.Builder().apply {
            addLocationRequest(request)
            setAlwaysShow(true)
        }.build()
    }

    /**
     * Permissions must be handled by Permission Class
     */
    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        settingsClient.checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener {
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                            locationCallback, Looper.myLooper())
                    listener.onGoogleServicesAllowed()
                    Log.d(TAG, "Sucesso")
                }
                .addOnFailureListener {
                    Log.d(TAG, "Erro")
                    handleLocationUpdateError(it)
                }
    }

    private fun handleLocationUpdateError(it: Exception) {
        when ((it as ApiException).statusCode) {
            CommonStatusCodes.RESOLUTION_REQUIRED -> {
                //SHOW RESOLUTION IF IT'S ON FOREGROUND
                val exception = it as ResolvableApiException
                if (context is Activity)
                    exception.startResolutionForResult(context, REQUEST_CODE_LOCATION_SERVICE)
                else exception.printStackTrace()
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int) {
        if (requestCode == REQUEST_CODE_LOCATION_SERVICE) {
            when (resultCode) {
                Activity.RESULT_OK -> startLocationUpdates()
                Activity.RESULT_CANCELED -> listener.onGoogleServicesDenied()
            }
        }
    }

    fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        googleApiClient.disconnect()
    }

    /**
     * Request params are based according Google Docs.
     * The types that contain params can be override as its needed.
     *
     * https://bit.ly/2n4Na9j
     */
    sealed class RequestType {
        data class High(val interval: Long = 5000L, val numUpdates: Int? = null) : RequestType()
        data class Balanced(val interval: Long = 60000L, val fastestInterval: Long = 60000L) : RequestType()
        data class Low(val interval: Long = 30000L) : RequestType()
        object NoPower : RequestType()
    }

    companion object {
        private const val REQUEST_CODE_LOCATION_SERVICE = 20
        private const val TAG = "LocationHelper"

        fun createLocationRequest(type: RequestType): LocationRequest =
                when (type) {
                    is RequestType.High -> LocationRequest().apply {
                        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        interval = type.interval
                        type.numUpdates?.let { numUpdates = it }
                    }
                    is RequestType.Balanced -> LocationRequest().apply {
                        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
                        interval = type.interval
                        fastestInterval = type.fastestInterval
                    }
                    is RequestType.Low -> LocationRequest().apply {
                        priority = LocationRequest.PRIORITY_LOW_POWER
                        interval = type.interval
                    }
                    is RequestType.NoPower -> LocationRequest().apply {
                        priority = LocationRequest.PRIORITY_NO_POWER
                    }
                }
    }
}