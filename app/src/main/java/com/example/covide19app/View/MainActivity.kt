package com.example.covide19app.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covide19app.Model.TotalCaseModel
import com.example.covide19app.R
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewMaps.InfectedMap
import com.example.covide19app.ViewMaps.PlacesMap
import com.example.covide19app.ViewModel.TotalCasesViewModel
import com.example.covide19app.databinding.ActivityMainBinding
import com.google.android.gms.common.data.DataBufferUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding:ActivityMainBinding
    val totalCasesViewModel: TotalCasesViewModel by viewModels()
     var death: Int =0
     var recoverd: Int= 0
     var total : Int= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityDesign()
        contentview()
    }

    fun contentview() {
        totalCasesViewModel.getTotalCases()
        listenToObservals()
    }

    fun listenToObservals() {
        totalCasesViewModel.dataset.observe(this, Observer {
            when (it){
                is DataState.Success<List<TotalCaseModel>> ->{
                    death=it.data.get(0).deaths.toInt()
                    recoverd=it.data.get(0).recovered.toInt()
                    total=it.data.get(0).confirmed.toInt()
                    activityMainBinding.numberOfIfected.setText(it.data.get(0).confirmed)
                    activityMainBinding.numberOfRecoverd.setText(it.data.get(0).recovered)
                }
                is DataState.Loading ->{

                }
                is DataState.Error->{
                    Log.e("erroronmainactivity",it.exception.toString())
                }
            }
        })
    }

    fun activityDesign() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.statusBarColor = resources.getColor(R.color.major_back)
            window.navigationBarColor = resources.getColor(R.color.major_back)
        }
    }

    fun logout(view: View?) {
        finishAffinity()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun advises(view: View?) {
        val intent = Intent(this, AdviceActivity::class.java)
        startActivity(intent)
    }

    fun registerAdvices(view: View?) {
        val intent = Intent(this, RegisterInfected::class.java)
        startActivity(intent)
    }

    fun placesmap(view: View?) {
        val intent = Intent(this, PlacesMap::class.java)
        startActivity(intent)
    }

    fun infectedmap(view: View?) {
        val intent = Intent(this, InfectedMap::class.java)
        startActivity(intent)
    }

    fun statistics(view: View?) {
        val intent = Intent(this, StatisticsActivity::class.java)
        intent.putExtra("death",death)
        intent.putExtra("recoverd",recoverd)
        intent.putExtra("total",total)
        startActivity(intent)
    }
}