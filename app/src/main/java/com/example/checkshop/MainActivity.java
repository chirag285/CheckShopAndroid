package com.example.checkshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.checkshop.BuyerOrSeller.flag;

public class MainActivity extends AppCompatActivity {

    private Firebase mref;
    private TextView one;
    private FirebaseAuth mAuth;
    private EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Login, SignUp;

        username = findViewById(R.id.email);
        password = findViewById(R.id.editText3);
        Login = findViewById(R.id.buttonbs);
        SignUp = findViewById(R.id.button2);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oo = new Intent(MainActivity.this, BuyerOrSeller.class);
                startActivity(oo);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }
        private void startSignIn()
        {
            String mEmail = username.getText().toString();
            String mPassword = password.getText().toString();

            if(TextUtils.isEmpty(mEmail)  || TextUtils.isEmpty(mPassword))
            {
                Toast.makeText(MainActivity.this,"Fields are Empty",Toast.LENGTH_LONG).show();

            } else
            {
                mAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.e("innnn",flag);
                            /*if(flag.matches("Buyer"))
                            {
                                Intent oo = new Intent(MainActivity.this, BuyerHomePage.class);
                                startActivity(oo);
                            }
                            else if(flag.matches("Seller"))
                            {*/
                                /*Intent oo = new Intent(MainActivity.this, SellerHomePage.class);
                                startActivity(oo);*/
                            Intent oo = new Intent(MainActivity.this, BuyerPage.class);
                                startActivity(oo);
                            //}
                            Toast.makeText(MainActivity.this,"Sign In Successfull",Toast.LENGTH_LONG).show();
                        } else
                        {
                            Toast.makeText(MainActivity.this,"Information Is Incorrect",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        //Test Commit Two
        //Test Commit Three
    }

