package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iffath.style_omega.R;


public class home_page extends Fragment {
    public home_page() {
        // Required empty public constructor
    }
    public static home_page getInstance(){
        return new home_page();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_page, container, false);

        return view;
    }

}
