package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.Fragment.Detailed_item;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Product> productList;
    private List<Product> filteredProducts;

    public RecyclerViewAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
        this.filteredProducts = products;
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
        final Product product = filteredProducts.get(position); //get a specific product

        holder.title.setText(product.getTitle());

        Picasso.get()
                .load(product.getThumb())
                .error(R.drawable.dressfail1)
                .placeholder(R.drawable.dressfail)
                .fit()
                .centerCrop()
                .into(holder.product_thumbnail);
        holder.price.setText("Rs."+Double.toString(product.getPrice()));
        if(product.getQuantity() ==0){
            holder.quantity.setText("SOLD OUT");
        }
        else{
            holder.quantity.setText(Integer.toString(product.getQuantity()));
        }
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
                transaction.replace(R.id.display_screen, detailed_fragment,product.getConsumer())
                        .addToBackStack(product.getConsumer())
                        .commit();
                activity.setTitle(holder.title.getText().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredProducts.size();      //method to get the count of the products available
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String typeSelected = charSequence.toString();
                if (typeSelected.isEmpty()) {
                    filteredProducts = productList;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for(String search: typeSelected.split(" ")) {
                        for (Product temp : productList) {
                            if(!filteredList.contains(temp)) {
                                if (search.equalsIgnoreCase(temp.getType())) {
                                    filteredList.add(temp);
                                    Log.i("Added", temp.getTitle());
                                    continue;
                                }
                                search.toLowerCase();
                                if (search.contains(temp.getTitle().toLowerCase())) {
                                    filteredList.add(temp);
                                    Log.i("Added", temp.getTitle());
                                    continue;
                                }
                                if (temp.getTitle().toLowerCase().contains(search)) {
                                    filteredList.add(temp);
                                    Log.i("Added", temp.getTitle());
                                    continue;
                                }
                                if (search.equals(temp.getConsumer().toLowerCase())) {
                                    filteredList.add(temp);
                                    Log.i("Added", temp.getTitle());
                                    continue;
                                }
                                if (isInteger(search) && (Integer.parseInt(search) > temp.getPrice())) {
                                    filteredList.add(temp);
                                    Log.i("Added", temp.getTitle());
                                    continue;
                                }
                            }
                        }
                    }
                    filteredProducts = filteredList;
                    if(filteredList.isEmpty()){
                        Toast.makeText(context,"No items",Toast.LENGTH_SHORT).show();
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredProducts;
                return  filterResults;
            }
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProducts = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    //method which validates if a string can be converted to an integer
    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{     //view objects in a  single card is
        TextView title;                                            //initialized here in the holder
        ImageView product_thumbnail;
        TextView price;
        TextView quantity;
        CardView item_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.item_quantity);
            price = itemView.findViewById(R.id.item_price);
            title = itemView.findViewById(R.id.item_title);
            product_thumbnail = itemView.findViewById(R.id.item_icon_image);
            item_cardview = itemView.findViewById(R.id.item_card);
        }
    }


}
