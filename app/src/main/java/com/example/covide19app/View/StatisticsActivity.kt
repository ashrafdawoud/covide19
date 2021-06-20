package com.example.covide19app.View

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.covide19app.R
import com.example.covide19app.databinding.ActivityStatisticsBinding
import org.eazegraph.lib.charts.BarChart
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel

class StatisticsActivity : AppCompatActivity() {
    var death:Int=0
    var recoverd:Int=0
    var total:Int=0
    lateinit var binding:ActivityStatisticsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_statistics)
        activityDesign()
        contentview()
    }

    fun contentview() {
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