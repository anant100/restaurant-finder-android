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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class view_myResDetails extends AppCompatActivity {

    SQLiteDatabase db;
    Sqlitehelper helper;
    ListView listView ;
    Button addmore;

    //ImageView home,loc,feed,setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_res_details);

        addmore=(Button)findViewById(R.id.btn_AddmoreRestaurant);


       /* home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(),HomeActivity_RestaurantOwner.class);
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
        });*/
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

                Intent i=new Intent(view_myResDetails.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });
        helper = new Sqlitehelper(view_myResDetails.this);
        db = helper.getReadableDatabase();


        SessionManager session=new SessionManager(view_myResDetails.this,"");
        String mail=session.getsessiontitle();

        try {


            String squery = "select * from RestaurantDetails where R_Email='"+mail+"'";
            Cursor c = db.rawQuery(squery, null);




            List<String> array = new ArrayList<String>();
            while(c.moveToNext()){

                String rname = c.getString(c.getColumnIndex("R_Name"));
                // String radd = c.getString(c.getColumnIndex("R_Address"));
                array.add(rname);
                //  array.add(radd);
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





                        Intent i=new Intent(view_myResDetails.this,manage_myResDetails.class);
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
            Toast.makeText(view_myResDetails.this, ""+e, Toast.LENGTH_LONG).show();

        }

        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(view_myResDetails.this,AddRestaurant.class);
                startActivity(i);
            }
        });

        // Toast.makeText(View_Restaurants.this,""+session.getsessiontitle(),Toast.LENGTH_LONG).show();

    }
}
