package com.example.googlemapsactivityexample

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "MapsActivity"
    private lateinit var mMap: GoogleMap
    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

/*        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        var mapOptions = MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney")

        mMap.addMarker(mapOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/



/*        // Add a marker in Sydney and animate the camera with zoom level 16
        val sydney = LatLng(-34.0, 151.0)

        val markerOptions = MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney")
            .snippet("Interesting place!")

        // You can change the MAP type to SATELLITE, HYBRID, TERRAIN, NORMAL etc.
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        mMap.addMarker(markerOptions)

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 20f)
        mMap.animateCamera(cameraUpdate)*/


        geocoder = Geocoder(this)

        try {
            val addressList = geocoder.getFromLocationName("Boston", 1)

            if (addressList.size > 0){

                val address = addressList[0]
                Log.d(TAG, "$address")
                // the address looks like below:
                // Address[addressLines=[0:"Boston"],
                // feature=Boston,admin=Massachusetts,sub-admin=Suffolk County,locality=Boston,
                // thoroughfare=null,postalCode=null,countryCode=US,countryName=United States,
                // hasLatitude=true,latitude=42.3600825,hasLongitude=true,
                // longitude=-71.0588801,phone=null,url=null,extras=null]

                // Convert to latitude and latitude to LatLng
                val latLng = LatLng(address.latitude, address.longitude)

                // Set the marker options
                val markerOptions = MarkerOptions()
                    .position(latLng)
                    .title(address.locality)

                // Add the marker
                mMap.addMarker(markerOptions)
                // Move the camera with a zoom level 16f
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))

            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
        }


    }
}
