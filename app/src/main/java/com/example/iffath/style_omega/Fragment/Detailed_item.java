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
import com.example.iffath.style_omega.BuildConfig;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.squareup.picasso.Picasso;

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
                Picasso.get()
                        .load(product.getThumb())
                        .error(R.drawable.dressfail1)
                        .placeholder(R.drawable.dressfail)
                        .into(thumbnail);
              //  thumbnail.setImageResource(product.getThumbnail());
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

    private void placeOrder(){   //method which creates/ updates an existing cart
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Long userid = sharedPreferences.getLong("user",-1);

        //            String [] values = {"0",Long.toString(userid)};
//            List<Cart> pendingCart = Cart.find(Cart.class,"status=? and userid=?", values);

//        List<Cart> pendingCart = Cart.findWithQuery(Cart.class,"Select * from Cart where userId = ? ", userid.toString());

//        List<Cart> pendingCart = Select.from(Cart.class)
//                        .where(Condition.prop("status").eq(0),
//                            Condition.prop("userId").eq(userid)).list();


        if(userid!= -1) {  //retrieve the carts that belong to the user that hasn't been checked out yet

       List<Cart> pendingCart = Cart.listAll(Cart.class); //this works
            Cart userCart = null;
            for(Cart x: pendingCart){
                if (x.getUserId()== userid && !x.isStatus()){
                    userCart = x;
                    break;
                }
            }


            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            if (userCart != null) {  //use existing cart
                userCart.setUpdated(date);
            }

            else{   //create new cart for the user if they don't have one
                userCart = new Cart(userid,0,false,date);
                userCart.save();
            }

            long existingOrderId = verifyItemExistsInCart(userCart.getId(),product.getId());
            Cart_Product addedItem = null;

            if(existingOrderId != -1){ // updates quantity of the existing item
                addedItem = Cart_Product.findById(Cart_Product.class,existingOrderId);
                addedItem.setQuantity(addedItem.getQuantity()+Integer.parseInt(quantity.getText().toString()));
                Toast.makeText(getActivity(),"Existing item updated" +
                        "",Toast.LENGTH_SHORT).show();
                addedItem.save();
            }

            else {  // creates a new item in the cart
                Toast.makeText(getActivity(),"New item Created",Toast.LENGTH_SHORT).show();
                addedItem =
                        new Cart_Product(userCart.getId(), product.getId(), product.getPrice(), Integer.parseInt(quantity.getText().toString()));
                addedItem.save();
            }
            userCart.setPrice(addedItem.getPrice()*addedItem.getQuantity()+userCart.getPrice());    //update price shown by the cart
            userCart.save();

        }
        else{
            Toast.makeText(getActivity(), "Order failed", Toast.LENGTH_SHORT).show();
        }

    }


    private void shareItem(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody = "Item " + product.getTitle() + " Price: Rs." + product.getPrice() + " Type: " + product.getType();
            shareBody += "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            String subject = "Style Omega Clothing Shop";
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shareIntent, "Share Using..."));
        }
        catch(Exception ex){
            Toast.makeText(getContext(),"Share Failed",Toast.LENGTH_SHORT).show();
        }
    }

    private long verifyItemExistsInCart(long cartId,long itemId){
        long valid= -1;
        List<Cart_Product> allOrders = Cart_Product.listAll(Cart_Product.class);

        for(Cart_Product xOrder: allOrders){
            if(xOrder.getCartId() == cartId && xOrder.getItemID() == itemId){
                valid = xOrder.getId();
                break;
            }
        }
        return valid;
    }


}
