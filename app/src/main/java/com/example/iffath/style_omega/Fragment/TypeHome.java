package com.example.iffath.style_omega.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iffath.style_omega.Adapter.ButtonListAdapter;
import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Model.BtnClickListener;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class TypeHome extends Fragment implements BtnClickListener{
    List<Product> products;
    String[] types;
    private String consumerType = null;
    ListView listView;
    public TypeHome() {
        // Required empty public constructor
    }
    public TypeHome(String customer){
        this.consumerType = customer;
    }
    public void setConsumerType(String consumerType){
        this.consumerType = consumerType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_home, container, false);
        products = new ArrayList<>();
//        if(products != null){
//
//        }
//        else {
            products = getConsumerItems(consumerType);
            types = getTypes();
//        }

        //inside type home fragment
        //this is to get the list of items
        final RecyclerView productGrid = view.findViewById(R.id.item_list_recycle);
        final RecyclerViewAdapter myAdapter= new RecyclerViewAdapter(this.getContext(),products);
        productGrid.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        productGrid.setAdapter(myAdapter);

        //this is for the list of item types
        listView = view.findViewById(R.id.type_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                consumerType = types[i];
                myAdapter.setProductList(getConsumerItems(consumerType));
                productGrid.setAdapter(myAdapter);
            }
        });

        ButtonListAdapter buttons = new ButtonListAdapter(types, this.getContext());
        listView.setAdapter(buttons);
        return view;
    }

    //method which gets the list of types for the user according to the type selected
    public String[] getTypes(){

        switch(consumerType){
            case "Women":
                types = new String[]{"Top","Bottom","Dress","Shalwar","Jacket"};
                break;
            case "Men":
                types = new String[]{"T-Shirt","Shirt","Bottom","Jean","Suit"};
                break;
            case "Kids":
                types=new String[]{"Top","Frock","Babysuit","Pant"};
                break;
        }
        return types;
    }

    public List<Product> getConsumerItems(String selector){
        List<Product> allProducts = SingletonProduct.getProducts();

        for (Product x:allProducts) {
            if(x.getCustomer().equals(selector)){
                products.add(x);
            }
        }
        return products;
    }

    @Override
    public void onBtnClick(int position) {

    }
}
