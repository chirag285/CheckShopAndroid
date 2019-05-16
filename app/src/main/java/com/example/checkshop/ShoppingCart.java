package com.example.checkshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import utils.Constants;

import static com.example.checkshop.BuyerPage.listdatausers;


public class ShoppingCart extends AppCompatActivity {
    String ProductName, decscript, dateget, Cost, Availability, ContactPhoto, Company, Name, indexs,leftdayini="",myFormat;
    FirebaseDatabase mDatabase;
    DatabaseReference mDb;
    DatabaseReference reference;
    SimpleDateFormat sdf;
    Calendar myCalendar = Calendar.getInstance();
    TextView priceView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    ArrayList<RowItemCart> list;
    int totalAmount = 0;
    CartListProductAdapter adapter;
    int index=1;
    int i=0;
    ListView listview;
    ArrayList<RowItemCustomer> items;// =
    Button subcribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_window);

        priceView = (TextView) findViewById(R.id.totalPriceCheckout);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDb = mDatabase.getReference();
        reference = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        String userKey = user.getUid();
        list = new ArrayList<RowItemCart>();
        myFormat = "dd/MM/yyyy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat);
        leftdayini=sdf.format(myCalendar.getTime());
        subcribe=findViewById(R.id.subcribe);

                for(i=0;i<listdatausers.size();i++) {
                    index = Integer.parseInt(listdatausers.get(i));
                    Log.e("index",""+index);
                    String myUrl = "https://check-shop-285.firebaseio.com/Product.json";
                    listview = findViewById(R.id.shoppingCartList);
                    // Request a string response from the provided URL.
                    String result;
                    //Instantiate new instance of our class
                    HttpGetRequest getRequest = new HttpGetRequest();
                    //Perform the doInBackground method, passing in our url
                    try {
                        result = getRequest.execute(myUrl).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }



        subcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(i=0;i<listdatausers.size();i++) {
                    index = Integer.parseInt(listdatausers.get(i));
                    Log.e("index",""+index);
                    String myUrl = "https://check-shop-285.firebaseio.com/Product.json";
                    listview = findViewById(R.id.shoppingCartList);
                    // Request a string response from the provided URL.
                    String result;
                    //Instantiate new instance of our class
                    HttpGetRequest1 getRequest = new HttpGetRequest1();
                    //Perform the doInBackground method, passing in our url
                    try {
                        result = getRequest.execute(myUrl).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.e("result", result);
                try {
                    //al.clear();
                    int itemPrice = -1, quantity = 0;
                    JSONArray jsonObject = new JSONArray(result);
                    int lenArray = jsonObject.length();
                    //for (int jIndex = 1; jIndex < lenArray; jIndex++) {
                        RowItemCart model = new RowItemCart();
                        JSONObject innerObject = jsonObject.getJSONObject(index);
                        /*if (!innerObject.getString("Company").matches(" ")) {
                            Company = innerObject.getString("Company");
                            Log.e("Company", Company);
                        }*/
                        /*if (Constants.CompanyName.matches(Company)) {*/
                            if (!innerObject.getString("Product Name").matches(" ")) {
                                ProductName = innerObject.getString("Product Name");
                                Log.e("Product Name", "" + ProductName);
                            }


                            if (!innerObject.getString("Cost").matches(" ")) {
                                Cost = innerObject.getString("Cost");
                                Log.e("Cost", Cost);


                                    itemPrice = Integer.valueOf((Cost).toString());

                            }

                            if (!innerObject.getString("Index").matches(" ")) {
                                indexs = innerObject.getString("Index");
                                Log.e("indexs", indexs);
                            }
                            if (!innerObject.getString("ImageUrl").matches(" ")) {
                                ContactPhoto = innerObject.getString("ImageUrl");
                                Log.e("ContactPhoto", ContactPhoto);
                            }
                            totalAmount += itemPrice;
                            model.setTitle(ProductName);
                            model.setCost(Cost);
                            model.setindex(indexs);
                            model.setContactPhoto(ContactPhoto);
                            list.add(model);
                           // Constants.listdata.add(model);

                   /* mDb.child("Subscribe").child(String.valueOf(i+1)).child("Customer Name").setValue(Name);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("Date").setValue(leftdayini);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("Merchant Id").setValue(Company);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("Cost").setValue(Cost);*/



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (list.size() >= 1) {
                adapter = new CartListProductAdapter(ShoppingCart.this, R.layout.cart_item, list);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                priceView.setText("Rs"+" "+totalAmount);


            }
        }
    }public class HttpGetRequest1 extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.e("result", result);
                try {
                    //al.clear();
                    int itemPrice = -1, quantity = 0;
                    JSONArray jsonObject = new JSONArray(result);
                    int lenArray = jsonObject.length();
                    //for (int jIndex = 1; jIndex < lenArray; jIndex++) {
                        RowItemCart model = new RowItemCart();
                        JSONObject innerObject = jsonObject.getJSONObject(index);
                         /*if (Constants.CompanyName.matches(Company)) {*/
                            if (!innerObject.getString("Product Name").matches(" ")) {
                                ProductName = innerObject.getString("Product Name");
                                Log.e("Product Name", "" + ProductName);
                            }


                            if (!innerObject.getString("Cost").matches(" ")) {
                                Cost = innerObject.getString("Cost");
                                Log.e("Cost", Cost);


                                    itemPrice = Integer.valueOf((Cost).toString());

                            }

                            if (!innerObject.getString("Index").matches(" ")) {
                                indexs = innerObject.getString("Index");
                                Log.e("indexs", indexs);
                            }
                            if (!innerObject.getString("ImageUrl").matches(" ")) {
                                ContactPhoto = innerObject.getString("ImageUrl");
                                Log.e("ContactPhoto", ContactPhoto);
                            }
                           // Constants.listdata.add(model);

                   // mDb.child("Subscribe").child(String.valueOf(i+1)).child("Customer Name").setValue(Name);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("Date").setValue(leftdayini);
                   // mDb.child("Subscribe").child(String.valueOf(i+1)).child("Merchant Id").setValue(Company);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("Cost").setValue(Cost);
                    mDb.child("Subscribe").child(String.valueOf(i+1)).child("ProductName").setValue(ProductName);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(ShoppingCart.this,"Subscribed Successfully",Toast.LENGTH_LONG).show();
            finish();


        }
    }


}