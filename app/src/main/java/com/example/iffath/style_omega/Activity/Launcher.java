package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import java.util.List;

public class Launcher extends AppCompatActivity {
    String username;
    public static String keyPreference = "preferences";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //if user has logged in then the username will be retrieved
        sharedPreferences = getSharedPreferences(keyPreference, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username",null);

        if(username!=null){//the user will be directed to the home page directly
            Toast.makeText(this, "Welcome Back " + username, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }
        else {
            setTheme(R.style.HiddenTitleTheme);
            super.onCreate(savedInstanceState); //the user has not logged in hence will be directed to the login page
            setContentView(R.layout.activity_launcher);
        }
    }

    public void redirectLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void redirectRegister(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
