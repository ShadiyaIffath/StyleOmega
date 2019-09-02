package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iffath.style_omega.R;


public class home_page extends Fragment implements View.OnClickListener{
    Button men;
    Button women;
    Button kids;


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
        women = view.findViewById(R.id.womensImg);
        men = view.findViewById(R.id.mensImage);
        kids = view.findViewById(R.id.kidsImage);
        women.setOnClickListener(this);
        men.setOnClickListener(this);
        kids.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        String type = null;
        switch(view.getId()){
            case R.id.womensImg:
                type="Women";
                break;
            case R.id.mensImage:
                type="Men";
                break;
            case R.id.kidsImage:
                type="Kids";
                break;
        }
        fragment = new TypeHome();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.display_screen, fragment).addToBackStack(null)
                .commit();
        getActivity().setTitle(type);
    }
}
