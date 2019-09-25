package com.example.iffath.style_omega.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iffath.style_omega.Activity.Launcher;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import java.util.List;


public class Profile extends Fragment implements View.OnClickListener {
    private String pw="";
    private String newPw ="";
    EditText name;
    TextView uname;
    EditText contactNumber;
    EditText email;
    SharedPreferences sharedPreferences;
    Button button;
    Button remove;
    Button password;
    User user;
    Context context;
    public Profile() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);
        context = getContext();
        sharedPreferences = getActivity().getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("user",0);
        user = User.findById(User.class,id);
        //retrieve logged user's username
        uname = view.findViewById(R.id.profileUsername);
        uname.setText(user.getUsername());

        name = view.findViewById(R.id.profileName);
        name.setText(user.getName());

        contactNumber = view.findViewById(R.id.contactProfile);
        contactNumber.setText(user.getContactNumber());

        email = view.findViewById(R.id.emailProfile);
        email.setText(user.getEmail());

        button = view.findViewById(R.id.profileButton);
        button.setText("Edit");
        button.setOnClickListener(this);

        remove = view.findViewById(R.id.profileRemove);
        remove.setOnClickListener(this);

        password = view.findViewById(R.id.profilePassword);
        password.setOnClickListener(this);
        return view;
    }
    public void editClick(){ //enable textfield editability
        Toast.makeText(getActivity(),"You can make changes to your profile",Toast.LENGTH_SHORT).show();
        name.setFocusableInTouchMode(true);
        contactNumber.setFocusableInTouchMode(true);
        email.setFocusableInTouchMode(true);
        button.setText("Confirm");
    }

    public void confirmEdit(){  //disable textfield editability
        Toast.makeText(getActivity(),"Profile updated",Toast.LENGTH_SHORT).show();
        email.setFocusable(false);
        contactNumber.setFocusable(false);
        name.setFocusable(false);
        button.setText("Edit");
        //update database
        user.setName(name.getText().toString());
        user.setContactNumber(contactNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.save();
    }

    public void removeProfile(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete Profile?");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setMessage("Enter password to confirm the deletion: ");
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pw = input.getText().toString();
                        confirmDeletion();
                    }
                });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void confirmDeletion(){
        if(pw.equals(user.password)) {

            List<Cart> pendingCart = Cart.listAll(Cart.class);
            Cart usercart = null;
            for (Cart x : pendingCart) {
                if (!x.isStatus() && x.getUserId() == user.getId()) {
                    usercart = x;
                    break;
                }
            }

            if (usercart != null) {
                List<Cart_Product> products = Cart_Product.listAll(Cart_Product.class);
                for (Cart_Product x : products) {
                    if (x.getCartId() == usercart.getId()) {
                        x.delete();
                    }
                }
            }
            usercart.delete();
            Log.i("cart", Integer.toString(pendingCart.size()));
            user.delete();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), Launcher.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getContext(),"Invalid password entered",Toast.LENGTH_SHORT).show();
        }
    }

    public void changePassword(){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Change Password");

        final EditText currentPassword = new EditText(context);
        currentPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        currentPassword.setHint("Current Password");
        layout.addView(currentPassword);

        final EditText newPassword = new EditText(context);
        newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newPassword.setHint("New Password");
        layout.addView(newPassword);

        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pw = currentPassword.getText().toString();
                newPw = newPassword.getText().toString();
                if(TextUtils.isEmpty(pw)|| TextUtils.isEmpty(newPw)){
                    Toast.makeText(getContext(),"Blank fields",Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                else{
                    if(pw.equals(user.password)){
                        user.setPassword(newPw);
                        user.save();
                        Toast.makeText(getActivity(),"Password updated",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity(),"Invalid Password Entered",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profileButton:
                if(button.getText().toString().equals("Edit")){
                    editClick();
                }
                else{
                    confirmEdit();
                }
                break;

            case R.id.profileRemove:
                removeProfile();
                break;

            case R.id.profilePassword:
                changePassword();
                break;

        }
    }
}
