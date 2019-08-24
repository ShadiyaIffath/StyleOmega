package com.example.iffath.style_omega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void getRegistered(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
