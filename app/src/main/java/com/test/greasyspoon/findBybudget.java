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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class findBybudget extends AppCompatActivity {

    ImageView loc,home,feed,setting;
    SQLiteDatabase db;
    Sqlitehelper helper;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bybudget);

        Intent intent=getIntent();
        final  String SelectedBudget=intent.getStringExtra("Budget");

        helper = new Sqlitehelper(findBybudget.this);
        db = helper.getReadableDatabase();

        SessionManager session=new SessionManager(findBybudget.this,"");
        String mail=session.getsessiontitle();

        try {


            String squery = "select * from RestaurantDetails where R_avgcost <='"+SelectedBudget+"'";
            Cursor c = db.rawQuery(squery, null);




            List<String> array = new ArrayList<String>();
            while(c.moveToNext()){

                String rname = c.getString(c.getColumnIndex("R_Name"));
                // String radd = c.getString(c.getColumnIndex("R_Address"));
                array.add(rname);
                //array.add(radd);
            }





            // Get ListView object from xml
            listView = (ListView) findViewById(R.id.list);

            String[] values = new String[array.size()];
            values = array.toArray(values);



            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);


            // Assign adapter to ListView
            listView.setAdapter(adapter);

            // ListView Item Click Listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {




                    // ListView Clicked item index
                    int itemPosition     = position;

                    // ListView Clicked item value
                    String  itemValue    = (String) listView.getItemAtPosition(position);

                    String squery = "select * from RestaurantDetails where R_Name='"+itemValue+"'";
                    Cursor c = db.rawQuery(squery, null);
                    if (c != null) {
                        c.moveToFirst();
                        String rname = c.getString(0);
                        String radd = c.getString(1);
                        String rcity = c.getString(2);
                        String rcui = c.getString(3);
                        String rophr=c.getString(4);
                        String rcost=c.getString(5);
                        String rperson=c.getString(6);
                        String remail=c.getString(7);
                        String rcontact=c.getString(8);





                        Intent i=new Intent(findBybudget.this,show_mysearch.class);
                        i.putExtra("Restaurant Name",rname);
                        startActivity(i);







                    }


                }

            });

            //bk_mail.setText(b_mail);
            //bk.setText(b_address);

            //  Toast.makeText(show_bookmark.this, "Selected email is:--" +b_mail+"Selected addess is:--" + b_address, Toast.LENGTH_LONG).show();


        }
        catch(Exception e)
        {
            Toast.makeText(findBybudget.this, ""+e, Toast.LENGTH_LONG).show();

        }



        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);



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

                PopupMenu popup = new PopupMenu(findBybudget.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(findBybudget.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(findBybudget.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            SessionManager session=new SessionManager(findBybudget.this,"");
                            if(session.checkLoginhome())
                            {
                                Intent i=new Intent(findBybudget.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(findBybudget.this, Login.class);
                                startActivity(i);
                                Toast.makeText(findBybudget.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(findBybudget.this,"default", Toast.LENGTH_LONG).show();
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
