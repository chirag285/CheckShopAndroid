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

public class Selller_SignUp_Form extends AppCompatActivity {
    EditText semail, spassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selller__sign_up__form);



        semail = findViewById(R.id.uuu);
        spassword = findViewById(R.id.pppp);
        mAuth = FirebaseAuth.getInstance();

        Button Ssignup;
        Ssignup = findViewById(R.id.button34);
        Ssignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }
    private void startSignIn()
    {
        String msEmail = semail.getText().toString();
        String msPassword = spassword.getText().toString();

        if(TextUtils.isEmpty(msEmail)  || TextUtils.isEmpty(msPassword))
        {
            Toast.makeText(Selller_SignUp_Form.this,"Filleds are Empty",Toast.LENGTH_LONG).show();

        } else {
            mAuth.createUserWithEmailAndPassword(msEmail, msPassword)
                    .addOnCompleteListener(Selller_SignUp_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Selller_SignUp_Form.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                startActivity(new Intent(Selller_SignUp_Form.this, MainActivity.class));
                                finish();
                            } else {

                                Intent pp = new Intent (Selller_SignUp_Form.this, SellerHomePage.class);
                                startActivity(pp);
                            }
                        }
                    });

        }}
}

