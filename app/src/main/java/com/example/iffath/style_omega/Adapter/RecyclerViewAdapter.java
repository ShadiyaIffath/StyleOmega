package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.Fragment.TypeHome;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;

    public RecyclerViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item_cloth,parent,false); //create new card for each product
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //set the values of a single card
        Product product =productList.get(position);

        holder.title.setText(product.getTitle());
        holder.product_thumbnail.setImageResource(product.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return productList.size();      //method to get the count of the products available
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{     //view objects in a  single card is
        TextView title;                                            //initialized here in the holder
        ImageView product_thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            product_thumbnail = itemView.findViewById(R.id.item_icon_image);
        }
    }

}
