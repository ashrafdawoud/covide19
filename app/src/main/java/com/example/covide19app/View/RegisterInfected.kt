package com.example.covide19app.View

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.covide19app.R
import kotlin.math.log

class RegisterInfected : AppCompatActivity() {
    lateinit var male:TextView
    lateinit var female:TextView
    lateinit var morethan:TextView
    lateinit var lessthan:TextView
    lateinit var sore:LinearLayout
    lateinit var breath:LinearLayout
    lateinit var coach:LinearLayout
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
        morethan=findViewById(R.id.more_than_weak)
        lessthan=findViewById(R.id.less_than_weak)
        sore=findViewById(R.id.sore)
        coach=findViewById(R.id.coach)
        breath=findViewById(R.id.breath)
        male.setOnClickListener {
            male.background=resources.getDrawable(R.drawable.unselected_tap)
            female.background=null
        }
        female.setOnClickListener {
            female.background=resources.getDrawable(R.drawable.unselected_tap)
            male.background=null
        }
        morethan.setOnClickListener {
            morethan.background=resources.getDrawable(R.drawable.unselected_tap)
            lessthan.background=null
        }
        lessthan.setOnClickListener {
            lessthan.background=resources.getDrawable(R.drawable.unselected_tap)
            morethan.background=null
        }
        sore.setOnClickListener {
            if (sore.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                sore.background = resources.getDrawable(R.drawable.selected_tap)
            }
            else
            {
                Log.e("equal","false")
                sore.background=resources.getDrawable(R.drawable.unselected_tap)
            }

        }
        coach.setOnClickListener {
            if (coach.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                coach.background = resources.getDrawable(R.drawable.selected_tap)
            }
            else
            {
                Log.e("equal","false")
                coach.background=resources.getDrawable(R.drawable.unselected_tap)
            }

        }
        breath.setOnClickListener {
            if (breath.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                breath.background = resources.getDrawable(R.drawable.selected_tap)
            }
            else
            {
                Log.e("equal","false")
                breath.background=resources.getDrawable(R.drawable.unselected_tap)
            }

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