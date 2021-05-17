package com.example.covide19app.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.covide19app.R;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    EditText emailedit,passwordedit;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
         sharedPreferences=LoginActivity.this.getSharedPreferences("user",MODE_PRIVATE);
        activityDesign();
       /*  if (!sharedPreferences.getString("useremail","").equals("")){
             Intent intent=new Intent(LoginActivity.this,MainActivity.class);
             startActivity(intent);
             finish();
         }*/
        //////////////////////////connect to back4app////////////////////////
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //////////////////////////////////////////////////
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("UserTable");
                final String emailtext=email.getText().toString();
                final String passwordtext=password.getText().toString();
                query.whereEqualTo("email", emailtext);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject player, ParseException e) {
                        if (e == null) {
                            Log.e("click", "true");
                            if (player.getString("password").equals(passwordtext)){
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("useremail",emailtext);
                                editor.putString("userpassword",passwordtext);
                                editor.putString("username",player.get("name").toString());
                                editor.putString("userId", player.getString("objectId"));
                                editor.commit();
                                Toast.makeText(LoginActivity.this,player.getString("objectId"),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"Worng password",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // Something is wrong
                            Log.e("return2", "true");
                            Toast.makeText(LoginActivity.this,"we didn't have this account",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


    public void signup(View view) {
        Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    void getid(){

    }
    void activityDesign() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources().getColor(R.color.major_back));
            window.setNavigationBarColor(getResources().getColor(R.color.major_back));
        }
    }
}

