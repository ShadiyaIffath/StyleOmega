package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

public class Register extends AppCompatActivity {
    EditText uname,passTxt,emailTxt,nameTxt, numberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.HiddenTitleTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void login(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void onRegister(View view){
        uname=  findViewById(R.id.regUsername);
        String username = uname.getText().toString().toLowerCase();
        String name, email, password, number;     //declare string variables to hold registered attributes

        if (!username.isEmpty() && !User.find(User.class,"username=?",username).isEmpty()) {
            uname.setError("Username is already in use");
        }

        nameTxt = findViewById(R.id.regName);
        name = nameTxt.getText().toString();

        numberTxt = findViewById(R.id.regNumber);
        number = numberTxt.getText().toString();

        emailTxt = findViewById(R.id.regEmail);
        email = emailTxt.getText().toString();

        passTxt = findViewById(R.id.regPw);
        password = passTxt.getText().toString();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty()|| number.isEmpty() ||username.isEmpty()) {
            if (name.isEmpty()) {
                nameTxt.setError("Name cannot be blank");
            }
            if (email.isEmpty()) {
                emailTxt.setError("E-mail cannot be blank");
            }
            if (password.isEmpty()) {
                passTxt.setError("Password cannot be blank");
            }
            if (username.isEmpty()) {
                uname.setError("Username cannot be blank");
            }
            if(number.isEmpty()){
                numberTxt.setError("Contact number cannot be blank");
            }
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();
            User user = new User(name,username,email,password,number);
            user.save();

            //once successfully registered the username is stored to prevent the user having to login again unless they logout
            SharedPreferences sharedPreferences  = getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",username);
            editor.apply();

            Intent intent = new Intent(this,home.class);
            startActivity(intent);

        }

    }
}
