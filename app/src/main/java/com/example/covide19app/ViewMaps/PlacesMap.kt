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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.PlacesViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacesMap : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap
    lateinit var placescopy:List<PlacesModel>
    val viewmodel:PlacesViewModel by viewModels()
    lateinit var  bottomsheet:CoordinatorLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        bottomsheet=findViewById(R.id.bottomSheet)
        activityDesign()
        bottomsheet_setup()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        viewmodel.getPlacesData()
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
    private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
        val background = ContextCompat.getDrawable(context, R.drawable.ic_map_marker)
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
    fun listObserverdata(){
        viewmodel.dataset.observe(this, Observer {
            when (it) {
                is DataState.Success<List<PlacesModel>> -> {
                    placescopy=it.data
                    for (placemodel in it.data) {
                        setupMap(placemodel.long, placemodel.lang, placemodel.type, placemodel.address)
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
    fun setupMap(long: String, lang: String, type: String, address: String){
        val sydney = LatLng(long.toDouble(), lang.toDouble())
        val marker:MarkerOptions=MarkerOptions().position(sydney).title(type)
        if (type.equals("hospital"))
            marker.icon(bitmapDescriptorFromVector(this, R.drawable.ic_hospital))
        else if (type.equals("pharmacy"))
            marker.icon(bitmapDescriptorFromVector(this, R.drawable.ic_phrmacy))
        else if (type.equals("market"))
            marker.icon(bitmapDescriptorFromVector(this, R.drawable.ic_market))
        mMap.addMarker(marker)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        Log.e("mapclicked","true")
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        val position = p0?.position
        for (item in placescopy){
            if (item.lang.toDouble()==position?.latitude){

            }
        }
        return true
    }
    fun bottomsheet_setup(){
        //#2 Initializing the BottomSheetBehavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet)

        //#3 Listening to State Changes of BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        })
    }
}