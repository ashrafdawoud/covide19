package com.example.covide19app.ViewMaps

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.covide19app.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions


class InfectedMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infected_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        activityDesign()
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
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_night))
            if (!success) {
                Log.e("added", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("added", "Can't find style. Error: ", e)
        }
        // Add a marker in Sydney and move the camera
        val circle: Circle = mMap.addCircle(CircleOptions()
                .center(LatLng(30.033333, 31.533334))
                .radius(10000.0)
                .strokeColor(Color.RED)
                .fillColor(Color.RED))
        val circle2: Circle = mMap.addCircle(CircleOptions()
                .center(LatLng(30.533333, 31.133334))
                .radius(10000.0)
                .strokeColor(Color.RED)
                .fillColor(Color.RED))
        val sydney = LatLng(30.033333, 31.533334)
        val zoomLevel = 6.0f //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
    }
    fun back2(view: View?) {
        finish()
    }

    fun activityDesign() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.statusBarColor = resources.getColor(R.color.water)
            window.navigationBarColor = resources.getColor(R.color.water)
        }
    }
}