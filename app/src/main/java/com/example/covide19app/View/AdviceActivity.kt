package com.example.covide19app.View

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Adapter.AdviceAdapter
import com.example.covide19app.Adapter.CountryAdapter
import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.AdvicesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdviceActivity : AppCompatActivity() {
    lateinit var advicerecy : RecyclerView
    lateinit var adviceAdapter: AdviceAdapter
    val viewmodel: AdvicesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advice)
        activityDesign()
        contentview()
    }
    fun contentview(){
        viewmodel.getAllCountries()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        ///////////
        advicerecy=findViewById(R.id.adviceRecy)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        advicerecy.setLayoutManager(linearLayoutManager)
    }
    fun activityDesign() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.statusBarColor = resources.getColor(R.color.major_back)
            window.navigationBarColor = resources.getColor(R.color.major_back)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
    fun listentoObservables(){
        viewmodel.dataset.observe(this, Observer {
            when(it){
                is DataState.Success<List<AdvicesModel>> -> {
                    adviceAdapter=AdviceAdapter(it.data)
                    advicerecy.setAdapter(adviceAdapter)
                    adviceAdapter.notifyDataSetChanged()
                }
                is DataState.Error -> {
                    Log.e("errorstatistics",it.exception.toString())
                }
                is DataState.Loading -> {}
            }
        })
    }
}