package com.example.covide19app.View

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Adapter.DoctorAdapter
import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.DoctorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class DoctorsListActivity : AppCompatActivity() {
    lateinit var doctorrecy:RecyclerView
    val viewmodel:DoctorsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors_list)
        activityDesign()
        contentview()
    }
    fun contentview(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        ///////////
        viewmodel.getAllCountries()
        doctorrecy=findViewById(R.id.doctors)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        doctorrecy.setLayoutManager(linearLayoutManager)
        observable()
    }
    fun activityDesign() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.statusBarColor = resources.getColor(R.color.major_back)
            window.navigationBarColor = resources.getColor(R.color.major_back)
        }
    }
    fun observable(){
        viewmodel.dataset.observe(this, androidx.lifecycle.Observer {
            when (it) {

                is DataState.Success<List<DoctorsModel>> -> {
                    Log.e("errrrr",it.data.size.toString())
                    var doctorAdapter=DoctorAdapter(this,it.data)
                    doctorrecy.adapter=doctorAdapter
                    doctorAdapter.notifyDataSetChanged()
                }
                is DataState.Error -> {
                    Toast.makeText(this, "Error " + it.exception.message, Toast.LENGTH_LONG)
                            .show()
                    Log.e("errrrr",it.exception.message.toString())
                }
                is DataState.Loading -> {
                    Log.e("errrrr","loading")
                }
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}