package com.example.iffath.style_omega.Adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iffath.style_omega.CustomViewHolder.CartHolder;

import com.example.iffath.style_omega.Interface.CustomItemClickListener;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartHolder> {
    private Context context;
    CustomItemClickListener listener;
    private List<Cart_Product> orderedProducts;
    private final List<Product> allProducts = SingletonProduct.getInstance().getProducts();

    public CartRecycleAdapter(Context context, List<Cart_Product> products, CustomItemClickListener listener) {
        this.context = context;
        this.orderedProducts = products;
        this.listener = listener;
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
    public void onBindViewHolder(@NonNull final CartHolder holder, final int position) {
        Cart_Product selected = orderedProducts.get(position);

        int productId = selected.getItemID();
        Product product = getProduct(productId);

        if(product!= null){
            //Double quantity = selected.getQuantity()* product.getPrice(); //calculate cost
            holder.title.setText(product.getTitle());
            holder.category.setText(product.getType());
            holder.consumer.setText(product.getConsumer());
            Picasso.get()
                    .load(product.getThumb())
                    .error(R.drawable.dressfail1)
                    .placeholder(R.drawable.dressfail)
                    .into(holder.thumbnail);
            holder.cost.setText(Double.toString(product.getPrice()));
            holder.quantity.setText(Integer.toString(selected.getQuantity()));

            holder.cartItem.setOnTouchListener(new View.OnTouchListener() { //handle double tap feature of the card
                                                                            //which redirects user to a detailed view of the item
                private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e){
                        listener.onItemClick(holder.cartItem,position);
                        return false;
                    }

                });
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    gestureDetector.onTouchEvent(event);
                    return true;
                }
            });

            holder.increaseButton.setOnClickListener(new View.OnClickListener() { // item quantity increase function
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,position);
                    int count = Integer.parseInt(holder.quantity.getText().toString()) + 1;
                    Cart_Product product1 = orderedProducts.get(position);

                    product1.setQuantity(count);
                    product1.update();
                    orderedProducts.set(position,product1);
                    notifyItemChanged(position);
                    holder.quantity.setText(Integer.toString(count));
                }
            });
            holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {       //item quantity decrease function
                    int count = Integer.parseInt(holder.quantity.getText().toString());
                    count--;
                    if(count > 0) {
                        listener.onItemClick(v, position);
                        Cart_Product product1 = orderedProducts.get(position);

                        product1.setQuantity(count);
                        product1.update();

                        orderedProducts.set(position,product1);
                        notifyItemChanged(position);
                        holder.quantity.setText(Integer.toString(count));
                    }
                }
            });
            holder.removeButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {        //remove clothing item from cart
                        listener.onItemClick(v, position);
                        return false;
                }
            });

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

}
