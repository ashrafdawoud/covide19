package com.example.lawerapp.Utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import com.example.covide19app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityDesign @Inject constructor() {

    fun excuteDesign(activity:Activity){
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = activity.resources.getColor(R.color.white)
            window.navigationBarColor = activity.resources.getColor(R.color.white)
        }
    }
}