package com.example.covide19app.ViewMaps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.Utils.GpsTracker
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
    ///////////
    lateinit var name:TextView
    lateinit var phone:TextView
    lateinit var address:TextView
    lateinit var status:TextView
    //////////////////////////
    //////test code/////////////
    private var gpsTracker: GpsTracker? = null
    private var tvLatitude: TextView? = null
    private  var tvLongitude:TextView? = null
    ////////////////////////////
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
        name=findViewById(R.id.name)
        phone=findViewById(R.id.phone)
        address=findViewById(R.id.address)
        status=findViewById(R.id.status)
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
        locationtest()
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
    fun listObserverdata(){
        viewmodel.dataset.observe(this, Observer {
            when (it) {
                is DataState.Success<List<PlacesModel>> -> {
                    placescopy = it.data
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
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        val position = p0?.position
        for (item in placescopy){
            Log.e("mapclicked", "${item.long.toDouble()}  " + position?.latitude)
            if (item.long.toDouble()==position?.latitude){
                Log.e("mapclicked2", "true")
                name.setText(item.name)
                phone.setText(item.phone)
                address.setText(item.address)
                if (item.status.equals("opened")) {
                    status.setText(item.status)
                    status.setTextColor(Color.GREEN)
                }
                else{
                    status.setText(item.status)
                    status.setTextColor(Color.RED)
                }
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
    fun locationtest(){
        try {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
            }else{
                getloactionTest()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getloactionTest(){
        gpsTracker = GpsTracker(this)
        Log.e("loaction_success","called")
        if (gpsTracker!!.canGetLocation()) {
            val latitude = gpsTracker!!.latitude
            val longitude = gpsTracker!!.longitude
            //tvLatitude!!.text = latitude.toString()
            //tvLongitude!!.text = longitude.toString()
            Log.e("loaction_success",latitude.toString() +" " +longitude.toString())
            val sydney = LatLng(latitude, longitude)
            val marker:MarkerOptions=MarkerOptions().position(sydney).title("Location")
            marker.icon(bitmapDescriptorFromVector(this, R.drawable.ic_current_location))
            val zoomLevel = 21.0f //This goes up to 21
            mMap.addMarker(marker)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f))
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        } else {
            gpsTracker!!.showSettingsAlert(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> {
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    //If user presses allow
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
                    getloactionTest()
                } else {
                    //If user presses deny
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    locationtest()
                }
            }
        }
    }
}