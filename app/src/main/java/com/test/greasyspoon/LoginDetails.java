package com.test.greasyspoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginDetails extends AppCompatActivity {

    TextView Email,Psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);

        Email=(TextView) findViewById(R.id.login_Email);
        Psw=(TextView) findViewById(R.id.login_Psw);

        Intent intent = getIntent();
        String psw = intent.getStringExtra("psw");
        String email = intent.getStringExtra("email");

        Email.setText("Email: " + email);
        Psw.setText("pasword:"+psw);
    }
}
