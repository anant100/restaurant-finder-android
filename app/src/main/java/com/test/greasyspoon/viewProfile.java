package com.test.greasyspoon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class viewProfile extends AppCompatActivity {
    TextView Name,Email,Contact,wlmsg;
    Button ChangePsw;

    SQLiteDatabase db;
    Sqlitehelper helper;

    ImageView home,loc,feed,setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Name = (TextView) findViewById(R.id.tvName);
        Email = (TextView) findViewById(R.id.tvEmail);
        Contact = (TextView) findViewById(R.id.tvContact);
        wlmsg = (TextView) findViewById(R.id.tvWlcmP);
        ChangePsw = (Button) findViewById(R.id.btnChangePsw);

        helper = new Sqlitehelper(viewProfile.this);
        db = helper.getReadableDatabase();

        home = (ImageView) findViewById(R.id.ImgHome);
        loc = (ImageView) findViewById(R.id.ImgLocation);
        feed = (ImageView) findViewById(R.id.ImgFeed);
        setting = (ImageView) findViewById(R.id.ImgSetting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity_User.class);
                startActivity(home);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed = new Intent(getApplicationContext(), FeedActivity.class);
                startActivity(feed);
            }
        });

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myacc = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(myacc);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(setting);
            }
        });
        ImageView imgback = (ImageView) findViewById(R.id.Imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView imgHeader = (ImageView) findViewById(R.id.Img4);

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(viewProfile.this, v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        if (item.getItemId() == R.id.fb) {

                            Toast.makeText(viewProfile.this, "Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1 = new Intent(getApplicationContext(), FeedActivity.class);
                            startActivity(feed1);
                        } else if (item.getItemId() == R.id.rt1) {

                            Toast.makeText(viewProfile.this, "Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1 = new Intent(getApplicationContext(), RatingActivity.class);
                            startActivity(r1);

                        } else if (item.getItemId() == R.id.bk1) {

                            Toast.makeText(viewProfile.this, "Your Bookmark", Toast.LENGTH_LONG).show();
                            Intent b1 = new Intent(getApplicationContext(), show_bookmark.class);
                            startActivity(b1);

                        } else if (item.getItemId() == R.id.sp) {


                            Intent share = new Intent(android.content.Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_SUBJECT, "Download GreasySpoon app.....");
                            share.putExtra(Intent.EXTRA_TEXT, "Download GreasySpoon app from playstore...");
                            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(Intent.createChooser(share, "Share App!"));
                        } else {

                            Toast.makeText(viewProfile.this, "default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });


        SessionManager session = new SessionManager(viewProfile.this, "");

        final String emailid = session.getsessiontitle();


            try {


                String squery = "select * from register where email='" + emailid + "'";
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
                Toast.makeText(viewProfile.this, "" + e, Toast.LENGTH_LONG).show();

            }

            Toast.makeText(viewProfile.this, "" + session.getsessiontitle(), Toast.LENGTH_LONG).show();


            //
            ChangePsw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        String squery = "select * from register where email='" + emailid + "'";
                        Cursor c = db.rawQuery(squery, null);
                        if (c != null) {
                            c.moveToFirst();
                            String s_name = c.getString(0);
                            String s_mail = c.getString(1);
                            String s_psw = c.getString(2);
                            String s_contact = c.getString(3);
                            Intent i1 = new Intent(viewProfile.this, password_change.class);

                            SessionManager session = new SessionManager(viewProfile.this, "");
                            String emailid = session.getsessiontitle();
                            startActivity(i1);
                        }
                    } catch (Exception e) {
                        Toast.makeText(viewProfile.this, "" + e, Toast.LENGTH_LONG).show();


                    }
                }
            });


    }


}
