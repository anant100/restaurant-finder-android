package com.test.greasyspoon;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditRestaurant extends Activity{



    SQLiteDatabase db,db1;
    Sqlitehelper helper;
    TextView R_name;

    EditText  R_address,R_city,R_cuisine, R_openhour, R_avgcost,R_email,R_person,R_contact;
    Button btneditRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);


        R_name = (TextView)findViewById(R.id.edt_RName);
        R_address = (EditText) findViewById(R.id.edt_Raddress);
        R_city = (EditText) findViewById(R.id.edt_RCity);
        R_cuisine = (EditText) findViewById(R.id.edt_RCusine);
        R_openhour = (EditText) findViewById(R.id.edt_ROpenHour);
        R_avgcost = (EditText) findViewById(R.id.edt_Ravgcost);
        R_email = (EditText) findViewById(R.id.edt_Remail);
        R_person = (EditText) findViewById(R.id.edt_person);
        R_contact = (EditText) findViewById(R.id.edt_RContact);
        btneditRes = (Button) findViewById(R.id.btn_EditRestaurant);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Intent intent=getIntent();
        final  String SelectedRname=intent.getStringExtra("Rname");


        helper = new Sqlitehelper(EditRestaurant.this);
        db = helper.getReadableDatabase();

        helper = new Sqlitehelper(EditRestaurant.this);
        db1 = helper.getWritableDatabase();





        try {


            String squery = "select * from RestaurantDetails where R_Name='"+SelectedRname+"'";
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

               R_name.setText(sRname );
                R_address.setText(sRadd);
                R_city.setText(sRcity);
                R_cuisine.setText(sRcuisine);
                R_openhour.setText(sRophr);
                R_avgcost.setText(sAvgcost);
                R_person.setText(sPerson);
                R_email.setText(sRemail);
                R_contact.setText(sRcontact);



                //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

            }
        }
        catch(Exception e)
        {
            Toast.makeText(EditRestaurant.this, ""+e, Toast.LENGTH_LONG).show();

        }






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

                Intent i=new Intent(EditRestaurant.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });


        btneditRes.setOnClickListener(new View.OnClickListener() {
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



                        String sqlquery = "update RestaurantDetails set R_Address='" + s_Radd + "',R_City='" + s_Rcity + "',R_Cuisine='" + s_Rcuisine + "', R_Openhour='" + s_opHour+ "', R_avgcost='" + s_avgcost + "', R_person='" + s_avgperson + "', R_Email='" + s_REmail + "', R_ContactNo='" + s_Rcontact + "' where R_Name = '" + SelectedRname + "'";
                        db1.execSQL(sqlquery);
                        alertValidation("Restaurant Updated Succesfully ");
                        Intent i=new Intent(EditRestaurant.this,view_myResDetails.class);
                        startActivity(i);
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

                      //  Toast.makeText(EditRestaurant.this, "Restaurant Updated Succesfully ", Toast.LENGTH_LONG).show();
                        //Intent i=new Intent(EditRestaurant.this,view_myResDetails.class);
                        //startActivity(i);


                    }

                } catch (Exception e) {
                    Toast.makeText(EditRestaurant.this, "" + e, Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(EditRestaurant.this);
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
