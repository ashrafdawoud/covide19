package com.example.covide19app.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.R
import com.example.covide19app.Utils.ActivityDesign
import com.example.covide19app.databinding.ActivityAdviceDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdviceDetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var activityDesign:ActivityDesign
    lateinit var binding:ActivityAdviceDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advice_details)
        contentview()
    }
    fun contentview(){
        activityDesign.excuteDesign(this)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_advice_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val i = intent
        val bundle = i.extras
        val user = (bundle!!.getSerializable("user") as AdvicesModel?)!!
        binding.discription.setText(user.topic)
        binding.title.setText(user.title)
        Picasso.get().load(user.image).into(binding.image)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}