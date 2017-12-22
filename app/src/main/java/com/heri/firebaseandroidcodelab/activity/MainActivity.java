//Android shared preference
package com.heri.firebaseandroidcodelab.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heri.firebaseandroidcodelab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    Button b1;

    public static final String MyPref = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedPreferences;
    @BindView(R.id.btnlain)
    Button btnlain;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ed1 = findViewById(R.id.edit_Text);
        ed2 = findViewById(R.id.edit_Text2);
        ed3 = findViewById(R.id.edit_Text3);


        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        init();


        getBtnlain();
    }

    public void init() {

        b1 = findViewById(R.id.Buttonsave);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = ed1.getText().toString();
                String ph = ed2.getText().toString();
                String e = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(MainActivity.this, "Thanks", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void ButtonTestFirebase(View view) {
        Intent i = new Intent(MainActivity.this, JournalListActivity.class);
        startActivity(i);
    }

//    public void setBtnlain(Button btnlain) {
//        this.btnlain = btnlain;
//        btnlain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(MainActivity.this, LoginGoogleFurebaseActivity.class);
//                startActivity(i);
//            }
//        });
//    }

    public Button getBtnlain() {
        btnlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        return  btnlain;
    }
}
