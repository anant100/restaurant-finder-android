package com.test.greasyspoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class payment extends AppCompatActivity {
    ImageView home,loc,feed,setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        WebView wvpayment=(WebView)findViewById(R.id.wbPayment);
        CookieManager.getInstance().setAcceptCookie(true);
        wvpayment.getSettings().setJavaScriptEnabled(true);

        wvpayment.loadUrl("https://www.paypal.com/webapps/hermes?token=57Y38316KM3401407&useraction=commit&mfid=1494691909767_e6fd01a442396");

        final  SessionManager session =new SessionManager(payment.this,"");
        String s=session.getsessiontitle();

        home=(ImageView)findViewById(R.id.ImgHome);
        loc=(ImageView)findViewById(R.id.ImgLocation);
        feed=(ImageView)findViewById(R.id.ImgFeed);
        setting=(ImageView)findViewById(R.id.ImgSetting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.checkLoginhome()) {

                    Intent home = new Intent(getApplicationContext(), HomeActivity_User.class);
                    startActivity(home);
                }
                else{

                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);

                }
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

                PopupMenu popup = new PopupMenu(payment.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(payment.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {






                            if(session.checkLoginhome())
                            {
                                Toast.makeText(payment.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
                                Intent r1=new Intent(getApplicationContext(),RatingActivity.class);
                                startActivity(r1);
                            }
                            else
                            {

                                Intent i = new Intent(payment.this, Login.class);
                                startActivity(i);
                                Toast.makeText(payment.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                            }


                        }
                        else if(item.getItemId()== R.id.bk1)
                        {



                            if(session.checkLoginhome())
                            {
                                Toast.makeText(payment.this,"Your Bookmark", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(payment.this,show_bookmark.class);
                                startActivity(i);
                            }
                            else
                            {

                                Intent i = new Intent(payment.this, Login.class);
                                startActivity(i);
                                Toast.makeText(payment.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                            }






                        }

                        else if(item.getItemId()== R.id.sp)
                        {
                            if(session.checkLoginhome())
                            {
                                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                                share.setType("text/plain");
                                share.putExtra(Intent.EXTRA_SUBJECT, "Download GreasySpoon app.....");
                                share.putExtra(Intent.EXTRA_TEXT, "Download GreasySpoon app from playstore...");
                                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(Intent.createChooser(share, "Share App!"));
                            }
                            else
                            {

                                Intent i = new Intent(payment.this, Login.class);
                                startActivity(i);
                                Toast.makeText(payment.this, "Please Login to view your bookmark ..!!", Toast.LENGTH_LONG).show();
                            }



                        }
                        else {

                            Toast.makeText(payment.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });

    }
}
