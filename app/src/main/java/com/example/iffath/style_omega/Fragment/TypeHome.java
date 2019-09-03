package com.example.iffath.style_omega.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class TypeHome extends Fragment {
    List<Product> products;
    public TypeHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_home, container, false);
        products = new ArrayList<>();
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Dress","Women",50,12000,R.drawable.dress));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Dress","Women",50,12250,R.drawable.dress2));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Women",50,1500,R.drawable.dress3));
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Dress","Women",50,12000,R.drawable.dress));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Dress","Women",50,12250,R.drawable.dress2));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Women",50,1500,R.drawable.dress3));
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Dress","Women",50,12000,R.drawable.dress));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Dress","Women",50,12250,R.drawable.dress2));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Women",50,1500,R.drawable.dress3));
        RecyclerView myview = view.findViewById(R.id.item_list_recycle); //inside type home fragment
        RecyclerViewAdapter myAdapter= new RecyclerViewAdapter(this.getContext(),products);
        myview.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        myview.setAdapter(myAdapter);

        return view;
    }

}
