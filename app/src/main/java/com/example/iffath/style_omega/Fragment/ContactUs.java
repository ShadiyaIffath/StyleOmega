package com.example.iffath.style_omega.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iffath.style_omega.Activity.Launcher;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import org.w3c.dom.Text;

import java.io.IOException;


public class ContactUs extends Fragment implements View.OnClickListener {
    EditText name;
    EditText email;
    EditText inquiry;
    Button confirm;
    Button call;
    SharedPreferences sharedPreferences;
    private SingletonProduct singletonProduct = SingletonProduct.getInstance();
    public ContactUs() {
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
       View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
       getActivity().setTitle("Contact Us");
       sharedPreferences = getActivity().getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
       long id = sharedPreferences.getLong("user",0);
       User user = User.findById(User.class,id);

       name = view.findViewById(R.id.contact_name);
       email = view.findViewById(R.id.contact_email);
       inquiry = view.findViewById(R.id.contact_inquiry);
       confirm = view.findViewById(R.id.contact_confirm);
       call = view.findViewById(R.id.contact_call);

       name.setText(user.getName());
       email.setText((user.getEmail()));

       confirm.setOnClickListener(this);
       call.setOnClickListener(this);

       return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.contact_confirm:
                sendInquiry();
                break;

            case R.id.contact_call:
                dialNumber("+94112732187");
                break;
        }
    }


    private void sendInquiry(){ // send inquiry to admin using the server
        String inquirerName, inquirerEmail,inquiryAbout;
        inquirerName = name.getText().toString();
        inquirerEmail = email.getText().toString();
        inquiryAbout = inquiry.getText().toString();

        if(inquirerEmail.isEmpty() || inquirerName.isEmpty() || inquiryAbout.isEmpty()){
            if(TextUtils.isEmpty(inquirerEmail)){
                email.setError("Enter valid email address");
            }
            if(TextUtils.isEmpty(inquirerName)){
                name.setError("Enter name");
            }
            if(TextUtils.isEmpty(inquiryAbout)){
                inquiry.setError("Enter inquiry information");
            }
            Toast.makeText(getContext(),"Please make sure to fill all fields",Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                singletonProduct.makeInquiry(inquirerName,inquirerEmail,inquiryAbout);
                Toast.makeText(getContext(),"Inquiry has been sent",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e("Post","Failed");
            }

            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.display_screen, new home_page())
                    .commit();
        }
    }

    private void dialNumber(final String contactNumber) { //method which is used to create an intent service to make the call
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactNumber, null)));
    }
}
