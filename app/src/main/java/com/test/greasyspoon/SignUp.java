package com.test.greasyspoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;
import java.util.List;

import static android.R.attr.password;

public class SignUp extends AppCompatActivity {

    ImageView home,loc,feed,setting;

    SQLiteDatabase db;
    Sqlitehelper helper;

    EditText name, email, psw, cpsw, contact;
    CheckBox chk_resOwner;
    Button signup;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        psw = (EditText) findViewById(R.id.edtPwd);
        cpsw = (EditText) findViewById(R.id.edtCnfPwd);
        contact = (EditText) findViewById(R.id.edtContact);
        chk_resOwner=(CheckBox)findViewById(R.id.chk_imResOwner);
        signup = (Button) findViewById(R.id.btn_SignUpMain);

        final  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

       // final  String NamePattern = "[a-zA-Z_]";

        helper = new Sqlitehelper(SignUp.this);
        db = helper.getWritableDatabase();

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

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

                PopupMenu popup = new PopupMenu(SignUp.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(SignUp.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(SignUp.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {
                            SessionManager session=new SessionManager(SignUp.this,"");
                            if(session.checkLoginhome())
                            {
                                Intent i=new Intent(SignUp.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(SignUp.this, Login.class);
                                startActivity(i);
                                Toast.makeText(SignUp.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(SignUp.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!chk_resOwner.isChecked())
                {



                String sname = name.getText().toString();
                String spsw = psw.getText().toString();
                String semail = email.getText().toString().trim();
                String cn = contact.getText().toString();
                String scfpsw=cpsw.getText().toString();


                try {

                    if (sname.isEmpty() || spsw.isEmpty() || semail.isEmpty() || cn.isEmpty() || scfpsw.isEmpty())
                    {
                        alertValidation("plz Enter Empty places..");

                    }
                    else if(!semail.matches(emailPattern))
                    {

                        alertValidation("You have entered Invalid Email.." +
                                "Plz Enter Valid Email");
                    }
                    else if(!spsw.equals(scfpsw))
                    {
                        alertValidation("Password Mismatch");

                    }
                    else if(cn.length()> 10)
                    {
                        alertValidation("Phone Number must be in 10 digits");
                    }
                    else
                    {


                        //String iquery = "insert into info values("+er+",'"+nm+"')";
                        String sqlquery = "Insert into register values('" + sname + "','" + semail + "','" + spsw + "','" + cn + "')";
                        db.execSQL(sqlquery);
                        List<String> toEmailList = Arrays.asList(semail.split("\\s*,\\s*"));


                        String fromemail = "greasyspoon2017@gmail.com";
                        String frompass = "greasyspoon2017@@@@@";
                        String fromsubject = "Greasy spoon App registration";

                        new SendMailTask(SignUp.this).execute(fromemail,
                                frompass, toEmailList, fromsubject, "<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td>Your Name:" + sname + "</td></tr><tr><td>Your Email :" + semail + "</td></tr><tr><td>Your Password:" + spsw + "</td></tr><tr><td>Your Contact :" + cn + "</td></tr><table>");
                        Toast.makeText(SignUp.this, "Password Send in you Email Account..", Toast.LENGTH_LONG).show();


                        Toast.makeText(SignUp.this, "Succesfully Inserted", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(SignUp.this, Login.class);
                        startActivity(i);
                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(SignUp.this, ""+e, Toast.LENGTH_LONG).show();

                }


            }
                else
                {
                    String sname = name.getText().toString();
                    String spsw = psw.getText().toString();
                    String semail = email.getText().toString().trim();
                    String cn = contact.getText().toString();
                    String scfpsw=cpsw.getText().toString();


                    try {

                        if (sname.isEmpty() || spsw.isEmpty() || semail.isEmpty() || cn.isEmpty() || scfpsw.isEmpty())
                        {
                            alertValidation("plz Enter Empty places..");

                        }
                        else if(!semail.matches(emailPattern))
                        {

                            alertValidation("You have entered Invalid Email.." +
                                    "Plz Enter Valid Email");
                        }
                        else if(!spsw.equals(scfpsw))
                        {
                            alertValidation("Password Mismatch");

                        }
                        else if(cn.length()> 10)
                        {
                            alertValidation("Phone Number must be in 10 digits");
                        }
                        else
                        {


                            //String iquery = "insert into info values("+er+",'"+nm+"')";
                            String sqlquery = "Insert into reg_ROwner values('" + sname + "','" + semail + "','" + spsw + "','" + cn + "')";
                            db.execSQL(sqlquery);
                            List<String> toEmailList = Arrays.asList(semail.split("\\s*,\\s*"));


                            String fromemail = "greasyspoon2017@gmail.com";
                            String frompass = "greasyspoon2017@@@@@";
                            String fromsubject = "Greasy spoon App registration";

                            new SendMailTask(SignUp.this).execute(fromemail,
                                    frompass, toEmailList, fromsubject, "<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td>Your Name:" + sname + "</td></tr><tr><td>Your Email :" + semail + "</td></tr><tr><td>Your Password:" + spsw + "</td></tr><tr><td>Your Contact :" + cn + "</td></tr><table>");
                            Toast.makeText(SignUp.this, "Password Send in you Email Account..", Toast.LENGTH_LONG).show();


                            Toast.makeText(SignUp.this, "Succesfully Inserted", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(SignUp.this, Login.class);
                            startActivity(i);
                        }

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(SignUp.this, ""+e, Toast.LENGTH_LONG).show();

                    }

                }
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SignUp Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(SignUp.this);
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
