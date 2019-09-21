package com.example.iffath.style_omega.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
    RecyclerView productGrid;
    private SingletonProduct singletonProduct = SingletonProduct.getInstance();

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
        types = getTypes();

        productGrid = view.findViewById(R.id.item_list_recycle);
        products = new ArrayList<>();
        products = singletonProduct.getConsumers(consumerType);
        myAdapter= new RecyclerViewAdapter(this.getContext(),products);

        productGrid.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        productGrid.setItemAnimator(new DefaultItemAnimator());
        productGrid.setAdapter(myAdapter);

        //this is for the list of item types
        listView = view.findViewById(R.id.type_list);
        ButtonListAdapter buttons = new ButtonListAdapter(types, this.getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = types[i];
                Log.i("Selected",selection);
                  Toast.makeText(getActivity(), selection, Toast.LENGTH_SHORT).show();
                  myAdapter.getFilter().filter(selection);
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

}
