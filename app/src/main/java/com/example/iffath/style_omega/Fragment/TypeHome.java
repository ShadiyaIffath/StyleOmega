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
import android.widget.Toast;

import com.example.iffath.style_omega.Adapter.ButtonListAdapter;
import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.Model.SingletonProduct;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class TypeHome extends Fragment {
    List<Product> products;
    String[] types;
    private String consumerType = null;
    ListView listView;
    RecyclerViewAdapter myAdapter;

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
        View view = inflater.inflate(R.layout.fragment_type_home, container, false);
        getActivity().setTitle(consumerType);

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
        RecyclerView productGrid = view.findViewById(R.id.item_list_recycle);
        myAdapter= new RecyclerViewAdapter(this.getContext(),products);
        productGrid.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        productGrid.setAdapter(myAdapter);

        //this is for the list of item types
        listView = view.findViewById(R.id.type_list);
        ButtonListAdapter buttons = new ButtonListAdapter(types, this.getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = types[i];
                  Toast.makeText(getActivity(), selection, Toast.LENGTH_SHORT).show();
                  myAdapter.refreshView(selection);
                  myAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(buttons);

        return view;
    }

    //method which gets the list of types for the user according to the type selected
    public String[] getTypes(){

        switch(consumerType){
            case "Women":
                types = new String[]{"Tops","Bottoms","Dresses","Shalwars","Jackets"};
                break;
            case "Men":
                types = new String[]{"T-Shirts","Shirts","Jeans","Blazers","Suits"};
                break;
            case "Kids":
                types=new String[]{"Tops","Frocks","Babysuits","Pants"};
                break;
        }
        return types;
    }

    //clothing items according to consumer type(women,men,kids)
    public List<Product> getConsumerItems(String selector){
        List<Product> allProducts = SingletonProduct.getProducts();

        for (Product x:allProducts) {
            if(x.getConsumer().equals(selector)){
                products.add(x);
            }
        }
        return products;
    }

    //list of the products of a specific type is updated
    public void updateProductType(String selectedType){

        for(Product temp: products){
            if(temp.getType().equalsIgnoreCase(selectedType)){
                products.add(temp);
            }
        }
    }

}
