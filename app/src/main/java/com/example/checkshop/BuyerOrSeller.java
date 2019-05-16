package com.example.checkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyerOrSeller extends AppCompatActivity {
    Button buyer,seller;
    public static String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_or_seller);


        buyer = findViewById(R.id.button);
        seller = findViewById(R.id.button2);


        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="Buyer";
                Intent BuyerSignUp = new Intent(BuyerOrSeller.this, BuyerSignUpForm.class);
                startActivity(BuyerSignUp);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="Seller";
                Intent yy = new Intent(BuyerOrSeller.this, Selller_SignUp_Form.class);
                startActivity(yy);
            }
        });
    }
}
