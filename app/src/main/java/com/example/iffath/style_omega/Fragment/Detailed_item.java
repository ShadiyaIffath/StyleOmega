package com.example.iffath.style_omega.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iffath.style_omega.Adapter.ImageSlider;
import com.example.iffath.style_omega.BuildConfig;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;

import com.example.iffath.style_omega.R;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Detailed_item extends Fragment implements View.OnClickListener{
    TextView title;
    TextView price;
    TextView description;
    TextView category;
    Product product;
    TextView size;
    TextView color;
    TextView quantity;
    TextView priceTag;
    TextView shareButton;
    Button add;
    Button sub;
    Button addToCart;
    ViewPager pages;
    private int pageCount = 0;
    private int currentPage =0;

    public Detailed_item(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_item, container, false);
        product = null;

        pages = view.findViewById(R.id.image_pages);
        title = view.findViewById(R.id.product_title);
        description = view.findViewById(R.id.product_desc);
        category = view.findViewById(R.id.product_category);
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
                price.append(Double.toString(product.getPrice()));
                color.append(product.allColors());
                size.append(product.allSizes());
                title.setText(product.getTitle());
                description.setText(product.getDescription());
                category.setText(product.getType());

                String[] images = product.getImages();
                pages.setAdapter(new ImageSlider(getContext(),images));
                CirclePageIndicator indicator = view.findViewById(R.id.image_indicator);
                indicator.setViewPager(pages);
                float density = getResources().getDisplayMetrics().density;
                indicator.setRadius(5 * density);
                pageCount = images.length;

                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (currentPage == pageCount) {
                            currentPage = 0;
                        }
                        pages.setCurrentItem(currentPage++, true);
                    }
                };
                //auto scroll of the pager
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (currentPage == pageCount) {
                            currentPage = 0;
                        }
                        handler.post(Update);
                    }
                }, 3000, 3000);

                // page listener for the indicator
                indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        currentPage = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
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

    private void increaseQuantity(){     //increase button
        int sum = Integer.parseInt(quantity.getText().toString());
        sum++;
        if(product.getQuantity() < sum){
            Toast.makeText(getActivity(), "You cannot increase anymore", Toast.LENGTH_SHORT).show();
        }else{
            quantity.setText(String.valueOf(sum));
            priceTag.setText(String.valueOf(sum*product.getPrice()));
        }
    }

    private void decreaseQuantity(){     //decrease button
        int sum = Integer.parseInt(quantity.getText().toString());
        sum--;
        if(sum < 0){
            Toast.makeText(getActivity(), "You cannot decrease anymore", Toast.LENGTH_SHORT).show();
        }else{
            quantity.setText(String.valueOf(sum));
            priceTag.setText(String.valueOf(sum*product.getPrice()));
        }
    }

    private void addToCart(){        //Create cart object on confirmation
        int sum = Integer.parseInt(quantity.getText().toString());
        if(sum <= 0){
            Toast.makeText(getActivity(), "You cannot place the order", Toast.LENGTH_SHORT).show();
        }
        else {
            showPopupDialog();
        }
    }

    private void showPopupDialog(){    //method which shows a dialog for confirmation when customer clicks add to cart
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
            int itemCount = Integer.parseInt(quantity.getText().toString());
            if(existingOrderId != -1){ // updates quantity of the existing item
                addedItem = Cart_Product.findById(Cart_Product.class,existingOrderId);
                addedItem.setQuantity(addedItem.getQuantity()+itemCount);
                Toast.makeText(getActivity(),"Existing item updated" +
                        "",Toast.LENGTH_SHORT).show();
                addedItem.save();
                userCart.setPrice(userCart.getPrice() +(itemCount * product.getPrice()));
            }

            else {  // creates a new item in the cart
                Toast.makeText(getActivity(),"New item Created",Toast.LENGTH_SHORT).show();
                addedItem = new Cart_Product
                        (userCart.getId(), product.getId(), product.getPrice(), itemCount);
                addedItem.save();
                userCart.setPrice(addedItem.getPrice()*addedItem.getQuantity()+userCart.getPrice());    //update price shown by the cart
            }
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
            String shareBody = "Item: " +
                    "" + product.getTitle() + "\nPrice: Rs." + product.getPrice() + "\nType: "
                    + product.getType()+"\n"+product.getThumb();
            shareBody += " \nThe url of the application: https://play.google.com/store/apps/details?id="
                    + BuildConfig.APPLICATION_ID + "\n\n";
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
