package com.test.greasyspoon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetails extends AppCompatActivity {

    ImageView home,loc,feed,setting;
    SQLiteDatabase db;
    Sqlitehelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        final SessionManager session=new SessionManager(ViewDetails.this,"");
        String semail=session.getsessiontitle();


        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLoginhome()) {
                    Intent home = new Intent(getApplicationContext(), HomeActivity_User.class);
                    startActivity(home);
                }
                else{
                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);

                }
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed=new Intent(getApplicationContext(),FeedActivity.class);
                startActivity(feed);
            }
        });

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myacc=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(myacc);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting=new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(setting);
            }
        });
        ImageView imgback=(ImageView)findViewById(R.id.Imgback) ;
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });
        ImageView imgHeader=(ImageView)findViewById(R.id.Img4) ;

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(ViewDetails.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(ViewDetails.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(ViewDetails.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {


                            Toast.makeText(ViewDetails.this,"Your Bookmark", Toast.LENGTH_LONG).show();
                            Intent b1=new Intent(getApplicationContext(),show_bookmark.class);
                            startActivity(b1);

                        }
                        else if(item.getItemId()== R.id.sp)
                        {


                            Intent share = new Intent(android.content.Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_SUBJECT, "Download GreasySpoon app.....");
                            share.putExtra(Intent.EXTRA_TEXT, "Download GreasySpoon app from playstore...");
                            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(Intent.createChooser(share, "Share App!"));
                        }
                        else {

                            Toast.makeText(ViewDetails.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });

        Intent i=getIntent();
        final String s=i.getStringExtra("Title");


        helper = new Sqlitehelper(ViewDetails.this);
        db = helper.getWritableDatabase();

        TextView tv1=(TextView)findViewById(R.id.tv);
        tv1.setText(s);

      final String address=tv1.getText().toString();



        final ImageView imgbk=(ImageView)findViewById(R.id.ImgBookmark);

        imgbk.setVisibility(View.VISIBLE);
        imgbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager session=new SessionManager(ViewDetails.this,"");
                String semail=session.getsessiontitle();


                if(session.checkLoginhome())
                {

                        // Toast.makeText(ViewDetails.this, "Your are login as :"+semail+"address is :"+address, Toast.LENGTH_LONG).show();
                        String query = "Insert into bookmark values('" + semail + "','" + address + "')";
                    db.execSQL(query);
                    // Toast.makeText(ViewDetails.this, "query "+query, Toast.LENGTH_LONG).show();
                    Toast.makeText(ViewDetails.this, "Succesfully Added As Bookmark", Toast.LENGTH_LONG).show();

                             }
                else
                {
                    Intent i=new Intent(ViewDetails.this,Login.class);
                    startActivity(i);
                    Toast.makeText(ViewDetails.this, "Go To Login Page", Toast.LENGTH_LONG).show();

                }






            }
        });



    }
}
