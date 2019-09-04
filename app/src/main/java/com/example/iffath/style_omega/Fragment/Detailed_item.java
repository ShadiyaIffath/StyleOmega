package com.example.iffath.style_omega.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Detailed_item extends Fragment implements View.OnClickListener{
    TextView title;
    TextView price;
    TextView description;
    TextView category;
    Product product;
    TextView size;
    TextView color;
    ImageView thumbnail;
    TextView quantity;
    TextView priceTag;
    Button add;
    Button sub;
    Button addToCart;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_item, container, false);
        product = null;
        title = view.findViewById(R.id.product_title);

        description = view.findViewById(R.id.product_desc);
        category = view.findViewById(R.id.product_category);
        thumbnail = view.findViewById(R.id.thumbnail_item);
        price = view.findViewById(R.id.product_price);
        color = view.findViewById(R.id.product_color);
        size = view.findViewById(R.id.product_size);
        add = view.findViewById(R.id.btnAdd);
        sub = view.findViewById(R.id.btnSub);
        addToCart = view.findViewById(R.id.add_cart_btn);
        quantity = view.findViewById(R.id.txtQuantity);
        priceTag = view.findViewById(R.id.priceTag);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        addToCart.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            product = bundle.getParcelable("Item");

            if(product != null){
                title.setText(product.getTitle());
                description.setText(product.getDescription());
                category.setText(product.getType());
                thumbnail.setImageResource(product.getThumbnail());
                price.append(Double.toString(product.getPrice()));
                color.append(product.allColors());
                size.append(product.allSizes());
            }
        }


        return view;
    }

    @Override
    public void onClick(View view) {
            int id = view.getId();
            switch(id){
                case R.id.btnAdd:
                    increaseQuantity();
                    break;

                case R.id.btnSub:
                    decreaseQuantity();
                    break;

                case R.id.add_cart_btn:
                    addToCart();
                    break;
            }
    }

//    @Override public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
    public void increaseQuantity(){     //increase button
            int sum = Integer.parseInt(quantity.getText().toString());
            sum++;
            if(product.getQuantity() < sum){
                Toast.makeText(getActivity(), "You cannot increase anymore", Toast.LENGTH_SHORT).show();
            }else{
                quantity.setText(String.valueOf(sum));
                priceTag.setText(String.valueOf(sum*product.getPrice()));
            }
    }

    public void decreaseQuantity(){     //decrease button
        int sum = Integer.parseInt(quantity.getText().toString());
        sum--;
        if(sum < 0){
            Toast.makeText(getActivity(), "You cannot decrease anymore", Toast.LENGTH_SHORT).show();
        }else{
            quantity.setText(String.valueOf(sum));
            priceTag.setText(String.valueOf(sum*product.getPrice()));
        }
    }

    public void addToCart(){        //Create cart object on confirmation
        int sum = Integer.parseInt(quantity.getText().toString());
        if(sum <= 0){
            Toast.makeText(getActivity(), "You cannot place the order", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Confirm add to cart");
            builder.setMessage("You are about to place an order for "+
                    product.getTitle()+". The total price is Rs."+ priceTag.getText().toString()+"\n Press 'Yes' to confirm");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(),"Order successfully placed",Toast.LENGTH_SHORT).show();
                    quantity.setText("0");
                    priceTag.setText("");
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(), "Order wasn't placed", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
    }


}
