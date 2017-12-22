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
import com.google.firebase.database.FirebaseDatabase;
import com.heri.firebaseandroidcodelab.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

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
    Button signup;

    FirebaseAuth auth;
    FirebaseDatabase fbdab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        fbdab = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        btnsignupmethod();
    }
    void btnsignupmethod(){
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailvar= email.getText().toString().trim();
                String passvar = password.getText().toString().trim();
                if (TextUtils.isEmpty(emailvar)){
                    Toast.makeText(getApplicationContext(),"Input email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(passvar)){
                    Toast.makeText(getApplicationContext(),"Input password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (passvar.length() < 6){
                    Toast.makeText(getApplicationContext(),"Password Kurang", Toast.LENGTH_LONG).show();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(emailvar, passvar).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUpActivity.this, "CreateUserWithEmail"+task.isSuccessful(), Toast.LENGTH_LONG).show();
                        progressbar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,"Auth fail", Toast.LENGTH_LONG).show();
                        }else {
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });


            }
        });
    }
}
