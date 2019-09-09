package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iffath.style_omega.Activity.Launcher;
import com.example.iffath.style_omega.Adapter.CartRecycleAdapter;
import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Interface.CustomItemClickListener;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewCart extends Fragment implements View.OnClickListener {

    CartRecycleAdapter myAdapter;
    SharedPreferences sharedPreferences;
    RecyclerView recycler_cart;
    TextView totalPrice;
    Button checkout;
    Cart userCart;
    List<Cart_Product> cartItems;

    public ViewCart(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_cart, container, false);
        getActivity().setTitle("My Cart");

        cartItems = new ArrayList<>();
        cartItems = getProducts();
        if(cartItems!= null){
            totalPrice = view.findViewById(R.id.totalPricetxt);
            checkout = view.findViewById(R.id.checkout_button);
            checkout.setOnClickListener(this);

            recycler_cart = view.findViewById(R.id.recycler_cart);
            recycler_cart.setHasFixedSize(true);
            myAdapter = new CartRecycleAdapter(this.getContext(),cartItems, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    int id = v.getId();
                    double cost = Double.parseDouble(totalPrice.getText().toString());
                    Cart_Product product = cartItems.get(position);
                    switch(id){
                        case(R.id.increase):
                            cost += product.getPrice();
                            break;
                        case(R.id.decrease):
                            cost -= product.getPrice();
                            break;
                        case(R.id.item_remove):
                            cost -= (product.getPrice() * product.getQuantity());
                            cartItems.remove(position);
                            myAdapter.notifyItemRemoved(position);
                            break;
                        case R.id.cart_item:
                            Bundle args = new Bundle();
                            Product productSelected = null;
                            args.putParcelable("Item",productSelected);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.display_screen,new Detailed_item())
                                    .addToBackStack(null)
                                    .commit();
                    }
                    totalPrice.setText(Double.toString(cost));
                }
            });
            recycler_cart.setAdapter(myAdapter);
            recycler_cart.setLayoutManager(new LinearLayoutManager(getContext()));

        }else{
            Toast.makeText(getActivity(),"No items",Toast.LENGTH_SHORT).show();
        }




        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"Check out",Toast.LENGTH_SHORT).show();
    }

    public List<Cart_Product> getProducts(){
        List<Cart_Product> orderedProducts = new ArrayList<>();

        sharedPreferences = getActivity().getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("user",-1);

        if(id != -1){
            List<Cart> pendingCart = Cart.find(Cart.class,"status=?","0");
            for(Cart x: pendingCart){
                if (x.getUserId()!= id){
                    pendingCart.remove(x);
                }
            }
            userCart = pendingCart.get(0);
        }

        //static test code
        orderedProducts.add(new Cart_Product(userCart.getId(),1,12000,1));
        orderedProducts.add(new Cart_Product(userCart.getId(), 3,1500,2));
        orderedProducts.add(new Cart_Product(userCart.getId(), 4,12000,1));
        orderedProducts.add(new Cart_Product(userCart.getId(),5,12250,1));
        return orderedProducts;
    }
}
