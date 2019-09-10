package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.iffath.style_omega.R;

public class Payment extends Fragment {
    EditText cardHolder;
    EditText cvn;
    EditText expiry;
    EditText num1;
    EditText num2;
    EditText num3;
    EditText num4;
    Button pay;
    ImageButton payType;

    public Payment() {
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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        cardHolder = view.findViewById(R.id.payment_holder);
        cvn = view.findViewById(R.id.payment_cvn);
        expiry = view.findViewById(R.id.payment_month);
        num1 = view.findViewById(R.id.card_1);
        num2 = view.findViewById(R.id.card_2);
        num3 = view.findViewById(R.id.card_3);
        num4 = view.findViewById(R.id.card_4);
        pay = view.findViewById(R.id.payment_pay);
        return view;
    }

}
