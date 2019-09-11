package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Recipient;
import com.example.iffath.style_omega.R;

public class Payment extends Fragment implements View.OnClickListener {
    EditText cardHolder;
    EditText cvn;
    EditText expiry;
    EditText num1;
    EditText num2;
    EditText num3;
    EditText num4;
    Button pay;
    Button visa;
    Button paypal;
    Button master;
    Button amex;
    long cartId = -1;
    Recipient recipient;

    public Payment() {
        // Required empty public constructor
    }
    public Payment(long cartId, Recipient recipient){
        this.cartId = cartId;
        this.recipient = recipient;
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
        paypal = view.findViewById(R.id.paypal);
        visa = view.findViewById(R.id.visa);
        amex = view.findViewById(R.id.amex);
        master = view.findViewById(R.id.master);

        pay.setOnClickListener(this);
        paypal.setOnClickListener(this);
        visa.setOnClickListener(this);
        master.setOnClickListener(this);
        amex.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.payment_pay:
                makePayment();
                break;

            case R.id.amex:
                Toast.makeText(getContext(),"American Express",Toast.LENGTH_SHORT).show();
               // recipient.setPaymentType("AmEx");
                break;

            case R.id.visa:
                Toast.makeText(getContext(),"Visa",Toast.LENGTH_SHORT).show();
               // recipient.setPaymentType("Visa");
                break;

            case R.id.master:
                Toast.makeText(getContext(),"Master Card",Toast.LENGTH_SHORT).show();
               // recipient.setPaymentType("MasterCard");
                break;

            case R.id.paypal:
                Toast.makeText(getContext(), "Payment Successful", Toast.LENGTH_SHORT).show();
              //  recipient.setPaymentType("Paypal");
                onSuccess();
                break;
        }

    }

    private void makePayment(){
        String card1 = num1.getText().toString();
        String card2 = num2.getText().toString();
        String card3 = num3.getText().toString();
        String card4 = num4.getText().toString();
        String verify = cvn.getText().toString();
        String dateOfExpiry = expiry.getText().toString();
        String cardOwner = cardHolder.getText().toString();

        if(card1.length() !=4   || card2.length() !=4 || card3.length() !=4 || card4.length() !=4 || verify.length() !=3 ||
                dateOfExpiry.length() != 5 || dateOfExpiry.charAt(2) != '/' || cardOwner.length() == 0 || !TextUtils.isDigitsOnly(verify)
                || !TextUtils.isDigitsOnly(card1) || !TextUtils.isDigitsOnly(card2) ||!TextUtils.isDigitsOnly(card3)
                || !TextUtils.isDigitsOnly(card4)){
            num1.setText("0000");
            num2.setText("0000");
            num3.setText("0000");
            num4.setText("0000");
            cvn.setText("CVN");
            expiry.setText("MM/YY");
            Toast.makeText(getContext(),"Payment Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Payment Successful", Toast.LENGTH_SHORT).show();
            onSuccess();
        }
    }

    private void onSuccess(){
        if(cartId != -1) {
            Cart cart = Cart.findById(Cart.class, cartId);
            //recipient.save();
            //cart.setRecipient(recipient);
            //cart.setStatus(true);
            //cart.update();
        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.display_screen, new home_page())
                .commit();
    }
}
