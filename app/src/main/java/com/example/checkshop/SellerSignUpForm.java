package com.example.checkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SellerSignUpForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_sign_up_form);

        EditText Emails, Passwords , ShopAdd;
        Button SignUp;

        Emails = findViewById(R.id.editText);
        Passwords = findViewById(R.id.editText1);
        ShopAdd = findViewById(R.id.editText2);

        SignUp = findViewById(R.id.button3);


    }
}
