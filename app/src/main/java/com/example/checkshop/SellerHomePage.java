package com.example.checkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellerHomePage extends AppCompatActivity {
    Button aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home_page);


        aa = findViewById(R.id.button6);
        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oo = new Intent(SellerHomePage.this, AddProduct.class);
                startActivity(oo);
            }
        });
    }
}
