package com.test.greasyspoon;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 24/03/2017.
 */

public class password_change extends Activity {
    EditText oldP,newP,cfP;
    Button updateP;

    SQLiteDatabase db;
    Sqlitehelper helper;

    ImageView home,loc,feed,setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        oldP=(EditText)findViewById(R.id.edt_oldpsw);
        newP=(EditText)findViewById(R.id.edt_newpsw);
        cfP=(EditText)findViewById(R.id.edt_cfpsw);
        updateP=(Button)findViewById(R.id.updatePsw);

        helper = new Sqlitehelper(password_change.this);
        db = helper.getWritableDatabase();


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

                PopupMenu popup = new PopupMenu(password_change.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(password_change.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(password_change.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

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

                            Toast.makeText(password_change.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });








                updateP.setOnClickListener(new View.OnClickListener() {



                    @Override
                    public void onClick(View v) {
                        try {

                            SessionManager session=new SessionManager(password_change.this,"");
                            String emailid=session.getsessiontitle();

                        String squery = "select * from register where email='"+emailid+"'";
                        Cursor c = db.rawQuery(squery, null);
                        if (c != null) {
                            c.moveToFirst();
                            String s_name = c.getString(0);
                            String s_mail = c.getString(1);
                            String s_psw = c.getString(2);
                            String s_contact= c.getString(3);
                            Toast.makeText(password_change.this, "Password is "+s_psw, Toast.LENGTH_LONG).show();



                            String old_psw = oldP.getText().toString();
                            String new_psw = newP.getText().toString();
                            String cf_psw = cfP.getText().toString();

                            if(old_psw.isEmpty() || new_psw.isEmpty() || cf_psw.isEmpty())
                            {
                                alertValidation("Plz Enter All empty Fields");
                            }
                            else if (old_psw.equals(s_psw))
                            {
                                if(new_psw.equals(cf_psw))
                                {
                                    String iquery = "update register set psw='" + new_psw + "' where psw = '" + old_psw + "'";
                                    db.execSQL(iquery);

                                    alertValidation("Both Password Matching..Password Updated");

                                  //mail code
                                     List<String> toEmailList = Arrays.asList(s_mail.split("\\s*,\\s*"));



                                     String fromemail = "greasyspoon2017@gmail.com";
                                     String frompass = "greasyspoon2017@@@@@";
                                     String fromsubject ="Greasy spoon App Changed Password ";

                                     new SendMailTask(password_change.this).execute(fromemail,frompass,toEmailList,fromsubject,"<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td> Changed Password :</td></tr><tr><td>"+new_psw+"</td></tr><table>");

                                     alertValidation("Your Updated Password Send in you Email Account.");


                              }
                                else
                              {
                                  alertValidation("Password Mismatch");


                              }
                            }
                                else
                                {

                                    alertValidation("Incorrect Old Password Plz Enter Correct Old Password");
                                }


                            }
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(password_change.this, "error :"+e, Toast.LENGTH_LONG).show();
                            alertValidation("error :"+e);

                        }

                    }
                //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

            });






    }

    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(password_change.this);
        b1.setMessage(str);
        b1.setCancelable(false);
        b1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog showD=b1.create();
        showD.show();
    }

}
