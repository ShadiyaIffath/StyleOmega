package com.example.iffath.style_omega.Model;

import android.util.Log;
import android.widget.Toast;

import com.example.iffath.style_omega.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SingletonProduct {

    private static SingletonProduct productList = new SingletonProduct();
    private String gUrl;
    private static ArrayList<Product> products;
    private Gson gson;
    private OkHttpClient client;
    private Type productListTypeToken;

    private SingletonProduct(){
        products = new ArrayList<>();
        getAllProducts();
    }

    public List<Product> getProducts(){
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
    @Contract(pure = true)
    public static SingletonProduct getInstance(){
        return productList;
    }


    private void getAllProducts(){
        try {
            gUrl = "http://192.168.8.100:8080/HostelLK/ProductController";
            gson = new GsonBuilder().create();
            productListTypeToken = new TypeToken<ArrayList<Product>>() {
            }.getType();

            Log.i("Response", "Client started");
            client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    .url(gUrl)
                    .build();

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            Log.i("Response", e.toString());
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            try {
                                String message = "";
                                if (!response.isSuccessful()) {
                                    Log.i("Response", "not successful");
                                } else {
                                    if(response.body()!=null) {
                                        message = response.body().string();
                                        products = gson.fromJson(message, productListTypeToken);
                                    }
                                }
                                Log.i("JSON", message);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void makeInquiry(String sender,String email, String inquriy) throws IOException{
        try {
            gUrl = "http://192.168.8.100:8080/HostelLK/ProductController";
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("form", "contact")
                    .add("name", sender)
                    .add("email", email)
                    .add("inquiry", inquriy)
                    .build();
            Log.i("Response", "Client started");
            Request request = new Request.Builder()
                    .url(gUrl)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    String mMessage = e.getMessage();
                    Log.w("Response", mMessage);
                    //call.cancel();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    if(response.body()!=null) {
                        String mMessage = response.body().string();
                        Log.e("Response", mMessage);
                    }
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void placeOrder(long cartId){
        try {
            gUrl = "http://192.168.8.100:8080/HostelLK/ProductController";
            OkHttpClient client = new OkHttpClient();
            gson = new GsonBuilder().create();
            Dictionary orders = getItems(cartId);
            String orderedItems = gson.toJson(orders);
            RequestBody body = new FormBody.Builder()
                    .add("form", "purchased")
                    .add("items", orderedItems)
                    .build();
            Log.i("Response", "Client started");
            Request request = new Request.Builder()
                    .url(gUrl)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    String fail = e.getMessage();
                    Log.w("Response", fail);
                    //call.cancel();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    if(response.body()!=null) {
                        String mMessage = response.body().string();
                        Log.e("Response", mMessage);
                    }
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private Dictionary getItems(long cartId){
        Dictionary items = new Hashtable();
        List<Cart_Product> orders = Cart_Product.listAll(Cart_Product.class);
        for(Cart_Product x: orders){
            if(x.getCartId() == cartId) {
                items.put(x.getItemID(), x.getQuantity());
            }
        }
        return items;
    }

}
