package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.Fragment.Detailed_item;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) { //set the values of a single card
        final Product product =productList.get(position);

        holder.title.setText(product.getTitle());
        holder.product_thumbnail.setImageResource(product.getThumbnail());
        holder.price.setText(Double.toString(product.getPrice()));
        holder.item_cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {    //detailed view of the item selected
                AppCompatActivity activity = (AppCompatActivity) view.getContext();     //gets the parent activity which holds fragments

                //the entire product object is sent by implementing the parceable interface in
                //the product model class
                Bundle args = new Bundle();
                args.putParcelable("Item",product);
                Fragment detailed_fragment = new Detailed_item();
                detailed_fragment.setArguments(args);

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.display_screen, detailed_fragment)
                        .addToBackStack(null)
                        .commit();
                activity.setTitle(holder.title.getText().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();      //method to get the count of the products available
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{     //view objects in a  single card is
        TextView title;                                            //initialized here in the holder
        ImageView product_thumbnail;
        TextView price;
        CardView item_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.item_price);
            title = itemView.findViewById(R.id.item_title);
            product_thumbnail = itemView.findViewById(R.id.item_icon_image);
            item_cardview = itemView.findViewById(R.id.item_card);
        }
    }

}
