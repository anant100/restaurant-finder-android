package com.test.greasyspoon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class view_ROprofile extends AppCompatActivity {

    TextView Name,Email,Contact,wlmsg;
    Button ChangePsw;

    SQLiteDatabase db;
    Sqlitehelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__roprofile);


        Name = (TextView) findViewById(R.id.tvName);
        Email = (TextView) findViewById(R.id.tvEmail);
        Contact = (TextView) findViewById(R.id.tvContact);
        wlmsg = (TextView) findViewById(R.id.tvWlcmP);
        ChangePsw = (Button) findViewById(R.id.btnChangePsw);

        helper = new Sqlitehelper(view_ROprofile.this);
        db = helper.getReadableDatabase();

        ImageView imgback=(ImageView)findViewById(R.id.Imgback) ;
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView imgHeader = (ImageView) findViewById(R.id.Imghome_header);

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(view_ROprofile.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });


        SessionManager session = new SessionManager(view_ROprofile.this, "");

        final String emailid = session.getsessiontitle();


        try {


            String squery = "select * from reg_ROwner where email='" + emailid + "'";
            Cursor c = db.rawQuery(squery, null);
            if (c != null) {
                c.moveToFirst();
                String s_name = c.getString(0);
                String s_mail = c.getString(1);
                String s_psw = c.getString(2);
                String s_contact = c.getString(3);

                wlmsg.setText("Welcome  " + s_name);
                Name.setText(s_name);
                Email.setText(s_mail);
                Contact.setText(s_contact);

                //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            Toast.makeText(view_ROprofile.this, "" + e, Toast.LENGTH_LONG).show();

        }

        Toast.makeText(view_ROprofile.this, "" + session.getsessiontitle(), Toast.LENGTH_LONG).show();


        //
        ChangePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String squery = "select * from reg_ROwner where email='" + emailid + "'";
                    Cursor c = db.rawQuery(squery, null);
                    if (c != null) {
                        c.moveToFirst();
                        String s_name = c.getString(0);
                        String s_mail = c.getString(1);
                        String s_psw = c.getString(2);
                        String s_contact = c.getString(3);
                        Intent i1 = new Intent(view_ROprofile.this, ROPasswordChange.class);

                        SessionManager session = new SessionManager(view_ROprofile.this, "");
                        String emailid = session.getsessiontitle();
                        startActivity(i1);
                    }
                } catch (Exception e) {
                    Toast.makeText(view_ROprofile.this, "" + e, Toast.LENGTH_LONG).show();


                }
            }
        });

    }
}
