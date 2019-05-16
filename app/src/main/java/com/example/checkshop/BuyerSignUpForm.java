package com.example.checkshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class BuyerSignUpForm extends AppCompatActivity {

    private EditText Email, Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_sign_up_form);


        Email = findViewById(R.id.email);
        Password = findViewById(R.id.uuu);

        mAuth = FirebaseAuth.getInstance();
        Button SignUp;

        SignUp = findViewById(R.id.SignUp1);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }
        private void startSignIn()
        {
            String mEmail = Email.getText().toString();
            String mPassword = Password.getText().toString();

            if(TextUtils.isEmpty(mEmail)  || TextUtils.isEmpty(mPassword))
            {
                Toast.makeText(BuyerSignUpForm.this,"Filleds are Empty",Toast.LENGTH_LONG).show();

            } else {
                mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                        .addOnCompleteListener(BuyerSignUpForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(BuyerSignUpForm.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(BuyerSignUpForm.this,"SignUp not successful",Toast.LENGTH_LONG).show();

                                } else {
                                    Intent pp = new Intent (BuyerSignUpForm.this, BuyerPage.class);
                                    startActivity(pp);
                                }
                            }
                        });

            }}

    }
