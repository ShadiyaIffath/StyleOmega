package com.example.iffath.style_omega.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iffath.style_omega.Adapter.RecyclerViewAdapter;
import com.example.iffath.style_omega.Fragment.TypeHome;
import com.example.iffath.style_omega.Model.Cart;
import com.example.iffath.style_omega.Model.Product;
import com.example.iffath.style_omega.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if(savedInstanceState == null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.recycleTest, new TypeHome())
//                    .commit();
//        }
//        products = new ArrayList<>();
//        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
//                "Neckline : Off the Shoulder\n"+
//                "Details : Frill","Dress","Women",50,12000,R.drawable.dress));
//        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
//                "Dress","Women",50,12250,R.drawable.dress2));
//        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
//                "Sleeves are included\n" +
//                "Length up to 35 inch","Dress","Women",50,1500,R.drawable.dress3));
//        RecyclerView myview = findViewById(R.id.recycleTest); //inside type home fragment
//        RecyclerViewAdapter myAdapter= new RecyclerViewAdapter(this,products);
//        myview.setLayoutManager(new GridLayoutManager(this,3));
//        myview.setAdapter(myAdapter);



    }
}
