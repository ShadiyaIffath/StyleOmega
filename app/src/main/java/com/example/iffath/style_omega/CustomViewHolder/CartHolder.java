package com.example.iffath.style_omega.CustomViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.R;

public class CartHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView thumbnail;
    public TextView category;
    public TextView consumer;
    public Button removeButton;
    public TextView cost;
    public TextView quantity;
    public CardView cartItem;
    public Button increaseButton;
    public Button decreaseButton;

    public CartHolder(@NonNull View itemView) {
        super(itemView);

        cartItem = itemView.findViewById(R.id.cart_item);
        title = itemView.findViewById(R.id.cart_item_title );
        thumbnail = itemView.findViewById(R.id.cart_item_image);
        consumer = itemView.findViewById(R.id.cart_item_consumer);
        category = itemView.findViewById(R.id.cart_item_category);
        removeButton = itemView.findViewById(R.id.item_remove);
        increaseButton = itemView.findViewById(R.id.increase);
        quantity = itemView.findViewById(R.id.cart_item_quantity);
        decreaseButton = itemView.findViewById(R.id.decrease);
        cost = itemView.findViewById(R.id.cart_item_cost);
    }
}
