package com.example.iffath.style_omega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.User;

import java.util.List;

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

            if (User.find(User.class, "username=? and password=?", login).isEmpty()) {

                pw.setError("Invalid Password");
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Welcome Back " + username, Toast.LENGTH_SHORT).show();

                //on successful login we store the logged in user's username so that the user doesn't have to login everytime
                    //the app launches
                sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.apply();

                Intent intent = new Intent(this, home.class);
                startActivity(intent);
            }
        }
        else{
            if (username.isEmpty()) {
                uname.setError("Username cannot be blank");
            }
            if (password.isEmpty()
            ) {
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
