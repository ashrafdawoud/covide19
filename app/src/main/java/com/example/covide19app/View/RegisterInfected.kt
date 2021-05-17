package com.example.covide19app.View

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.covide19app.R

class RegisterInfected : AppCompatActivity() {
    lateinit var male:TextView
    lateinit var female:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
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
        male=findViewById(R.id.male)
        female=findViewById(R.id.female)
        male.setOnClickListener {
            male.background=resources.getDrawable(R.drawable.unselected_tap)
            female.background=null
        }
        female.setOnClickListener {
            female.background=resources.getDrawable(R.drawable.unselected_tap)
            male.background=null
        }
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