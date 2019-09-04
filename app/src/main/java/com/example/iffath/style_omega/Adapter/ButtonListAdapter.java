package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.iffath.style_omega.Fragment.TypeHome;
import com.example.iffath.style_omega.Model.BtnClickListener;
import com.example.iffath.style_omega.R;

public class ButtonListAdapter extends ArrayAdapter<String> {
    private String[] buttonTypes;
    Context context;
    public ButtonListAdapter(String[] types, Context context){
            super(context, R.layout.cloth_type_buttons,types);
            buttonTypes = types;
            this.context = context;
    }


    public static class ViewHolder{
        Button type;

    }

    public View getView(final int position, View convertView, ViewGroup parent){

        String clothType = getItem(position);

        final View result;

        final ViewHolder holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cloth_type_buttons,parent,false);
            holder.type = convertView.findViewById(R.id.type_button);
            result = convertView;
            convertView.setTag(holder);
        }
        else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
        }
        holder.type.setText(clothType);
//
        holder.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) v.getParent()).performItemClick(v, position, 0);
            }
        } );

        return result;
    }







}
