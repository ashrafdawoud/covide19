package com.example.covide19app.ViewMaps

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.covide19app.Model.InfectedPoapleModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.InfectedPoapleViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfectedMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val viewmodel:InfectedPoapleViewModel by viewModels()
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
        viewmodel.getInfected()
        listObserverdata()
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
    fun listObserverdata(){
        viewmodel.datasateReturn.observe(this, Observer {
            when (it) {
                is DataState.Success<List<InfectedPoapleModel>> -> {
                    for (placemodel in it.data) {
                        Log.e("datalist", it.data.size.toString())
                        setupMap(placemodel.long, placemodel.lat)
                    }
                }
                is DataState.Error -> {
                    Log.e("errormap", it.exception.toString())
                }
                is DataState.Loading -> {
                }
            }
        })
    }
    fun setupMap(long: String?, lang: String?){
        val sydney = LatLng(lang!!.toDouble(), long!!.toDouble())
        val marker: MarkerOptions = MarkerOptions().position(sydney).title("infected")
        marker.icon(bitmapDescriptorFromVector(this, R.drawable.ic_phrmacy))
        mMap.addMarker(marker)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(31.233334,30.033333), 4.0f))
    }
    private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
        val background = ContextCompat.getDrawable(context, R.drawable.background_marker)
        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(40, 20, vectorDrawable.intrinsicWidth + 40, vectorDrawable.intrinsicHeight + 20)
        val bitmap = Bitmap.createBitmap(background.intrinsicWidth, background.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)
        val smallMarker = Bitmap.createScaledBitmap(bitmap, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(smallMarker)
    }
}