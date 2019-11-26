package com.test.greasyspoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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

import org.w3c.dom.Text;

public class mycart extends AppCompatActivity {

    SQLiteDatabase db,db1;
    Sqlitehelper helper;
    ImageView home,loc,feed,setting;
    TextView i_name,i_price,i_quantity,i_total;
    EditText s_add,s_city1,s_pin,s_state1,s_country1;
    Button order,calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        final  SessionManager session =new SessionManager(mycart.this,"");
        final String smail=session.getsessiontitle();



        helper = new Sqlitehelper(mycart.this);
        db = helper.getWritableDatabase();

try {
    i_name=(TextView)findViewById(R.id.p_name);
    i_price=(TextView)findViewById(R.id.p_price);
    i_quantity=(TextView)findViewById(R.id.p_quantity);
    i_total=(TextView)findViewById(R.id.total_price);
    calculate=(Button)findViewById(R.id.btn_calculate);
    s_add=(EditText)findViewById(R.id.s_address);
    s_city1=(EditText)findViewById(R.id.s_city);
    s_pin=(EditText)findViewById(R.id.s_pincode);
    s_state1=(EditText)findViewById(R.id.s_state);
    s_country1=(EditText)findViewById(R.id.s_country);

    Intent intent = getIntent();
    final String SelectedItemName = intent.getStringExtra("ItemName");
    final String SelectedItemPrice = intent.getStringExtra("ItemPrice");

   // Toast.makeText(mycart.this,"Selected Item Name :"+SelectedItemName,Toast.LENGTH_LONG).show();
    //Toast.makeText(mycart.this,"Selected Item price :"+SelectedItemPrice,Toast.LENGTH_LONG).show();

    i_name.setText(""+SelectedItemName);
    i_price.setText(""+SelectedItemPrice);




    home=(ImageView)findViewById(R.id.ImgHome);
    loc=(ImageView)findViewById(R.id.ImgLocation);
    feed=(ImageView)findViewById(R.id.ImgFeed);
    setting=(ImageView)findViewById(R.id.ImgSetting);


    order=(Button)findViewById(R.id.submitOrder);




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

            PopupMenu popup = new PopupMenu(mycart.this,v);
            popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {



                    if(item.getItemId()==R.id.fb)
                    {

                        Toast.makeText(mycart.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                        Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                        startActivity(feed1);
                    }
                    else if(item.getItemId()== R.id.rt1)
                    {

                        Toast.makeText(mycart.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                        Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                        startActivity(r1);

                    }
                    else if(item.getItemId()== R.id.bk1)
                    {

                        Toast.makeText(mycart.this,"Your Bookmark", Toast.LENGTH_LONG).show();
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

                        Toast.makeText(mycart.this,"default", Toast.LENGTH_LONG).show();
                    }

                    return false;
                }
            });


            popup.show();//showing popup menu


        }
    });

    calculate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String q=i_quantity.getText().toString();
            Integer quantity=Integer.parseInt(q);

            String p=i_price.getText().toString();
            Integer price=Integer.parseInt(p);

            Integer total=price*quantity;

            i_total.setText(""+total);


        }
    });

    order.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            String address = s_add.getText().toString();
            String city = s_city1.getText().toString();
            String pincode = s_pin.getText().toString();
            String state = s_state1.getText().toString();
            String country = s_country1.getText().toString();

            helper = new Sqlitehelper(mycart.this);
            db1 = helper.getReadableDatabase();













            try {

                if (address.isEmpty() || city.isEmpty() || pincode.isEmpty() || state.isEmpty() || country.isEmpty() ) {
                    alertValidation("plz Enter Empty places..");

                }
                 else if (pincode.length() > 6) {
                    alertValidation("pincode must be within 6 digits");
                } else {

                    String squery = "select * from food_item where item_name='"+SelectedItemName+"'";
                    Cursor c = db1.rawQuery(squery, null);
                    if (c != null) {
                        c.moveToFirst();
                         String item_id = c.getString(0);





                        String sqlquery = "Insert into shipping_details values('" + address + "','" + city + "','" + pincode + "','" + state + "','" + country + "','" + smail + "','" + item_id+ "')";
                        db.execSQL(sqlquery);


                        Toast.makeText(mycart.this, "Shipping detail inserted sucessfully ", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(mycart.this,checkout.class);
                        startActivity(i);

                    }



                }

            } catch (Exception e) {
                Toast.makeText(mycart.this, "" + e, Toast.LENGTH_LONG).show();

            }



        }
    });
    }
catch(Exception e) {

            Toast.makeText(mycart.this,""+e,Toast.LENGTH_LONG).show();

        }

    }
    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(mycart.this);
        b1.setMessage(str);
        b1.setCancelable(false);
        b1.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        AlertDialog showD=b1.create();
        showD.show();
    }

}
