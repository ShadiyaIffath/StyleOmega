package com.example.iffath.style_omega.Model;

import android.util.Log;

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

    public void setTheProducts(){
        String[] sizes = {"XS","S","M","L"};

        products.add(new Product(1, "Women Burgundy Solid Maxi Dress",
                "Burgundy and beige solid woven maxi dress with attached jacket, has a mandarin collar, three-quarter sleeves, an attached lining, flared hem",
                "Dresses", "Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/5559085/2018/5/9/11525849854374-Athena-Women-Burgundy-Solid-Maxi-Dress-4291525849853320-1.jpg"
                , 3, 1199,new String[]{"Red","Green","Black"}
                ,sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/5559085/2018/5/9/11525849853381-Athena-Women-Burgundy-Solid-Maxi-Dress-4291525849853320-5.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/5559085/2018/5/9/11525849853848-Athena-Women-Burgundy-Solid-Maxi-Dress-4291525849853320-3.jpg"}));

        products.add(new Product(2,"Women Navy Blue & White Solid A-Line Layered Dress",
                "Navy Blue solid woven A-line dress, has a round neck, sleeveless, flared hem\n" +
                        "Comes with navy blue and white printed open front ethnic jacket, has short sleeves",
                "Dresses","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8076695/2018/12/7/8419b6de-ac4f-4b08-b3e0-d1e7bec7f7eb1544169388376-AKS-Women-Dresses-7371544169386924-1.jpg",
                5,759,new String[]{"Orange","Blue","Black"},
                sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8076695/2018/12/7/4cc8af9c-26c9-4c18-91d6-6fef4e4af7aa1544169388301-AKS-Women-Dresses-7371544169386924-3.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8076695/2018/12/7/0e04af82-6329-4e45-92e1-a55a3f8ea3db1544169388266-AKS-Women-Dresses-7371544169386924-4.jpg"}));

        products.add(new Product(3,"Women White Printed Fit & Flare Dress",
                "White and blue printed woven fit and flare dress, has a round neck, sleeveless, concealed zip closure, an attached lining, flared hem",
                "Dresses", "Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9327599/2019/4/30/ebfff17c-191b-4096-817e-139fd28a68d51556605606312-MISH-Women-White-Printed-A-Line-Dress-6681556605605094-3.jpg",
                2,740,new String[]{"White"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9327599/2019/4/30/8db985d5-8266-4941-a421-f2c838ef3aa91556605606289-MISH-Women-White-Printed-A-Line-Dress-6681556605605094-4.jpg"}));

        products.add(new Product(4,"Women Navy Blue Solid Bardot Top",
                "Navy Blue solid woven regular bardot top, has a shoulder straps, three-quarter sleeves",
                "Tops","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2108754/2017/9/8/11504850123837-Trend-Arrest-Women-Tops-3471504850123630-1.jpg",
                5,479,new String[]{"Blue"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2108754/2017/9/8/11504850123805-Trend-Arrest-Women-Tops-3471504850123630-2.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2108754/2017/9/8/11504850123724-Trend-Arrest-Women-Tops-3471504850123630-5.jpg"}));

        products.add(new Product(5,"Women Beige Printed A-Line Top",
                "Beige and pink printed woven A-line top, has shoulder straps, three-quarter sleeves",
                "Tops","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7581493/2018/10/10/5a05ed21-06bf-4fd3-a880-cd8b02a7366d1539166924979-AASK-Women-Beige-Printed-A-Line-Top-3641539166922043-1.jpg",
                4,388,new String[]{"Beige"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7581493/2018/10/10/33864d9b-84bd-4608-9c16-839fd2e38fa61539166924921-AASK-Women-Beige-Printed-A-Line-Top-3641539166922043-3.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7581493/2018/10/10/34b6b5b0-a7c5-4472-b8fd-942b401154451539166924876-AASK-Women-Beige-Printed-A-Line-Top-3641539166922043-5.jpg"}));

        products.add(new Product(6,"Time Travlr Women Pink Slim Fit Checked Casual Shirt",
                "Pink checked casual shirt, has a spread collar, a full button placket, long sleeves with roll-up tab feature, two chest pockets, curved hem",
                "Tops","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1719876/2018/8/14/00823493-e2c6-4234-9eb7-1f762bb537301534245866080-Roadster-Time-Travlr-Women-Pink-Slim-Fit-Checked-Casual-Shirt-3851534245865854-2.jpg",
                2,779,new String[]{"Beige"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1719876/2018/8/14/08590ff0-41c6-4599-a304-d1bff9e4bab41534245866127-Roadster-Time-Travlr-Women-Pink-Slim-Fit-Checked-Casual-Shirt-3851534245865854-1.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1719876/2018/8/14/a6888950-ab17-46e9-a8c8-4d64834f88f61534245866044-Roadster-Time-Travlr-Women-Pink-Slim-Fit-Checked-Casual-Shirt-3851534245865854-3.jpg"}));

        products.add(new Product(7,"Women Black Solid Biker Jacket",
                "Black solid biker jacket, has a mandarin collar, two pockets, zip closure, long sleeves, straight hem, polyester lining",
                "Jackets","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2018/12/7/4363b624-0d7b-49b9-adf8-95fff3d83c591544188736909-3.jpg",
                2,2100,new String[]{"Black","Brown"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2018/12/7/13d43914-1ff5-43ea-9d00-d1fb06d4d1a11544188736864-1.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2018/12/7/54c88b95-185e-459c-9cc5-9ed34fa1aab11544188736950-5.jpg",}));

        products.add(new Product(8,"Women Black Skinny Fit High-Rise Clean Look Stretchable Jeans","Black dark wash 5-pocket high-rise jeans, clean look, no fade, has a button and zip closure, and waistband with belt loops",
                "Bottoms","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2019/7/4/f72e1f99-4e1b-4f80-97d8-dc20c5de612d1562221950490-1.jpg",
                5,1449,
                new String[]{"Black","Brown"},sizes,new String[]
                {"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2019/7/4/8b1d5a99-7ea9-492a-86d6-095f17ff81681562221950522-2.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/productimage/2019/7/4/b68436c8-b8aa-449a-a937-cdfaa953160c1562221950598-4.jpg"}));

        products.add(new Product(9,"Women Pack of 2 Solid Slim Fit Track Pants",
                "Pack of 2 solid track pants in grey and black, each has two pockets, an elasticated waistband with an inner drawstring fastening",
                "Botoms","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8799629/2019/2/14/d69ef794-e7a9-4a00-a863-43423ac43af01550124827715-Boston-Club-Women-Pack-of-2-Solid-Slim-Fit-Track-Pants-62915-1.jpg",
                3,816,
                new String[]{"Black","Grey","White"},
                sizes,new String[]{
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8799629/2019/2/14/f09be3ad-c13d-4d52-999e-240afb8dea0d1550124827518-Boston-Club-Women-Pack-of-2-Solid-Slim-Fit-Track-Pants-62915-6.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/8799629/2019/2/14/a3905b43-39c0-4d73-a154-e98e585aca821550124827601-Boston-Club-Women-Pack-of-2-Solid-Slim-Fit-Track-Pants-62915-5.jpg"}));

        products.add(new Product(10,"Women Beige Churidar","Beige solid churidar, has an elasticated waistband",
                "Shalwars","Women",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/6819344/2018/7/10/a0e400c6-66e1-43ef-b686-4aaac98add891531219398693-Melange-by-Lifestyle-Women-Beige-Churidar-6421531219397736-4.jpg",
                1,499,
                new String[]{"Beige","Black","White"},
                sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/6819344/2018/7/10/82024266-522a-4472-b4b9-118c240fb3251531219398713-Melange-by-Lifestyle-Women-Beige-Churidar-6421531219397736-3.jpg"}));

        products.add(new Product(11,"Men Maroon Printed Round Neck T-shirt",
                "Maroon and black printed T-shirt, has a round neck, long sleeves",
                "T-Shirts","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2378362/2018/6/9/405568f1-c3c1-4713-9c38-6dd95ac962d31528527188543-Moda-Rapido-Men-Maroon-Printed-Round-Neck-T-shirt-3811528527-2.jpg",
                5,649,
                new String[]{"Maroon"},
                sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2378362/2018/6/9/33523035-65f5-4fcb-b7a5-4062e656d10b1528527188511-Moda-Rapido-Men-Maroon-Printed-Round-Neck-T-shirt-3811528527-3.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2378362/2018/6/9/ed7b0cd1-8fa7-4f65-95b4-36518bfa94461528527188485-Moda-Rapido-Men-Maroon-Printed-Round-Neck-T-shirt-3811528527-4.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2378362/2018/6/9/405568f1-c3c1-4713-9c38-6dd95ac962d31528527188543-Moda-Rapido-Men-Maroon-Printed-Round-Neck-T-shirt-3811528527-2.jpg"}));

        products.add(new Product(12,"Grey Flock Print T-shirt","Grey T-shirt with a tinge of blue and melange effect, has a round neck, patches along the shoulders, long sleeves and brand flock print on the front",
                "T-Shirts","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1301358/2016/11/21/11479716524889-Roadster-Charcoal-Grey-Flock-Print-Sweatshirt-1311479716524565-1.jpg",
                3,639,
                new String[]{"Black","Grey","White"},
                sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1301358/2016/11/21/11479716524889-Roadster-Charcoal-Grey-Flock-Print-Sweatshirt-1311479716524565-1.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1301358/2016/11/21/11479716524866-Roadster-Charcoal-Grey-Flock-Print-Sweatshirt-1311479716524565-2.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1301358/2016/11/21/11479716524841-Roadster-Charcoal-Grey-Flock-Print-Sweatshirt-1311479716524565-3.jpg"}));

        products.add(new Product(13,"Men Navy & Brown Slim Fit Checked Casual Shirt","Navy blue and brown checked casual shirt, has a spread collar, button placket, 1 pocket, long sleeves with roll-up tab, curved hem",
                "Shirts","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1979305/2018/4/3/11522753438599-HIGHLANDER-Men-Navy-Slim-Fit-Checked-Casual-Shirt-221522753438432-1.jpg",
                3,789,new String[]{"Burgundry","Grey","Blue","White"},sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1979305/2018/4/3/11522753438538-HIGHLANDER-Men-Navy-Slim-Fit-Checked-Casual-Shirt-221522753438432-5.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1979305/2018/4/3/11522753438553-HIGHLANDER-Men-Navy-Slim-Fit-Checked-Casual-Shirt-221522753438432-4.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1979305/2018/4/3/11522753438567-HIGHLANDER-Men-Navy-Slim-Fit-Checked-Casual-Shirt-221522753438432-3.jpg"}));

        products.add(new Product(14,"Navy Slim Fit Formal Shirt","Navy blue formal shirt, has a spread collar, long sleeves, a full button placket, a patch pocket, a curved hem",
                "Shirts","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1304671/2016/4/14/11460624898615-Hancock-Men-Shirts-8481460624898035-1.jpg",
                6,824,
                new String[]{"Blue","Grey","Black"},
                sizes,
                new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1304671/2016/4/14/11460624898615-Hancock-Men-Shirts-8481460624898035-1.jpg",
                        "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1304671/2016/4/14/11460624898231-Hancock-Men-Shirts-8481460624898035-5.jpg"}));

        products.add(new Product(15,"INVICTUS","Blue checked mid-rise smart casual trousers, has a hook and bar closure, 4 pockets",
                "Jeans","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2029950/2017/9/19/11505820114847-INVICTUS-Men-Trousers-7511505820114562-1.jpg",
                5,1654,
                new String[]{"LightBlue","Black","White"},
                sizes, new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2029950/2017/9/19/11505820114702-INVICTUS-Men-Trousers-7511505820114562-4.jpg"
                ,"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/2029950/2017/9/19/11505820114791-INVICTUS-Men-Trousers-7511505820114562-2.jpg"}));

        products.add(new Product(16,"Louis Philippe Sport","Khaki brown solid low-rise regular trousers, has a button closure, four pockets",
                "Jeans","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9751117/2019/5/30/9c62afc1-3d6e-4655-8975-78cbb1720bf21559212135413-Louis-Philippe-Sport-Men-Khaki-Super-Slim-Fit-Solid-Chinos-6-1.jpg",
                2,1355,
                new String[]{"Khaki"},sizes,new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9751117/2019/5/30/39b4e14f-b757-45a0-9ccf-3d734e855fe61559212135341-Louis-Philippe-Sport-Men-Khaki-Super-Slim-Fit-Solid-Chinos-6-4.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9751117/2019/5/30/751441a7-a898-4475-a337-9f04c03bf19e1559212135388-Louis-Philippe-Sport-Men-Khaki-Super-Slim-Fit-Solid-Chinos-6-2.jpg"}));

        products.add(new Product(17,"INVICTUS","Green checked casual blazer, has a notched lapel, single-breasted with double button closure,padded shoulders, long sleeves with elbow patches, 5 pockets, a double vented back hem",
                "Blazers","Men","https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826061/2018/1/4/11515060889914-INVICTUS-Green-Checked-Slim-Fit-Single-Breasted-Blazer-5571515060889823-2.jpg",
                2,3899,new String[]{"Green"},sizes,new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826061/2018/1/4/11515060889914-INVICTUS-Green-Checked-Slim-Fit-Single-Breasted-Blazer-5571515060889823-2.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826061/2018/1/4/11515060889934-INVICTUS-Green-Checked-Slim-Fit-Single-Breasted-Blazer-5571515060889823-1.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826061/2018/1/4/11515060889847-INVICTUS-Green-Checked-Slim-Fit-Single-Breasted-Blazer-5571515060889823-6.jpg"}));

        products.add(new Product(18,"Blue Slim Fit Single-Breasted Formal Blazer","Blue formal blazer, has a notched lapel, single-breasted with double button closures, long sleeves, three pockets, vented back hem, three pockets built into the lining",
                "Blazers","Men","https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826056/2017/9/7/11504796397167-INVICTUS-Men-Blazers-1201504796396974-2.jpg",
                3,4589, new String[]{"LightBlue","White"},
                sizes,new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826056/2017/9/7/11504796397167-INVICTUS-Men-Blazers-1201504796396974-2.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826056/2017/9/7/11504796397113-INVICTUS-Men-Blazers-1201504796396974-4.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/1826056/2017/9/7/11504796397075-INVICTUS-Men-Blazers-1201504796396974-5.jpg"}));

        products.add(new Product(19,"Men Navy Blue Self Design Slim Fit Tuxedo Suit","Navy Blue self-design tuxedo, has a shawl collar, single breasted with double button closures, long sleeves, one welt pocket and two flap pockets, and a double-vented back hem\n" +
                "Navy blue self-design mid-rise trousers, has a zip fly with a button and a hook-and-bar closure, four pockets, a waistband with belt loops\n" +
                "Navy Blue waistcoat with two pockets",
                "Suits","Men","https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9447203/2019/5/9/9d26f530-180f-42ed-9c56-ca6a51666fa71557404085556-Louis-Philippe-Men-Navy-Blue-Self-Design-Slim-Fit-Tuxedo-Sui-1.jpg",
                3,9799,new String[]{"Navy Blue"},sizes, new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9447203/2019/5/9/9d26f530-180f-42ed-9c56-ca6a51666fa71557404085556-Louis-Philippe-Men-Navy-Blue-Self-Design-Slim-Fit-Tuxedo-Sui-1.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9447203/2019/5/9/d22578b4-370d-49d1-aab3-80a905d149571557404085493-Louis-Philippe-Men-Navy-Blue-Self-Design-Slim-Fit-Tuxedo-Sui-4.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/9447203/2019/5/9/15d8dea9-1853-4eb0-a320-0079fcd70b0e1557404085534-Louis-Philippe-Men-Navy-Blue-Self-Design-Slim-Fit-Tuxedo-Sui-2.jpg"}));

        products.add(new Product(20,"Men Beige & Grey Checked Slim-Fit Single-Breasted Three-Piece Suit",
                "Beige and grey checked three-piece suit\n" +
                        "Beige checked slim-fit blazer, has a peaked lapel, single-breasted with double button closures, long sleeves, three pockets, and a double-vented back hem\n" +
                        "Beige checked mid-rise trousers, has four pockets, a zip fly with hook-and-bar and a button closure, a waistband with belt loops.\n" +
                        "Grey self-design waistcoat, has a V-neck, button closures on the front, sleeveless.\n" +
                        "Comes with a tie, a pocket square and a brooch",
                "Suits","Men",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/10476186/2019/8/17/741ff4a7-1977-41be-a563-5894ae6b91661566044239228-LUXURAZI-Men-Beige--Grey-Checked-Slim-Fit-Single-Breasted-Th-1.jpg",
                2,8788,new String[]{"Beige","Grey"},sizes, new String[]{"https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/10476186/2019/8/17/741ff4a7-1977-41be-a563-5894ae6b91661566044239228-LUXURAZI-Men-Beige--Grey-Checked-Slim-Fit-Single-Breasted-Th-1.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/10476186/2019/8/17/c896371f-95d2-4465-9920-7d73b1dd1ee41566044239180-LUXURAZI-Men-Beige--Grey-Checked-Slim-Fit-Single-Breasted-Th-3.jpg",
                "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/10476186/2019/8/17/abe59e5f-ccda-4eaa-adfe-04527c4d4ac31566044239204-LUXURAZI-Men-Beige--Grey-Checked-Slim-Fit-Single-Breasted-Th-2.jpg"}));

    }

}
