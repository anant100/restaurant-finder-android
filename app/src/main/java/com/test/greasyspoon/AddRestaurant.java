package com.test.greasyspoon;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class AddRestaurant extends Activity{



    SQLiteDatabase db;
    Sqlitehelper helper;

    EditText R_name, R_address,R_city,R_cuisine, R_openhour, R_avgcost,R_email,R_person,R_contact;
    Button btnaddRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);


        R_name = (EditText) findViewById(R.id.edt_RName);
        R_address = (EditText) findViewById(R.id.edt_Raddress);
        R_city = (EditText) findViewById(R.id.edt_RCity);
        R_cuisine = (EditText) findViewById(R.id.edt_RCusine);
        R_openhour = (EditText) findViewById(R.id.edt_ROpenHour);
        R_avgcost = (EditText) findViewById(R.id.edt_Ravgcost);
        R_email = (EditText) findViewById(R.id.edt_Remail);
        R_person = (EditText) findViewById(R.id.edt_person);
        R_contact = (EditText) findViewById(R.id.edt_RContact);
        btnaddRes = (Button) findViewById(R.id.btn_AddRestaurant);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String charPattern = "[a-z]+[A-Z]";

        // final  String NamePattern = "[a-zA-Z_]";

        helper = new Sqlitehelper(AddRestaurant.this);
        db = helper.getWritableDatabase();


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

                Intent i=new Intent(AddRestaurant.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });


        btnaddRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_Rname = R_name.getText().toString();
                String s_Radd = R_address.getText().toString();
                String s_Rcity = R_city.getText().toString();
                String s_Rcuisine = R_cuisine.getText().toString();
                String s_opHour = R_openhour.getText().toString();
                String s_avgcost = R_avgcost.getText().toString();
                String s_REmail = R_email.getText().toString().trim();
                String s_avgperson = R_person.getText().toString();
                String s_Rcontact = R_contact.getText().toString();

                try {

                    if (s_Rname.isEmpty() || s_Radd.isEmpty() || s_Rcity.isEmpty() || s_Rcuisine.isEmpty() || s_opHour.isEmpty() || s_avgcost.isEmpty() || s_avgperson.isEmpty()|| s_REmail.isEmpty()  || s_Rcontact.isEmpty()) {
                        alertValidation("plz Enter Empty places..");

                    }  else if (!s_REmail.matches(emailPattern)) {

                        alertValidation("You have entered Invalid Email.." +
                                "Plz Enter Valid Email");
                    } else if (s_Rcontact.length() > 10) {
                        alertValidation("Mobile Number must be within 10 digits");
                    } else {



                        String sqlquery = "Insert into RestaurantDetails values('" + s_Rname + "','" + s_Radd + "','" + s_Rcity + "','" + s_Rcuisine + "','" + s_opHour + "','" + s_avgcost + "','" + s_avgperson + "','" + s_REmail + "','" + s_Rcontact + "')";
                        db.execSQL(sqlquery);

                        /*
                        add  restaurant and send mail code
                         */

                        /*List<String> toEmailList = Arrays.asList(s_REmail.split("\\s*,\\s*"));


                        String fromemail = "greasyspoon2017@gmail.com";
                        String frompass = "greasyspoon2017@@@@@";
                        String fromsubject = "Greasy spoon App Restauraant registration";

                        new SendMailTask(AddRestaurant.this).execute(fromemail,
                                frompass, toEmailList, fromsubject, "<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td>Your Name:" + s_Rname + "</td></tr><tr><td>Your Email :" + s_REmail + "</td></tr><tr><td>Your Contact :" + s_Rcontact + "</td></tr><table>");
                        Toast.makeText(AddRestaurant.this, "Password Send in you Email Account..", Toast.LENGTH_LONG).show();

*/
                        Toast.makeText(AddRestaurant.this, "Restaurant added Succesfully ", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(AddRestaurant.this,view_myResDetails.class);
                        startActivity(i);


                    }

                } catch (Exception e) {
                    Toast.makeText(AddRestaurant.this, "" + e, Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(AddRestaurant.this);
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
