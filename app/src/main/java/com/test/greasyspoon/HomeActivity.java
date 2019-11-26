package com.test.greasyspoon;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.ViewFlipper;




public class HomeActivity extends AppCompatActivity {

    MenuItem feed1;
    ImageView loc,home,feed,setting;
    Button login,search,bookmark,vmap,findRes,signup;
    int mFlipping=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


       ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        if(mFlipping==0)
        {
            flipper.startFlipping();
            mFlipping=1;
        }

        login=(Button)findViewById(R.id.LoginBtn);
        search=(Button)findViewById(R.id.NearRes);
        vmap=(Button)findViewById(R.id.dir);
        bookmark=(Button)findViewById(R.id.bookmark);
        findRes=(Button)findViewById(R.id.rest);
        signup=(Button)findViewById(R.id.signup);

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(HomeActivity.this,SignUp.class);
                startActivity(i);



            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SessionManager session=new SessionManager(HomeActivity.this,"");
                if(session.checkLoginhome())
                {
                    Intent i=new Intent(HomeActivity.this,show_bookmark.class);
                    startActivity(i);
                }
                else
                {

                    Intent i = new Intent(HomeActivity.this, Login.class);
                    startActivity(i);
                    Toast.makeText(HomeActivity.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                }


            }
        });
        vmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc=new Intent(getApplicationContext(),Login.class);
                Toast.makeText(HomeActivity.this, "Please Login to Order Food Now ..!!", Toast.LENGTH_LONG).show();
                startActivity(loc);


            }
        });
        findRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(getApplicationContext(),findres.class);
                startActivity(r);


            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r1=new Intent(getApplicationContext(),advanceSearch.class);
                startActivity(r1);



            }
        });

        ImageView imgHeader=(ImageView)findViewById(R.id.Img4) ;

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(HomeActivity.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());





                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(HomeActivity.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(HomeActivity.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            SessionManager session=new SessionManager(HomeActivity.this,"");
                            if(session.checkLoginhome())
                            {
                                Intent i=new Intent(HomeActivity.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(HomeActivity.this, Login.class);
                                startActivity(i);
                                Toast.makeText(HomeActivity.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(HomeActivity.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });
        ImageView imgMenu=(ImageView)findViewById(R.id.Img2);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity.this,menu_dialog.class);
                startActivity(i);
                finish();

                /*final Dialog d_menu_popup = new Dialog(HomeActivity.this);
                d_menu_popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d_menu_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d_menu_popup.setContentView(R.layout.activity_menu_dialog);
                Window window = d_menu_popup.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);
*/

            }
        });


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(loc);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(getApplicationContext(),Login.class);
                startActivity(login);
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

    }
}
