package com.example.iffath.style_omega.CustomViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.R;

import org.w3c.dom.Text;

public class HistoryHolder extends RecyclerView.ViewHolder {
    public TextView receiverName;
    public TextView cartId;
    public TextView checkedOutOn;
    public TextView address;
    public TextView totalCost;
    public TextView paymentType;
    public CardView cartCard;
    public TextView contactNum;


    public HistoryHolder(@NonNull View itemView) {
        super(itemView);
        cartId = itemView.findViewById(R.id.history_cart_id);
        checkedOutOn = itemView.findViewById(R.id.history_checkedout);
        receiverName = itemView.findViewById(R.id.history_recipient);
        address = itemView.findViewById(R.id.history_address);
        totalCost = itemView.findViewById(R.id.history_cost);
        paymentType = itemView.findViewById(R.id.history_payment_type);
        cartCard = itemView.findViewById(R.id.history_card);
        contactNum = itemView.findViewById(R.id.history_contactnum);
    }
}
