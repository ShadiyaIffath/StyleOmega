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
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.Model.User;
import com.example.iffath.style_omega.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        totalPrice = view.findViewById(R.id.totalPricetxt);
        checkout = view.findViewById(R.id.checkout_button);
        userCart = null;

        cartItems = new ArrayList<>();
        cartItems = getProducts();

        if (userCart != null || !cartItems.isEmpty()) {

                checkout.setOnClickListener(this);

                recycler_cart = view.findViewById(R.id.recycler_cart);
                recycler_cart.setHasFixedSize(true);
                myAdapter = new CartRecycleAdapter(this.getContext(), cartItems, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        int id = v.getId();
                        double cost = Double.parseDouble(totalPrice.getText().toString());
                        Cart_Product product = cartItems.get(position);
                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        switch (id) {
                            case (R.id.increase):
                                cost += product.getPrice();
                                userCart.setUpdated(date);
                                userCart.setPrice(cost);
                                userCart.save();
                                break;
                            case (R.id.decrease):
                                cost -= product.getPrice();
                                userCart.setUpdated(date);
                                userCart.setPrice(cost);
                                userCart.save();
                                break;
                            case (R.id.item_remove):
                                cost -= (product.getPrice() * product.getQuantity());
                                cartItems.remove(position);
                                product.delete();
                                userCart.setUpdated(date);
                                userCart.setPrice(cost);
                                userCart.save();
                                myAdapter.notifyItemRemoved(position);

                                if (cartItems.isEmpty()) {    //disable checkout button when there are no items in the cart
                                    checkout.setEnabled(false);
                                    Toast.makeText(getActivity(), "Cart is empty", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case R.id.cart_item:
                                Bundle args = new Bundle();
                                Product productSelected = SingletonProduct.findProduct(product.getItemID());

                                //the entire product object is sent by implementing the parceable interface in
                                //the product model class
                                args.putParcelable("Item", productSelected);
                                Fragment detailed_fragment = new Detailed_item();
                                detailed_fragment.setArguments(args);

                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.display_screen, detailed_fragment)
                                        .addToBackStack(null)
                                        .commit();
                                getActivity().setTitle(productSelected.getTitle());
                        }
                        totalPrice.setText(Double.toString(cost));
                    }
                });
                recycler_cart.setAdapter(myAdapter);
                recycler_cart.setLayoutManager(new LinearLayoutManager(getContext()));

            } else {
                Toast.makeText(getActivity(), "No items", Toast.LENGTH_SHORT).show();
                checkout.setEnabled(false);
            }

        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"Check out",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.display_screen, new DeliveryLocation())
                .addToBackStack(null)
                .commit();
        getActivity().setTitle("Check Out");
    }

    public List<Cart_Product> getProducts(){
        List<Cart_Product> orderedProducts = new ArrayList<>();

        sharedPreferences = getActivity().getSharedPreferences(Launcher.keyPreference, Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("user",-1);
        double sum = 0;
        if(id != -1) {
            // retrieve existing cart of user
            List<Cart> pendingCart = Cart.listAll(Cart.class);
            for (Cart x : pendingCart) {
                if (x.getUserId() == id && !x.isStatus()) {
                    userCart = x;
                    break;
                }
            }

        }

        if(userCart != null) {
            long cartId = userCart.getId();
            sum = userCart.getPrice();

            List<Cart_Product> allProductsOrdered = Cart_Product.listAll(Cart_Product.class);
            for (Cart_Product xProduct : allProductsOrdered) {
                if (xProduct.getCartId() == cartId) {
                    orderedProducts.add(xProduct);
                }
            }

            totalPrice.setText(Double.toString(sum));
        }
        return orderedProducts;
    }
}