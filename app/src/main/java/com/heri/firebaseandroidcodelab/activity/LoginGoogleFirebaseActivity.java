package com.heri.firebaseandroidcodelab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.heri.firebaseandroidcodelab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginGoogleFirebaseActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.btnresetpass)
    Button btnresetpass;
    @BindView(R.id.btnsignup)
    Button btnsignup;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_google_firebase);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(LoginGoogleFirebaseActivity.this, MainActivity.class));
            finish();
        }
        firebaseAuth = FirebaseAuth.getInstance();
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginGoogleFirebaseActivity.this, ResetPasswordActivity.class));
            }
        });

//
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(emails)){
                    Toast.makeText(getApplicationContext(), "Input Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Input Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass.length() < 6){
                    Toast.makeText(getApplicationContext(), "Password Pendek", Toast.LENGTH_LONG).show();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(emails, pass).
                        addOnCompleteListener(LoginGoogleFirebaseActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(LoginGoogleFirebaseActivity.this, "Create berhasil"+task.isSuccessful(),Toast.LENGTH_LONG).show();
                        progressbar.setVisibility(View.GONE);

                        if (!task.isSuccessful()){
                            Toast.makeText(LoginGoogleFirebaseActivity.this,"Auth failed"+task.getException(), Toast.LENGTH_LONG).show();
                        }else {
                            startActivity(new Intent(LoginGoogleFirebaseActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }

        });

    }
}
