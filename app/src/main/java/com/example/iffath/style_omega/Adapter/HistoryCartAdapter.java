package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.CustomViewHolder.HistoryHolder;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Recipient;
import com.example.iffath.style_omega.R;

import java.util.List;

public class HistoryCartAdapter extends RecyclerView.Adapter<HistoryHolder> {
    private Context context;
    private List<Cart> allCarts;

    public HistoryCartAdapter(Context context,List<Cart> allCarts){
        this.context = context;
        this.allCarts = allCarts;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_cart_history,parent,false); //create new card for each card
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryHolder holder, int position) {
        Cart cart = allCarts.get(position);
        Recipient recipient = cart.getRecipient();

        if(cart != null && recipient !=null){
            holder.receiverName.setText(recipient.getName());
            holder.checkedOutOn.setText(cart.getUpdated());
            holder.cartId.setText(Long.toString(cart.getId()));
            holder.paymentType.setText(recipient.getPaymentType());
            holder.totalCost.setText(Double.toString(cart.getPrice()));
            holder.contactNum.setText(recipient.getContactNumber());
            holder.address.setText(recipient.getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return allCarts.size();
    }
}
