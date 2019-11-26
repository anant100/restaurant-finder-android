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
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ROPasswordChange extends AppCompatActivity {

    EditText oldP,newP,cfP;
    Button updateP;

    SQLiteDatabase db;
    Sqlitehelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropassword_change);

        oldP=(EditText)findViewById(R.id.edt_oldpsw);
        newP=(EditText)findViewById(R.id.edt_newpsw);
        cfP=(EditText)findViewById(R.id.edt_cfpsw);
        updateP=(Button)findViewById(R.id.updatePsw);

        helper = new Sqlitehelper(ROPasswordChange.this);
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

                Intent i=new Intent(ROPasswordChange.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);

            }
        });






        updateP.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                try {

                    SessionManager session=new SessionManager(ROPasswordChange.this,"");
                    String emailid=session.getsessiontitle();

                    String squery = "select * from reg_ROwner where email='"+emailid+"'";
                    Cursor c = db.rawQuery(squery, null);
                    if (c != null) {
                        c.moveToFirst();
                        String s_name = c.getString(0);
                        String s_mail = c.getString(1);
                        String s_psw = c.getString(2);
                        String s_contact= c.getString(3);
                        Toast.makeText(ROPasswordChange.this, "Password is "+s_psw, Toast.LENGTH_LONG).show();



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
                                String iquery = "update reg_ROwner set psw='" + new_psw + "' where psw = '" + old_psw + "'";
                                db.execSQL(iquery);

                                alertValidation("Both Password Matching..Password Updated");

                                //mail code
                                List<String> toEmailList = Arrays.asList(s_mail.split("\\s*,\\s*"));



                                String fromemail = "greasyspoon2017@gmail.com";
                                String frompass = "greasyspoon2017@@@@@";
                                String fromsubject ="Greasy spoon App Changed Password ";

                                new SendMailTask(ROPasswordChange.this).execute(fromemail,frompass,toEmailList,fromsubject,"<table border=1><tr><td>Welcome to GreasySpoon</td></tr><tr><td> Changed Password :</td></tr><tr><td>"+new_psw+"</td></tr><table>");

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
                    Toast.makeText(ROPasswordChange.this, "error :"+e, Toast.LENGTH_LONG).show();
                    alertValidation("error :"+e);

                }

            }
            //Toast.makeText(Login.this, "Selected name is:--" + s_name+"Selected mail is:--" + s_mail+"Selected psw is:--" + s_psw, Toast.LENGTH_LONG).show();

        });

    }

    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(ROPasswordChange.this);
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
