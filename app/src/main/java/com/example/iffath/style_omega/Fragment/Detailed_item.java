package com.example.iffath.style_omega.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.iffath.style_omega.Activity.Launcher;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    TextView shareButton;
    Button add;
    Button sub;
    Button addToCart;
    int orderedVal =0;

    private Unbinder unbinder;

    public Detailed_item(){

    }

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
        shareButton = view.findViewById(R.id.share_button);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        addToCart.setOnClickListener(this);
        shareButton.setOnClickListener(this);

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

                case R.id.share_button:
                    shareItem();
                    break;
            }
    }

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
            showPopupDialog();
        }
    }

    public void showPopupDialog(){    //method which shows a dialog for confirmation when customer clicks add to cart
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm add to cart");
        builder.setMessage("\tYou are about to place an order for \n\t"+
                product.getTitle()+".\n\tThe total price is Rs."+ priceTag.getText().toString()+"\n\t Press 'Yes' to confirm");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                placeOrder();
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

    public void placeOrder(){   //method which creates/ updates an existing cart
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Long userid = sharedPreferences.getLong("user",0);

        //            String [] values = {"0",Long.toString(userid)};
//            List<Cart> pendingCart = Cart.find(Cart.class,"status=? and userid=?", values);

//        List<Cart> pendingCart = Cart.findWithQuery(Cart.class,"Select * from Cart where userId = ? ", userid.toString());

//        List<Cart> pendingCart = Select.from(Cart.class)
//                        .where(Condition.prop("status").eq(0),
//                            Condition.prop("userId").eq(userid)).list();


        if(userid!= null) {  //retrieve the carts that belong to the user that hasn't been checked out yet

       List<Cart> pendingCart = Cart.find(Cart.class,"status=?","0"); //this works

            for(Cart x: pendingCart){
                if (x.getUserId()!= userid){
                    pendingCart.remove(x);
                }
            }

            Cart userCart = null;
            if (pendingCart != null) {  //use existing cart
                Toast.makeText(getActivity(), "Item added to cart", Toast.LENGTH_SHORT).show();
                userCart = pendingCart.get(0);
            }
            else{   //create new cart for the user if they don't have one
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                userCart = new Cart(userid,product.getPrice(),false,date);
                userCart.save();
                Toast.makeText(getActivity(),"New Cart Created",Toast.LENGTH_SHORT).show();
            }


            Cart_Product addedItem =
                    new Cart_Product(userCart.getId(), product.getId(), product.getPrice(), Integer.parseInt(quantity.getText().toString()));
            //addedItem.save();
        }
        else{
            Toast.makeText(getActivity(), "Order failed", Toast.LENGTH_SHORT).show();
        }

    }


    public void shareItem(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "Item "+product.getTitle()+" Price: Rs."+product.getPrice()+" Type: "+product.getType();
        String subject = "Style Omega Clothing Shop";
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(shareIntent,"Share Using..."));
    }


}
