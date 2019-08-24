package com.example.iffath.style_omega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.User;

public class Register extends AppCompatActivity {
    EditText uname,passTxt,emailTxt,nameTxt, numberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        boolean validator = false;

        nameTxt = findViewById(R.id.regName);
        name = nameTxt.getText().toString();

        numberTxt = findViewById(R.id.regNumber);
        number = numberTxt.getText().toString();

        emailTxt = findViewById(R.id.regEmail);
        email = emailTxt.getText().toString();

        passTxt = findViewById(R.id.regPw);
        password = passTxt.getText().toString();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty()|| number.isEmpty() ||validator == true) {
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
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            User user = new User(name,username,email,password,number);
            user.save();
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
