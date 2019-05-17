package com.example.checkshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import utils.Constants;

import static com.example.checkshop.AddProduct.mypreference;


public class BuyerPage extends AppCompatActivity {
    private Toolbar mToolbar;
    public static ArrayList<String> listdatausers=new ArrayList<>();
    FirebaseDatabase mDatabase;
    DatabaseReference mDb;
    DatabaseReference reference;
    Query query;
    ArrayList<RowItemCustomer> list;
    GridView lv_languages;
    public static int selectedPosition = 0;
    private FirebaseAuth mAuth;
    SparseBooleanArray sparseBooleanArray;
    SharedPreferences sharedpreferences;
    private ProgressDialog mProgressDialog;
    FloatingActionButton fab;
    String ProductName, decscript, dateget, Cost, Availability, Company, Name, indexs;
    String ContactPhoto="null";
    CustomerListProductAdapter adapter;
    private TextView mCounter;
    private int count = 0;
    TextView textCartItemCount;
    int mCartItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerlistactivity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();
        mDb = mDatabase.getReference();
        reference = mDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();

        String userKey = user.getUid();

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);



        list = new ArrayList<RowItemCustomer>();

        lv_languages = findViewById(R.id.lv_languages);
      lv_languages.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       // lv_languages.setItemsCanFocus(false);

        String myUrl = "https:/check-shop-285.firebaseio.com/Product.json";

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

                    JSONArray jsonObject = new JSONArray(result);
                    int lenArray = jsonObject.length();
                    for (int jIndex = 1; jIndex < lenArray; jIndex++) {
                        RowItemCustomer model = new RowItemCustomer();
                        JSONObject innerObject = jsonObject.getJSONObject(jIndex);


                            if (!innerObject.getString("Product Name").matches(" ")) {
                                ProductName = innerObject.getString("Product Name");
                                Log.e("Product Name", "" + ProductName);
                            }
                            if (!innerObject.getString("Description").matches(" ")) {
                                decscript = innerObject.getString("Description");
                                Log.e("Datefromattendence", "" + decscript);
                            }

                            if (!innerObject.getString("Date").matches(" ")) {
                                dateget = innerObject.getString("Date");
                                Log.e("Dateget", dateget);
                            }

                            if (!innerObject.getString("Cost").matches(" ")) {
                                Cost = innerObject.getString("Cost");
                                Log.e("Cost", Cost);
                            }

                            if (!innerObject.getString("ImageUrl").matches(" ")) {
                                ContactPhoto = innerObject.getString("ImageUrl");
                                Log.e("ContactPhoto", ContactPhoto);
                            }


                            if (!innerObject.getString("Index").matches(" ")) {
                                indexs = innerObject.getString("Index");
                                Log.e("indexs", indexs);
                            }



                            model.setTitle(ProductName);
                            model.setDesc(decscript);
                            model.setRes_DateFrom(dateget);
                            model.setCost(Cost);
                            model.setContactPhoto(ContactPhoto);
                            model.setindex(indexs);
                            list.add(model);

                    }


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
           Constants.vlues = list.size();
            Log.e("sizelist", "" + Constants.vlues);
            if (list.size() == 0) {
                Constants.vlues = 1;
            } else {
                Constants.vlues = list.size();
            }
            if (list.size() >= 1) {
                adapter = new CustomerListProductAdapter(BuyerPage.this, R.layout.listitemcustomer, list);
                lv_languages.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                /*lv_languages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RowItemCustomer mod=list.get(position);
                        itemcost=mod.getCost();
                        Constants.itemimage=mod.getContactPhoto();
                        Intent i=new Intent(BuyerPage.this,ItemDetailActivity.class);
                        startActivity(i);
                        finish();
                    }
                });*/
                lv_languages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        // TODO Auto-generated method stub
                        int i=0;
                        TextView yourview=lv_languages.getChildAt(pos).findViewById(R.id.indication);
                        RowItemCustomer mod=list.get(pos);
                        String indx=mod.getIndex();

                        if (lv_languages.isItemChecked(pos))
                        {
                            lv_languages.setItemChecked(pos,false);
                            yourview.setBackgroundResource(android.R.color.holo_red_dark);
                            mCartItemCount--;
                            setupBadge();

                            listdatausers.remove(indx);
                                Log.e("list",""+listdatausers);

                            }
                        else
                            {
                                yourview.setBackgroundResource(android.R.color.holo_green_dark);
                                if (textCartItemCount != null) {
                                    mCartItemCount++;
                                    setupBadge();
                                }
                                lv_languages.setItemChecked(pos,true);
                                listdatausers.add(indx);
                                    Log.e("list", "" + listdatausers);

                        }
                        Log.v("tagg","long clicked pos: " + pos);
                        //lvMain.setSelection();
                        HashSet hs = new HashSet();
                        hs.addAll(listdatausers);
                        listdatausers.clear();
                        listdatausers.addAll(hs);
                        Log.e("listssss", "" + listdatausers);
                        return false;
                    }
                });
                /*lv_languages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // TODO Auto-generated method stub
                        if (textCartItemCount != null) {
                            mCartItemCount++;
                            setupBadge();
                        }
                    }
                });*/


              /* lv_languages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        RowItemCustomer mod=list.get(position);
                        Constants.Product_Name=mod.getTitle();
                        Constants.pos=position+1;
                        Constants.vlues=mod.getIndex();
                        Constants.Description=mod.getDesc();
                        Constants.Cost=mod.getCost();
                        Constants.Availability=mod.getAvailability();
                        Constants.img=mod.getContactPhoto();


            }*/
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversation_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        //setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_cart: {
              Intent i=new Intent(BuyerPage.this,ShoppingCart.class);
              startActivity(i);
              finish();
                return true;
            }
           /* case R.id.action_sub: {
              Intent i=new Intent(BuyerPage.this,Subcription.class);
              startActivity(i);
              finish();
                return true;
            }*/
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}




