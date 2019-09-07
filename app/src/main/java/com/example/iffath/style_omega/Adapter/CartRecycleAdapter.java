package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.CustomViewHolder.CartHolder;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.R;

import org.w3c.dom.Text;

import java.util.List;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartHolder> implements View.OnClickListener {
    private Context context;

    private List<Cart_Product> orderedProducts;
    private final List<Product> allProducts = SingletonProduct.getProducts();

    public CartRecycleAdapter(Context context, List<Cart_Product> products) {
        this.context = context;
        this.orderedProducts = products;

    }


    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_cart_item,parent,false); //create new card for each product
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Cart_Product selected = orderedProducts.get(position);

//        holder.title
        int productId = selected.getItemID();
        Product product = getProduct(productId);

        if(product!= null){
//            Double quantity = selected.getQuantity()* product.getPrice(); //calculate cost
            holder.title.setText(product.getTitle());
            holder.category.setText(product.getType());
            holder.consumer.setText(product.getConsumer());
            holder.thumbnail.setImageResource(product.getThumbnail());
            holder.cost.setText(Double.toString(selected.getPrice()));
            holder.quantity.setText(Integer.toString(selected.getQuantity()));

            holder.cartItem.setOnClickListener(this);
            holder.increaseButton.setOnClickListener(this);
            holder.decreaseButton.setOnClickListener(this);
            holder.removeButton.setOnClickListener(this);
//            holder.thumbnail.setImageResource(product.getThumbnail());
//            holder.

        }

    }

    @Override
    public int getItemCount() {
        return orderedProducts.size();
    }

    public Product getProduct(int productId){
        Product product= null;
        for (Product x:allProducts) {
            if(x.getId() == productId){
                product = x;
                break;
            }
        }
        return product;
    }


    @Override
    public void onClick(View view) {

    }
}
