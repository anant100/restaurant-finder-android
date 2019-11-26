package com.test.greasyspoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HomeActivity_User extends AppCompatActivity {
    ImageView home,loc,feed,setting;
    Button share,profile,resD,bookmark,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__user);



        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

        share=(Button)findViewById(R.id.Shareapp);
        resD=(Button)findViewById(R.id.ViewRes);
        profile=(Button)findViewById(R.id.MyProfile);
        bookmark=(Button)findViewById(R.id.ViewBookmark);
        cart=(Button)findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewFoodItemList.class);
                startActivity(i);
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),show_bookmark.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),viewProfile.class);
                startActivity(i);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Download GreasySpoon app.....");
                share.putExtra(Intent.EXTRA_TEXT, "Download GreasySpoon app from playstore...");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Intent.createChooser(share, "Share App!"));
            }
        });

        resD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),View_Restaurants.class);
                startActivity(i);
            }
        });

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

        ImageView imgMenu=(ImageView)findViewById(R.id.Img2);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity_User.this,menu_dialog.class);
                startActivity(i);


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

        ImageView imgHeader=(ImageView)findViewById(R.id.Img4) ;

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(HomeActivity_User.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(HomeActivity_User.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(HomeActivity_User.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                            Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                            startActivity(r1);

                        }
                        else if(item.getItemId()== R.id.bk1)
                        {

                            Toast.makeText(HomeActivity_User.this,"Your Bookmark", Toast.LENGTH_LONG).show();
                            Intent b1=new Intent(getApplicationContext(),show_bookmark.class);
                            startActivity(b1);

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

                            Toast.makeText(HomeActivity_User.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });
    }
}
