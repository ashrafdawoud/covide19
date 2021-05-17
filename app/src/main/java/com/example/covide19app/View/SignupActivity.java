package com.example.covide19app.View;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.parse.SaveCallback;

public class SignupActivity extends AppCompatActivity {
    EditText name, email, password, repassword,phone,address;
    String nameText,emailtext,passwordtext,repasswordtext,phonetext,addresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        name=findViewById(R.id.name1);
        name.setTextColor(getResources().getColor(R.color.white));
        name.setHintTextColor(getResources().getColor(R.color.white));
        email=findViewById(R.id.email1);
        email.setTextColor(getResources().getColor(R.color.white));
        email.setHintTextColor(getResources().getColor(R.color.white));
        password=findViewById(R.id.password1);
        password.setTextColor(getResources().getColor(R.color.white));
        password.setHintTextColor(getResources().getColor(R.color.white));
        repassword=findViewById(R.id.Rpassword1);
        repassword.setTextColor(getResources().getColor(R.color.white));
        repassword.setHintTextColor(getResources().getColor(R.color.white));
        phone=findViewById(R.id.phone1);
        phone.setTextColor(getResources().getColor(R.color.white));
        phone.setHintTextColor(getResources().getColor(R.color.white));
        address=findViewById(R.id.address1);
        address.setTextColor(getResources().getColor(R.color.white));
        address.setHintTextColor(getResources().getColor(R.color.white));
        Log.e("emailtext",emailtext+"s");
        activityDesign();
        //////////////////////////connect to back4app////////////////////////
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }
    public void signup(View view) {
        nameText=name.getText().toString();
        emailtext=email.getText().toString();
        passwordtext=password.getText().toString();
        repasswordtext=repassword.getText().toString();
        phonetext=phone.getText().toString();
        addresstext=address.getText().toString();
        Log.e("emailtext",emailtext);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserTable");
        String emailtext=email.getText().toString();
        final String passwordtext=password.getText().toString();
        query.whereEqualTo("email", emailtext);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject player, ParseException e) {
                if (e == null) {
                    Toast.makeText(SignupActivity.this, "you have signed in before ", Toast.LENGTH_SHORT).show();
                } else {
                   signup(); } }});
    }
    void signup(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //////////////////////////////////////////////////
        final ParseObject soccerPlayers = new ParseObject("UserTable");
// Store an object
        if (!name.getText().toString().equals("")||!email.getText().toString().equals("")||!password.getText().toString().equals("")||!repassword.getText().toString().equals("")||!phone.getText().toString().equals("")||!address.getText().toString().equals("")) {
            if (passwordtext.equals(repasswordtext) && !passwordtext.equals("") && !repasswordtext.equals("")) {
                Log.e("emailtext", emailtext);
                if (emailtext.contains("@gmail.com") || emailtext.contains("@yahoo.com")) {
                    soccerPlayers.put("email", emailtext);
                    soccerPlayers.put("password", passwordtext);
                    soccerPlayers.put("name", nameText);
                    soccerPlayers.put("phone", phonetext);
                    soccerPlayers.put("address", addresstext);
                    soccerPlayers.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignupActivity.this, "sign up successfully ", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SignupActivity.this, "error try again ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "email is not correct ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "password not matched", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(SignupActivity.this, "All Filds Required", Toast.LENGTH_SHORT).show();
        }
// Saving object

    }
    public void back2(View view) {
        finish();
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
