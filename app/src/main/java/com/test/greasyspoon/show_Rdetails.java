package com.test.greasyspoon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class show_Rdetails extends AppCompatActivity {

    SQLiteDatabase db;
    Sqlitehelper helper;
    ListView listView ;
    TextView rname,radd,rcity,rcuisine,rophr,rcost,rperson,rcontact;
    Button givereview;

    ImageView home,loc,feed,setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__rdetails);
        SessionManager session=new SessionManager(show_Rdetails.this,"");

        rname=(TextView)findViewById(R.id.viewRname);
        radd=(TextView)findViewById(R.id.viewRadd);
       rcity=(TextView)findViewById(R.id.viewRcity);
        rcuisine=(TextView)findViewById(R.id.viewRcuisine);
       rophr=(TextView)findViewById(R.id.viewRophr);
        rcost=(TextView)findViewById(R.id.viewRavgcost);
        rperson=(TextView)findViewById(R.id.viewRperson);
        rcontact=(TextView)findViewById(R.id.viewRcontact);

        givereview=(Button)findViewById(R.id.GiveReview);

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);


        Intent i=getIntent();
        final String name=i.getStringExtra("Restaurant Name");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(),HomeActivity_User.class);
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

                PopupMenu popup = new PopupMenu(show_Rdetails.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(show_Rdetails.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(show_Rdetails.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            Toast.makeText(show_Rdetails.this,"Your Bookmark", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(show_Rdetails.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });
        givereview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();
                SessionManager session=new SessionManager(show_Rdetails.this,"");


                if(session.checkLoginhome())
                {
                    Intent i=new Intent(show_Rdetails.this,RatingActivity.class);
                    i.putExtra("Rname",name);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(show_Rdetails.this,"Plz Login To give Review..!!",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(show_Rdetails.this,Login.class);
                    startActivity(i);


                }


            }
        });
        helper = new Sqlitehelper(show_Rdetails.this);
        db = helper.getReadableDatabase();





        try {


            String squery = "select * from RestaurantDetails where R_Name='"+name+"'";
            Cursor c = db.rawQuery(squery, null);
            if (c != null) {
                c.moveToFirst();
                String sRname = c.getString(0);
                String sRadd = c.getString(1);
                String sRcity = c.getString(2);
                String sRcuisine= c.getString(3);
                String sRophr= c.getString(4);
                String sRavgcost= c.getString(5);

                String sRavgperson= c.getString(6);
                String sRemail= c.getString(7);
                String sRcontact= c.getString(8);

                rname.setText(sRname+" Restaurant");
                radd.setText("Address: "+sRadd);
              rcity.setText("City: "+sRcity);
                rcuisine.setText("Cuisine: "+sRcuisine);
                rophr.setText("Opening Hour: "+sRophr);
                rcost.setText("Average Cost: Rs."+sRavgcost);
              //  rperson.setText(sRavgperson);
                rcontact.setText("Contact no : "+sRcontact);



                //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

            }
        }
        catch(Exception e)
        {
            Toast.makeText(show_Rdetails.this, ""+e, Toast.LENGTH_LONG).show();

        }

        Toast.makeText(show_Rdetails.this,""+session.getsessiontitle(),Toast.LENGTH_LONG).show();



    }
}
