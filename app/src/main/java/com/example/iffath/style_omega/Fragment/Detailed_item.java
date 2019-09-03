package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Detailed_item extends Fragment {
    @BindView(R.id.product_title) TextView title;
    TextView description;
    TextView category;
    Product product;
    ImageView thumbnail;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_item, container, false);
        product = null;
        title = view.findViewById(R.id.product_title);
 //       ButterKnife.bind(this,view);
//        unbinder = ButterKnife.bind(this, view);

        description = view.findViewById(R.id.product_desc);
        category = view.findViewById(R.id.product_category);
        thumbnail = view.findViewById(R.id.thumbnail_item);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            product = bundle.getParcelable("Item");
            if(product != null){
                title.setText(product.getTitle());
                description.setText(product.getDescription());
                category.setText(product.getType());
                thumbnail.setImageResource(product.getThumbnail());
            }
        }


        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
