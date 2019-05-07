package com.example.checkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyerOrSeller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_or_seller);

        Button buyer,seller;
        buyer = findViewById(R.id.button);
        seller = findViewById(R.id.button2);

        final Intent BuyerSignUp = new Intent(this, BuyerSignUpForm.class);

        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BuyerSignUp);
            }
        });
    }
}
