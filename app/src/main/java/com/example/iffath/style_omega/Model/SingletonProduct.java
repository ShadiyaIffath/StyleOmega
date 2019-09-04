package com.example.iffath.style_omega.Model;

import com.example.iffath.style_omega.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SingletonProduct {

    private static SingletonProduct productList = new SingletonProduct();
    private static String url = "http://192.168.1.4:8080/Test/productController";
    private static List<Product> products;
    Request request;

    OkHttpClient client;

    private SingletonProduct(){
            products = new ArrayList<>();
//            client = new OkHttpClient();
//
//            request = new Request.Builder().url(url).build(); //creates a request

//        client.newCall(request)
//                .enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                        //display a proper message
//                    }
//                }
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Yellow");
        colors.add("Blue");
        colors.add("Green");

        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("M");
        sizes.add("XL");
        sizes.add("S");
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Tops","Women",50,12000,R.drawable.dress,new ArrayList<String>(),new ArrayList<String>()));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Bottoms","Women",50,12250,R.drawable.dress2,colors,sizes));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Men",50,1500,R.drawable.dress3,colors,sizes));
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Jumpsuit","Kids",50,12000,R.drawable.dress,colors,sizes));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Jacket","Women",50,12250,R.drawable.dress2,colors,sizes));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Women",50,1500,R.drawable.dress3,colors,sizes));
        products.add(new Product("Frill Bardot Jungle Print Dress","Pattern Type : Tropical\n" +
                "Neckline : Off the Shoulder\n"+
                "Details : Frill","Dress","Men",50,12000,R.drawable.dress,colors,sizes));
        products.add(new Product("Maxi long Dress","Prestige Boat Neck Full Sleeves",
                "Dress","Women",50,12250,R.drawable.dress2,colors,sizes));
        products.add(new Product("Polyester Red Solid","Fabric: Polyester\n" +
                "Sleeves are included\n" +
                "Length up to 35 inch","Dress","Kids",50,1500, R.drawable.dress3,colors,sizes));

    }

    public static List<Product> getProducts(){
        return products;
    }
}