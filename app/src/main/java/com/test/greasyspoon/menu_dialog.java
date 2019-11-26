package com.test.greasyspoon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class menu_dialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dialog);



        final TextView tvMenu_home=(TextView)findViewById(R.id.tvMenu1_home);
        TextView tvMenu_about=(TextView)findViewById(R.id.tvMenu3_about);
        TextView tvMenu_bookmark=(TextView)findViewById(R.id.tvMenu4_bookmark);
        TextView tvMenu_profile=(TextView)findViewById(R.id.tvMenu5_profile);
        TextView tvMenuViewRes=(TextView)findViewById(R.id.tvMenu7_ViewRestaurant);
        TextView tvMenuLogout=(TextView)findViewById(R.id.tvMenu8_Logout);
        TextView tvMenuExit=(TextView)findViewById(R.id.tvMenu9_Exit);
        final ImageView img_close=(ImageView)findViewById(R.id.imgClose);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SessionManager session=new SessionManager(menu_dialog.this,"");
                if(session.checkLoginhome())
                {
                    Intent i=new Intent(menu_dialog.this,HomeActivity_User.class);
                    startActivity(i);

                }
                else
                {

                    Intent i = new Intent(menu_dialog.this, HomeActivity.class);
                    startActivity(i);

                }


            }
        });

        tvMenu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SessionManager session=new SessionManager(menu_dialog.this,"");
                if(session.checkLoginhome())
                {
                    Intent i=new Intent(menu_dialog.this,HomeActivity_User.class);
                    startActivity(i);

                }
                else
                {

                    Intent i = new Intent(menu_dialog.this, HomeActivity.class);
                    startActivity(i);

                }
            }
        });
        tvMenu_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();



                Intent i=new Intent(menu_dialog.this,about_us.class);
                startActivity(i);

            }
        });
        tvMenu_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();

                SessionManager session=new SessionManager(menu_dialog.this,"");
                if(session.checkLoginhome())
                {
                    Intent i=new Intent(menu_dialog.this,show_bookmark.class);
                    startActivity(i);
                }
                else
                {

                    Intent i = new Intent(menu_dialog.this, Login.class);
                    startActivity(i);
                    Toast.makeText(menu_dialog.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                }



            }
        });
        tvMenu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SessionManager session=new SessionManager(menu_dialog.this,"");
                if(session.checkLoginhome())
                {
                    Intent i=new Intent(menu_dialog.this,viewProfile.class);
                    startActivity(i);

                }
                else
                {

                    Intent i = new Intent(menu_dialog.this, Login.class);
                    startActivity(i);
                    Toast.makeText(menu_dialog.this, "To View Profile ..Login Please..!!", Toast.LENGTH_LONG).show();
                }


            }
        });

        tvMenuViewRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();



                Intent i=new Intent(menu_dialog.this,View_Restaurants.class);
                startActivity(i);

            }
        });
        tvMenuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();
                SessionManager session_logout=new SessionManager(getApplicationContext(),"");
                session_logout.deletetitlesession();
                Intent i=new Intent(menu_dialog.this,HomeActivity.class);
                startActivity(i);

            }
        });
        tvMenuExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertConfirmation("Do you want to exit app?");

            }
        });




    }
    public void alertConfirmation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(menu_dialog.this);
        b1.setMessage(str);
        b1.setCancelable(false);
        setTitle("Confirmation");
        b1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }


        });
        b1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }


        });

        AlertDialog showD=b1.create();
        showD.show();
    }
}
