package com.example.iffath.style_omega.Model;

import android.util.Log;
import android.widget.Toast;

import com.example.iffath.style_omega.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SingletonProduct {

    private static SingletonProduct productList = new SingletonProduct();
    private static String gUrl;
    private static ArrayList<Product> products;
    private Gson gson;
    private OkHttpClient client;
    Type productListTypeToken;
    Request request;

    private SingletonProduct(){
        products = new ArrayList<>();
        getAllProducts();
    }

    public List<Product> getProducts(){
    //    setTheProducts();
        return products;
    }

    public static Product findProduct(int id){
        Product temp = null;
        for (Product x:products) {
                if(x.getId()== id){
                    temp= x;
                    break;
                }
        }
        return temp;
    }

    public List<Product> getConsumers(String consumer){
        Log.i("Consumer",consumer);
        List<Product> consumerProduct= new ArrayList<>();
        for(Product x: products){
            if(x.getConsumer().equals(consumer)){
                consumerProduct.add(x);
            }
        }
        return consumerProduct;
    }
    public static SingletonProduct getInstance(){
        return productList;
    }


    public void getAllProducts(){
        gson = new GsonBuilder().create();
        productListTypeToken = new TypeToken<ArrayList<Product>>() {}.getType();
        gUrl = "http://192.168.1.4:8080/HostelLK/ProductController";
        Log.i("Response","Client started");
        client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(gUrl)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("Response",e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            String message ="";
                            if (!response.isSuccessful()) {
                                Log.i("Response", "not successful");
                            } else {
                                message = response.body().string();
                                products = gson.fromJson(message, productListTypeToken);
                            }
                            Log.i("JSON", message);
                        }
                        catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

}
