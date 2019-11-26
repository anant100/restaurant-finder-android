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

public class show_bookmark extends AppCompatActivity {
    SQLiteDatabase db;
    Sqlitehelper helper;
    ListView listView ;
    Button bkdlt;
    ImageView home,loc,feed,setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookmark);

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

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

                PopupMenu popup = new PopupMenu(show_bookmark.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(show_bookmark.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(show_bookmark.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            Toast.makeText(show_bookmark.this,"Your Bookmark", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(show_bookmark.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });
        helper = new Sqlitehelper(show_bookmark.this);
        db = helper.getReadableDatabase();



        SessionManager session=new SessionManager(show_bookmark.this,"");
        String mail=session.getsessiontitle();

        if(session.checkLoginhome())
        {

        }
        else
        {

        }

                try {


            String squery = "select * from bookmark where email='"+mail+"'";
            Cursor c = db.rawQuery(squery, null);


                List<String> array = new ArrayList<String>();
                while(c.moveToNext()){
                  // String umail = c.getString(c.getColumnIndex("email"));
                    String uaddress = c.getString(c.getColumnIndex("address"));
                   // array.add(umail);
                    array.add(uaddress);
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

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }

            });

                //bk_mail.setText(b_mail);
                //bk.setText(b_address);

              //  Toast.makeText(show_bookmark.this, "Selected email is:--" +b_mail+"Selected addess is:--" + b_address, Toast.LENGTH_LONG).show();


        }
        catch(Exception e)
        {
            Toast.makeText(show_bookmark.this, ""+e, Toast.LENGTH_LONG).show();

        }

        Toast.makeText(show_bookmark.this,""+session.getsessiontitle(),Toast.LENGTH_LONG).show();



    }
}
