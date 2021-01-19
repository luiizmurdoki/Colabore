package com.example.colabore.utils.location

import android.content.Context
import android.location.Location

interface OnLocationChangedListener {

    /**
     * Return the context of the Activity that implements this listener.
     * To be able to start the Google Services Api objects its needed an valid context;
     *
     * Note: To solve the exception caught in checkLocationSettings() call, its needed an activity context.
     * So when handling with fragments, this method must return the activity context.
     */
    fun getActivityContext(): Context

    /**
     * This method notifies that google play services are enable.
     *
     * The Fused Location only works when user enable the Google Service Locations,
     * even user has already enable all location permissions, the location will only be available
     * if the Google Services Location is enable too.
     */
    fun onGoogleServicesAllowed()

    /**
     * This method notifies that google play services were not enable by user.
     * Here we can handle the best way to guide user.
     */
    fun onGoogleServicesDenied()

    /**
     * This notifies that a new location has been detected.
     *
     * @param location The new detected location.
     */
    fun onLocationChanged(location: Location)
}