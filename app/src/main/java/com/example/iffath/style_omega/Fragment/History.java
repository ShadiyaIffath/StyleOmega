package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iffath.style_omega.Adapter.HistoryCartAdapter;
import com.example.iffath.style_omega.CustomViewHolder.HistoryHolder;
import com.example.iffath.style_omega.Interface.CustomItemClickListener;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Cart_Product;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class History extends Fragment {

    RecyclerView recyclerView;
    List<Cart> orders;
    HistoryCartAdapter adapter;

    public History() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle("History");
        orders = new ArrayList<>();
        orders = getCarts();
        if(!orders.isEmpty()){
            recyclerView = view.findViewById(R.id.cart_history);
            recyclerView.setHasFixedSize(true);
            adapter = new HistoryCartAdapter(this.getContext(), orders, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
//                    if(v.getId() == R.id.history_card){
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.display_screen, new ViewCart())
//                                .addToBackStack(null)
//                                .commit();
//                    }
                }
            });
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else{
            Toast.makeText(getActivity(), "You have not made any purchases", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private List<Cart> getCarts(){

        List<Cart> allCarts = Cart.listAll(Cart.class);
        List<Cart> allOrders = new ArrayList<>();
        for(Cart s: allCarts){
            if(s.isStatus()){
                allOrders.add(s);
            }
        }
        return allOrders;
    }


}
