package com.test.greasyspoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {

    EditText ETemail,ETpsw;
    ImageView loc,home,feed,setting;

    Button BNlogin;
    TextView TVFpsw,TVnewacc;

    SQLiteDatabase db;
    Sqlitehelper helper;
    CheckBox chk_resOwnerLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //final  String pswPattern = "[a-zA-Z0-9._-]";

        ETemail=(EditText)findViewById(R.id.edtEmail1);
        ETpsw=(EditText)findViewById(R.id.edtPwd1);
        BNlogin=(Button)findViewById(R.id.btn_LoginMain);
        TVFpsw=(TextView)findViewById(R.id.tvForgotPsw);
        TVnewacc=(TextView)findViewById(R.id.tvCreateAcc);
        chk_resOwnerLogin=(CheckBox)findViewById(R.id.chk_imResOwnerLogin);

        helper = new Sqlitehelper(Login.this);
        db = helper.getReadableDatabase();

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

                PopupMenu popup = new PopupMenu(Login.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(Login.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(Login.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            SessionManager session=new SessionManager(Login.this,"");
                            if(session.checkLoginhome())
                            {
                                Intent i=new Intent(Login.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(Login.this, Login.class);
                                startActivity(i);
                                Toast.makeText(Login.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(Login.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });
        TVnewacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent S=new Intent(getApplicationContext(),SignUp.class);
                startActivity(S);
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


        final String a_mail="admin";
        final String a_psw="admin";

        BNlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!chk_resOwnerLogin.isChecked()) {

                    String spsw = ETpsw.getText().toString();
                    String semail = ETemail.getText().toString().trim();
                    if ((semail.equals(a_mail) ) && (spsw.equals(a_psw) ))
                    {

                        Intent i = new Intent(Login.this, HomeActivity_Admin.class);
                        startActivity(i);
                        Toast.makeText(Login.this, "Welcome Admin", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            if (semail.isEmpty() || spsw.isEmpty()) {
                                alertValidation("Plz Enter Empty Field..");
                            } else if (!semail.matches(emailPattern)) {
                                alertValidation("Invalid email address");
                            } else {
                                alertValidation("valid email address");
                                String squery = "select * from register where email='" + semail + "'and psw='" + spsw + "'";
                                Cursor c = db.rawQuery(squery, null);
                                if (c != null) {
                                    c.moveToFirst();
                                    String s_name = c.getString(0);
                                    String s_mail = c.getString(1);
                                    String s_psw = c.getString(2);
                                    String s_contact = c.getString(3);
                                    Intent i = new Intent(Login.this, HomeActivity_User.class);
                                    SessionManager session = new SessionManager(Login.this, "");
                                    session.createtitlesession(s_mail);
                                    startActivity(i);
                                }


                            }
                        } catch (Exception e) {
                            Toast.makeText(Login.this, "" + e, Toast.LENGTH_LONG).show();

                        }
                    }
                    }
                    else
                    {
                        String spsw = ETpsw.getText().toString();
                        String semail = ETemail.getText().toString().trim();

                        try {
                            if (semail.isEmpty() || spsw.isEmpty()) {
                                alertValidation("Plz Enter Empty Field..");
                            } else if (!semail.matches(emailPattern)) {
                                alertValidation("Invalid email address");
                            } else {
                                alertValidation("valid email address");
                                String squery = "select * from reg_ROwner where email='" + semail + "'and psw='" + spsw + "'";
                                Cursor c = db.rawQuery(squery, null);
                                if (c != null) {
                                    c.moveToFirst();
                                    String s_name = c.getString(0);
                                    String s_mail = c.getString(1);
                                    String s_psw = c.getString(2);
                                    String s_contact = c.getString(3);
                                    Intent i = new Intent(Login.this, HomeActivity_RestaurantOwner.class);
                                    SessionManager session = new SessionManager(Login.this, "");
                                    session.createtitlesession(s_mail);
                                    startActivity(i);
                                }


                            }
                        } catch (Exception e) {
                            Toast.makeText(Login.this, "" + e, Toast.LENGTH_LONG).show();

                        }
                    }


            }
        });


        TVFpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk_resOwnerLogin.isChecked()) {

                    String spsw = ETpsw.getText().toString();
                    String semail = ETemail.getText().toString();
                    try {


                        String squery = "select * from register where email='" + semail + "'";
                        Cursor c = db.rawQuery(squery, null);
                        if (c != null) {
                            c.moveToFirst();
                            String s_name = c.getString(0);
                            String s_mail = c.getString(1);
                            String s_psw = c.getString(2);
                            String s_contact = c.getString(3);

                            //mail code
                            List<String> toEmailList = Arrays.asList(semail.split("\\s*,\\s*"));


                            String fromemail = "greasyspoon2017@gmail.com";
                            String frompass = "greasyspoon2017@@@@@";
                            String fromsubject = "Greasy spoon App Forgot Password ";

                            new SendMailTask(Login.this).execute(fromemail, frompass, toEmailList, fromsubject, "<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td>Password :</td></tr><tr><td>" + s_psw + "</td></tr><table>");
                            Toast.makeText(Login.this, "Your Password Send in you Email Account..", Toast.LENGTH_LONG).show();

                            //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

                        }
                    } catch (Exception e) {
                        Toast.makeText(Login.this, "" + e, Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    String spsw = ETpsw.getText().toString();
                    String semail = ETemail.getText().toString();
                    try {


                        String squery = "select * from reg_ROwner where email='" + semail + "'";
                        Cursor c = db.rawQuery(squery, null);
                        if (c != null) {
                            c.moveToFirst();
                            String s_name = c.getString(0);
                            String s_mail = c.getString(1);
                            String s_psw = c.getString(2);
                            String s_contact = c.getString(3);

                            //mail code
                            List<String> toEmailList = Arrays.asList(semail.split("\\s*,\\s*"));


                            String fromemail = "greasyspoon2017@gmail.com";
                            String frompass = "greasyspoon2017@@@@@";
                            String fromsubject = "Greasy spoon App Forgot Password ";

                            new SendMailTask(Login.this).execute(fromemail, frompass, toEmailList, fromsubject, "<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td>Password :</td></tr><tr><td>" + s_psw + "</td></tr><table>");
                            Toast.makeText(Login.this, "Your Password Send in you Email Account..", Toast.LENGTH_LONG).show();

                            //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

                        }
                    } catch (Exception e) {
                        Toast.makeText(Login.this, "" + e, Toast.LENGTH_LONG).show();

                    }

                }


            }
        });

    }
    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(Login.this);
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
