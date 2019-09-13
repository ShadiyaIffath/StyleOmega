package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iffath.style_omega.Activity.Launcher;
import com.example.iffath.style_omega.Activity.home;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import java.util.List;


public class Profile extends Fragment {
    EditText name;
    TextView uname;
    EditText contactNumber;
    EditText email;
    EditText password;
    List<User> logged;
    SharedPreferences sharedPreferences;
    String username;
    Button button;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);

        //retrieve logged user's username
        uname = view.findViewById(R.id.profileUsername);
        username = home.loggedUser;
        uname.setText(username);

        logged =User.find(User.class,"username=?",username);

        name = view.findViewById(R.id.profileName);
        name.setText(logged.get(0).getName());

        contactNumber = view.findViewById(R.id.contactProfile);
        contactNumber.setText(logged.get(0).getContactNumber());

        email = view.findViewById(R.id.emailProfile);
        email.setText(logged.get(0).getEmail());

        password = view.findViewById(R.id.passwordProfile);
        password.setText(logged.get(0).getPassword());

        button = view.findViewById(R.id.profileButton);
        button.setText("Edit");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = button.getText().toString();
                if(title.equals("Edit")){
                    editClick();
                    button.setText("Confirm");
                }
                else{
                    button.setText("Edit");
                    confirmEdit();
                }
            }
        });
        return view;
    }
    public void editClick(){ //enable textfield editability
        Toast.makeText(getActivity(),"You can make changes to your profile",Toast.LENGTH_SHORT).show();
        name.setFocusableInTouchMode(true);
        contactNumber.setFocusableInTouchMode(true);
        email.setFocusableInTouchMode(true);
        password.setFocusableInTouchMode(true);
    }

    public void confirmEdit(){  //disable textfield editability
        Toast.makeText(getActivity(),"Profile updated",Toast.LENGTH_SHORT).show();
        email.setFocusable(false);
        password.setFocusable(false);
        contactNumber.setFocusable(false);
        name.setFocusable(false);
        //update database
        User user = User.findById(User.class,logged.get(0).getId());
        user.setName(name.getText().toString());
        user.setContactNumber(contactNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.save();
    }

}
