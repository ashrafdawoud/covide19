package com.example.covide19app.View

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Adapter.CountryAdapter
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.CountryViewModel
import com.example.covide19app.databinding.ActivityStatisticsBinding
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.charts.BarChart
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel
@AndroidEntryPoint
class StatisticsActivity : AppCompatActivity() {
    var death:Int=0
    var recoverd:Int=0
    var total:Int=0
    lateinit var countryRecyclerview:RecyclerView
    lateinit var binding:ActivityStatisticsBinding
    lateinit var countryAdapter: CountryAdapter
    val viewmodel:CountryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_statistics)
        activityDesign()
        contentview()
    }

    fun contentview() {
        viewmodel.getAllCountries()
        setup_recyclerview()
        getObserverData()
        ///////////////////////
        val bundel=intent.extras
        death=bundel?.getInt("death",0)!!
        recoverd=bundel?.getInt("recoverd",0)!!
        total=bundel?.getInt("total",0)!!
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val mPieChart = findViewById<View>(R.id.piechart) as PieChart
        mPieChart.addPieSlice(PieModel("death", death.toFloat(), Color.parseColor("#F10000")))
        mPieChart.addPieSlice(PieModel("recoverd", recoverd.toFloat(), Color.parseColor("#25CDFF")))
        mPieChart.startAnimation()
        binding.death.setText(death.toString())
        binding.totoal.setText(total.toString())
        binding.recoverd.setText(recoverd.toString())


    }
    fun getObserverData(){
        viewmodel.dataset.observe(this, Observer {
            when(it){
                is DataState.Success<List<CountriesModel>> -> {
                    countryAdapter= CountryAdapter(it.data)
                    countryRecyclerview.adapter=countryAdapter
                    countryAdapter.notifyDataSetChanged()
                }
                is DataState.Error -> {
                    Log.e("errorstatistics",it.exception.toString())
                }
                is DataState.Loading -> {}
            }
        })
    }
    fun  setup_recyclerview(){
        countryRecyclerview=findViewById(R.id.countryRecy)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
        )
        countryRecyclerview.layoutManager = layoutManager
        countryRecyclerview.hasFixedSize()
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
}