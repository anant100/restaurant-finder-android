package com.test.greasyspoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class advanceSearch extends AppCompatActivity {

    ImageView loc,home,feed,setting;
    Button s_Bycity,s_Bybudget,s_Bycuisine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

        s_Bycity=(Button)findViewById(R.id.by_city);
        s_Bybudget=(Button)findViewById(R.id.by_Budget);
        s_Bycuisine=(Button)findViewById(R.id.by_Cuisine);

       s_Bycity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent c1=new Intent(getApplicationContext(),SearchBycity.class);
                startActivity(c1);
            }
        });
        s_Bybudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b1=new Intent(getApplicationContext(),searchBybudget.class);
                startActivity(b1);

            }
        });
        s_Bycuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1=new Intent(getApplicationContext(),searchBycuisine.class);
                startActivity(s1);

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

                PopupMenu popup = new PopupMenu(advanceSearch.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(advanceSearch.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(advanceSearch.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            Toast.makeText(advanceSearch.this,"Your Bookmark", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(advanceSearch.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(loc);
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(home);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed=new Intent(getApplicationContext(),FeedActivity.class);
                startActivity(feed);
            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting=new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(setting);
            }
        });

    }
}
