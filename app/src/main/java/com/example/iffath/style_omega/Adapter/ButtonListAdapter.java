package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.iffath.style_omega.R;

public class ButtonListAdapter extends ArrayAdapter<String> {
    private String[] buttonTypes;
    Context context;
    public ButtonListAdapter(String[] types, Context context){
            super(context, R.layout.cloth_type_buttons,types);
            buttonTypes = types;
            this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        String clothType = getItem(position);
        Button type;
        final View result;

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cloth_type_buttons,parent,false);
        }
        result = convertView;
        type = convertView.findViewById(R.id.type_button);
        type.setText(clothType);

        return result;
    }







}
