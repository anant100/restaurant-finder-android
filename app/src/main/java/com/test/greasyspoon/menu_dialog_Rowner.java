package com.test.greasyspoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class menu_dialog_Rowner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dialog__rowner);



        final TextView tvMenu_home=(TextView)findViewById(R.id.tvMenu1_home);
        TextView tvMenu_about=(TextView)findViewById(R.id.tvMenu3_about);
        TextView tvMenu_profile=(TextView)findViewById(R.id.tvMenu5_profile);
        TextView tvMenuAddRes=(TextView)findViewById(R.id.tvMenu6_AddRestaurant);
        TextView tvMenuViewRes=(TextView)findViewById(R.id.tvMenu7_ViewRestaurant);
        TextView tvMenuLogout=(TextView)findViewById(R.id.tvMenu8_Logout);
        TextView tvMenuExit=(TextView)findViewById(R.id.tvMenu9_Exit);
        final ImageView img_close=(ImageView)findViewById(R.id.imgClose);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                    Intent i = new Intent(menu_dialog_Rowner.this, HomeActivity_RestaurantOwner.class);
                    startActivity(i);




            }
        });

        tvMenu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent i = new Intent(menu_dialog_Rowner.this, HomeActivity_RestaurantOwner.class);
                    startActivity(i);


            }
        });
        tvMenu_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();



                Intent i=new Intent(menu_dialog_Rowner.this,about_us.class);
                startActivity(i);

            }
        });


        tvMenu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(menu_dialog_Rowner.this,HomeActivity_RestaurantOwner.class);
                startActivity(i);



            }
        });
        tvMenuAddRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();



                Intent i=new Intent(menu_dialog_Rowner.this,AddRestaurant.class);
                startActivity(i);

            }
        });
        tvMenuViewRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();



                Intent i=new Intent(menu_dialog_Rowner.this,view_myResDetails.class);
                startActivity(i);

            }
        });
        tvMenuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tvMenu_home.setTextColor();
                SessionManager session_logout=new SessionManager(getApplicationContext(),"");
                session_logout.deletetitlesession();
                Intent i=new Intent(menu_dialog_Rowner.this,HomeActivity.class);
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
        AlertDialog.Builder b1=new AlertDialog.Builder(menu_dialog_Rowner.this);
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
