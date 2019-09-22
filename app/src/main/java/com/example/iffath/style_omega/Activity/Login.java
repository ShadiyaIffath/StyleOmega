package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {
    EditText pw;
    EditText uname;
    String username;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.HiddenTitleTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view){
        uname = findViewById(R.id.usernametxt);
        pw = findViewById(R.id.pwtxt);
        username = uname.getText().toString().toLowerCase();
        String password = pw.getText().toString();

        if (!User.find(User.class,"username=?",username).isEmpty()) {

            String[] login = {username, password};
            List<User> users =User.find(User.class, "username=? and password=?", login);
            if (users.isEmpty()) {
                pw.setError("Invalid Password");
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Welcome Back " + username, Toast.LENGTH_SHORT).show();

//                on successful login we store the logged in user's username so that the user doesn't have to login everytime
//                    the app launches
                sharedPreferences = getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                long id = users.get(0).getId();
                editor.putLong("user",id);
                editor.apply();

                Intent intent = new Intent(this, home.class);
                startActivity(intent);
            }
        }
        else{
            if (TextUtils.isEmpty(username)) {
                uname.setError("Username cannot be blank");
            }
            if (TextUtils.isEmpty(password)) {
                pw.setError("Password cannot be blank");
            }
            Toast.makeText(this, "Login attempt failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void getRegistered(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
