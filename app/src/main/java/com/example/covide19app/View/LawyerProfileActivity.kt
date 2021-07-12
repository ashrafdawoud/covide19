package com.example.covide19app.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.R
import com.example.covide19app.databinding.ActivityLawyerProfileBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LawyerProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityLawyerProfileBinding
    lateinit var user: DoctorsModel
    var isfav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_profile)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val i = intent
        val bundle = i.extras
        user = (bundle!!.getSerializable("user") as DoctorsModel?)!!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lawyer_profile)
        contentview()
        activityDesign()
    }
    fun contentview() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.map.setOnClickListener {
            val intent=Intent(this, MapsActivity::class.java)
            intent.putExtra("long", user.long)
            intent.putExtra("lat", user.lat)
            startActivity(intent)
        }
        binding.name.setText(user.name)
        binding.address.setText(user.address)
        binding.exp.setText(user.exp + " سنوات خبره ")
        binding.description.setText(user.discreiption)
        Picasso.get().load(user.image).into(binding.profile)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
    fun activityDesign() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.statusBarColor = resources.getColor(R.color.major_back)
            window.navigationBarColor = resources.getColor(R.color.major_back)
        }
    }

}