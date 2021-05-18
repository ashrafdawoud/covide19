package com.example.covide19app.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.covide19app.R;
import com.example.covide19app.ViewMaps.InfectedMap;
import com.example.covide19app.ViewMaps.PlacesMap;
import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityDesign();
        contentview();
    }

    void contentview() {

    }

    void activityDesign() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources().getColor(R.color.major_back));
            window.setNavigationBarColor(getResources().getColor(R.color.major_back));
        }
    }

    public void logout(View view) {
        finishAffinity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void advises(View view) {
        Intent intent = new Intent(this, AdviceActivity.class);
        startActivity(intent);
    }


    public void registerAdvices(View view) {
        Intent intent = new Intent(this, RegisterInfected.class);
        startActivity(intent);
    }

    public void placesmap(View view) {
        Intent intent = new Intent(this, PlacesMap.class);
        startActivity(intent);
    }

    public void infectedmap(View view) {
        Intent intent = new Intent(this, InfectedMap.class);
        startActivity(intent);
    }

    public void statistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}