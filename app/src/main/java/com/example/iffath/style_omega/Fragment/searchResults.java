package com.example.iffath.style_omega.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class searchResults extends Fragment {
    private List<Product> products;
    private RecyclerViewAdapter myAdapter;
    private RecyclerView productGrid;
    private SingletonProduct singletonProduct = SingletonProduct.getInstance();

    public searchResults() {
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
       View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        getActivity().setTitle(R.string.search_title);

        productGrid = view.findViewById(R.id.search_list);
        products = new ArrayList<>();
        products = singletonProduct.getProducts();
        myAdapter= new RecyclerViewAdapter(this.getContext(),products);

        productGrid.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        productGrid.setItemAnimator(new DefaultItemAnimator());
        productGrid.setAdapter(myAdapter);
       return view;
    }
    public void setMyAdapterFilter(String searchValue){
        myAdapter.getFilter().filter(searchValue);
    }


}
