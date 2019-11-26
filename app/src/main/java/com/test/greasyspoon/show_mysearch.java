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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class show_mysearch extends AppCompatActivity {
    ImageView loc,home,feed,setting;
    SQLiteDatabase db;
    Sqlitehelper helper;

    TextView rname,radd,rcity,rcuisine,rophr,rcost,rperson,rcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mysearch);

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);


        SessionManager session=new SessionManager(show_mysearch.this,"");
        final String mail=session.getsessiontitle();

        rname=(TextView)findViewById(R.id.viewRname);
        radd=(TextView)findViewById(R.id.viewRadd);
        rcity=(TextView)findViewById(R.id.viewRcity);
        rcuisine=(TextView)findViewById(R.id.viewRcuisine);
        rophr=(TextView)findViewById(R.id.viewRophr);
        rcost=(TextView)findViewById(R.id.viewRavgcost);
        rperson=(TextView)findViewById(R.id.viewRavgPerson);
        rcontact=(TextView)findViewById(R.id.viewRcontact);

        Intent i=getIntent();
        final String name=i.getStringExtra("Restaurant Name");

        helper = new Sqlitehelper(show_mysearch.this);
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
                String sAvgcost= c.getString(5);
                String sPerson= c.getString(6);
                String sRemail= c.getString(7);
                String sRcontact= c.getString(8);

                rname.setText(sRname +" Restaurant");
                radd.setText("Address: "+sRadd);
                rcity.setText("City: "+sRcity);
                rcuisine.setText("Cuisine: "+sRcuisine);
                rophr.setText("Opening Hour: "+sRophr);
                rcost.setText("Average Cost: Rs."+sAvgcost);
                rperson.setText("For No of person: "+sPerson);
                rcontact.setText("Contact no : "+sRcontact);



                //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

            }
        }
        catch(Exception e)
        {
            Toast.makeText(show_mysearch.this, ""+e, Toast.LENGTH_LONG).show();

        }


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

                PopupMenu popup = new PopupMenu(show_mysearch.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(show_mysearch.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(show_mysearch.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {
                            SessionManager session=new SessionManager(show_mysearch.this,"");
                            if(session.checkLoginhome())
                            {
                                Intent i=new Intent(show_mysearch.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(show_mysearch.this, Login.class);
                                startActivity(i);
                                Toast.makeText(show_mysearch.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                            }

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

                            Toast.makeText(show_mysearch.this,"default", Toast.LENGTH_LONG).show();
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
