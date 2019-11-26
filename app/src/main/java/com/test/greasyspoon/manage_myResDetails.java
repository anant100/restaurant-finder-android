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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class manage_myResDetails extends AppCompatActivity {
    SQLiteDatabase db,db1;
    Sqlitehelper helper;
    ListView listView ;
    TextView rname,radd,rcity,rcuisine,rophr,rcost,rperson,rcontact;
    Button editR,deleteR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_res_details);

        SessionManager session=new SessionManager(manage_myResDetails.this,"");
        final String mail=session.getsessiontitle();

        rname=(TextView)findViewById(R.id.viewRname);
        radd=(TextView)findViewById(R.id.viewRadd);
        rcity=(TextView)findViewById(R.id.viewRcity);
        rcuisine=(TextView)findViewById(R.id.viewRcuisine);
        rophr=(TextView)findViewById(R.id.viewRophr);
        rcost=(TextView)findViewById(R.id.viewRavgcost);
        rperson=(TextView)findViewById(R.id.viewRavgPerson);
        rcontact=(TextView)findViewById(R.id.viewRcontact);
        editR=(Button)findViewById(R.id.btn_edit);
        deleteR=(Button)findViewById(R.id.btn_delete);




        Intent i=getIntent();
        final String name=i.getStringExtra("Restaurant Name");


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

                Intent i=new Intent(manage_myResDetails.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });
        helper = new Sqlitehelper(manage_myResDetails.this);
        db = helper.getReadableDatabase();
        db1=helper.getWritableDatabase();





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
            Toast.makeText(manage_myResDetails.this, ""+e, Toast.LENGTH_LONG).show();

        }



        editR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(manage_myResDetails.this,""+mail+"selected edit:"+name,Toast.LENGTH_LONG).show();
                Intent i=new Intent(manage_myResDetails.this,EditRestaurant.class);
                i.putExtra("Rname",name);
                startActivity(i);
            }
        });
        deleteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(manage_myResDetails.this,""+mail+"selected delete:"+name,Toast.LENGTH_LONG).show();

                String sqlquery = "delete from RestaurantDetails where R_Name='"+name+"'";
                db1.execSQL(sqlquery);
                alertValidation("Restaurant deleted Successfully ");
                Intent i=new Intent(manage_myResDetails.this,view_myResDetails.class);
                startActivity(i);
               // Toast.makeText(manage_myResDetails.this,name+ "Restaurant deleted Successfully",Toast.LENGTH_LONG).show();


            }
        });
        /*Intent ip=getIntent();

        String name=ip.getStringExtra("Restaurant Name");
        String add=ip.getStringExtra("Restaurant Address");
        String cty=ip.getStringExtra("Restaurant City");
        String cuisine=ip.getStringExtra("Restaurant Cuisine");
        String ohr=ip.getStringExtra("Restaurant Opening Hour");

        rname.setText(name);
        radd.setText(add);
        rcity.setText(cty);
        rcuisine.setText(cuisine);
        rophr.setText(ohr);*/
    }
    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(manage_myResDetails.this);
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
