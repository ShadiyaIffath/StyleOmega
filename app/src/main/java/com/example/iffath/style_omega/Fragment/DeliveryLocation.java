package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Recipient;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;


public class DeliveryLocation extends Fragment implements View.OnClickListener {
    Button payment;
    EditText reciever;
    EditText deliver;
    EditText contact;
    EditText email;
    TextView title;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    long cartId =-1;

    public DeliveryLocation() {
        // Required empty public constructor
    }

    public DeliveryLocation(long cartId){
        this.cartId = cartId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_location, container, false);
        reciever = view.findViewById(R.id.checkout_receiver);
        deliver = view.findViewById(R.id.checkout_deliver_to);
        contact = view.findViewById(R.id.checkout_cnumber);
        email = view.findViewById(R.id.checkout_email);
        payment = view.findViewById(R.id.checkout_proceed);
        title = view.findViewById(R.id.checkout_title);
        progressBar = view.findViewById(R.id.checkout_progress);

        User user = getUser();

        if(user != null){
            reciever.setText(user.getName());
            contact.setText(user.getContactNumber());
            email.setText(user.getEmail());
        }
        payment.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        String recipient = reciever.getText().toString();
        String recEmail = email.getText().toString();
        String number = contact.getText().toString();
        String address = deliver.getText().toString();

        if(recipient.isEmpty() || recEmail.isEmpty() ||number.isEmpty()|| address.isEmpty()){
            if(recipient.isEmpty()){
                reciever.setError("Recipient name cannot be blank");
            }
            if(recEmail.isEmpty()){
                email.setError("E-mail cannot be blank");
            }
            if(number.isEmpty()){
                contact.setError("Contact Number cannot be blank");
            }
            if(address.isEmpty()){
                deliver.setError("The delivery address cannot be blank");
            }
        }else{
            Recipient recipient1 = new Recipient(recipient,address,number,recEmail);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.display_screen, new Payment(recipient1)) //new Payment(cartId, recipient1)
                .commit();
        }
    }

    private User getUser(){
        User user = null;
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("user",0);
        user = User.findById(User.class,id);
        return user;
    }
}
