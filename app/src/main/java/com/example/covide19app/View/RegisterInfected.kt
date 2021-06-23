package com.example.covide19app.View

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.covide19app.R
import com.example.covide19app.Retrofit.Entities.SucssesEntity
import com.example.covide19app.Utils.DataState
import com.example.covide19app.ViewModel.InfectedPoapleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.HashMap

@AndroidEntryPoint
class RegisterInfected : AppCompatActivity() {
    lateinit var male:TextView
    lateinit var done:Button
    lateinit var female:TextView
    lateinit var morethan:TextView
    lateinit var lessthan:TextView
    lateinit var sore:LinearLayout
    lateinit var breath:LinearLayout
    lateinit var coach:LinearLayout
    lateinit var ageedittext:EditText
    lateinit var phone:EditText
    lateinit var sharedPrefrence:SharedPreferences
    var age=""
    var gander="male"
    var content_number=""
    var symptoms=""
    var lat="30.3333324"
    var long="31.554447777"
    var moreless=""
    val viewmodel:InfectedPoapleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        activityDesign()
        contentview()

    }
    fun contentview(){
        sharedPrefrence = this.getSharedPreferences("user", MODE_PRIVATE)
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
        done=findViewById(R.id.done)
        phone=findViewById(R.id.phone)
        ageedittext=findViewById(R.id.ageText)
        male.setOnClickListener {
            male.background=resources.getDrawable(R.drawable.unselected_tap)
            female.background=null
            gander="male"
        }
        female.setOnClickListener {
            female.background=resources.getDrawable(R.drawable.unselected_tap)
            male.background=null
            gander="female"
        }
        morethan.setOnClickListener {
            morethan.background=resources.getDrawable(R.drawable.unselected_tap)
            lessthan.background=null
            moreless="more than weak"
        }
        lessthan.setOnClickListener {
            lessthan.background=resources.getDrawable(R.drawable.unselected_tap)
            morethan.background=null
            moreless="less than weak"
        }
        sore.setOnClickListener {
            if (sore.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                sore.background = resources.getDrawable(R.drawable.selected_tap)
                symptoms=""

            }
            else
            {
                Log.e("equal", "false")
                sore.background=resources.getDrawable(R.drawable.unselected_tap)
                symptoms="sore throat"
            }

        }
        coach.setOnClickListener {
            if (coach.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                coach.background = resources.getDrawable(R.drawable.selected_tap)
                symptoms=""
            }
            else
            {
                Log.e("equal", "false")
                coach.background=resources.getDrawable(R.drawable.unselected_tap)
                symptoms="coagh"
            }

        }
        breath.setOnClickListener {
            if (breath.background.getConstantState()==resources.getDrawable(R.drawable.unselected_tap).getConstantState()) {
                Log.e("equal", "true")
                breath.background = resources.getDrawable(R.drawable.selected_tap)
                symptoms=""
            }
            else
            {
                Log.e("equal", "false")
                breath.background=resources.getDrawable(R.drawable.unselected_tap)
                symptoms="short breath"
            }
        }
        done.setOnClickListener{
            content_number=phone.text.toString()
            age=ageedittext.text.toString()
            val hashMap: HashMap<String, String> = HashMap<String, String>()
            hashMap.put("userid", sharedPrefrence.getString("userId","").toString());
            hashMap.put("age", age);
            hashMap.put("contant_number", content_number);
            hashMap.put("gander", gander);
            hashMap.put("symptoms", symptoms);
            hashMap.put("lat", lat);
            hashMap.put("long", long);
            viewmodel.postInfected(hashMap)
            observalsviewmodel()
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
    fun observalsviewmodel(){
        viewmodel.datasate.observe(this, Observer {
            when(it){
                is DataState.Success<SucssesEntity> -> {
                    Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading ->{
                }
                is DataState.Error ->{
                    Toast.makeText(this,"${it.exception}",Toast.LENGTH_SHORT).show()

                }
            }
        })

    }
}