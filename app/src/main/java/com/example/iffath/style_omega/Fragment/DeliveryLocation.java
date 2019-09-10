package com.example.iffath.style_omega.Fragment;

import android.content.Context;
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

import com.example.iffath.style_omega.R;


public class DeliveryLocation extends Fragment implements View.OnClickListener {
    Button payment;
    EditText reciever;
    EditText deliver;
    EditText contact;
    EditText email;
    TextView title;
    FrameLayout frame;
    ProgressBar progressBar;


    public DeliveryLocation() {
        // Required empty public constructor
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
        frame = view.findViewById(R.id.checkout_progress);

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
            progressBar.incrementProgressBy(50);
            title.setText("Payment");
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.display_screen, new Payment())
                .addToBackStack(null)
                .commit();
//      LayoutInflater.from(getContext()).inflate(R.id.payment_layout,frame,true);
        }

    }
}
