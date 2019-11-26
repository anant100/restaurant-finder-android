package com.test.greasyspoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    ImageView loc,home,feed,setting;
    RatingBar ratingBar;
    Button submitRateButton;
    TextView rateDisplay,tvres_review;
    EditText review;

    SQLiteDatabase db;
    Sqlitehelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        helper = new Sqlitehelper(RatingActivity.this);
        db = helper.getWritableDatabase();

        Intent intent=getIntent();
     final  String SelectedRname=intent.getStringExtra("Rname");

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

                PopupMenu popup = new PopupMenu(RatingActivity.this,v);
                popup.getMenuInflater().inflate(R.menu.lr, popup.getMenu());



                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        if(item.getItemId()==R.id.fb)
                        {

                            Toast.makeText(RatingActivity.this,"Give Your Feedback", Toast.LENGTH_LONG).show();
                            Intent feed1=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(feed1);
                        }
                        else if(item.getItemId()== R.id.rt1)
                        {

                            Toast.makeText(RatingActivity.this,"Give Your Rating plz", Toast.LENGTH_LONG).show();
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

                            Toast.makeText(RatingActivity.this,"default", Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });


                popup.show();//showing popup menu


            }
        });

      final  SessionManager session=new SessionManager(RatingActivity.this,"");
       String mail= session.getsessiontitle();


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitRateButton = (Button) findViewById(R.id.ratingSubmitButton);
        review=(EditText)findViewById(R.id.edtReview);
        tvres_review=(TextView)findViewById(R.id.resname_review);
        rateDisplay = (TextView) findViewById(R.id.ratingDisplay);
        tvres_review.setText("Give your opinion about:"+"\n\n"+SelectedRname);
        rateDisplay.setText("Your Rating :");
        Toast.makeText(getApplicationContext(), "UserMail: " + mail+" Rname"+SelectedRname, Toast.LENGTH_LONG).show();

        submitRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager session=new SessionManager(RatingActivity.this,"");
                String mail= session.getsessiontitle();

                Intent intent=getIntent();
               String SelectedRname=intent.getStringExtra("Rname");


                String ratingValue = String.valueOf(ratingBar.getRating());
                String reviewValue = String.valueOf(review.getText());
                rateDisplay.setText("Rate: " + ratingValue);

                try {



                String q = "Insert into Res_review values('" + mail + "','" + SelectedRname + "','" + ratingValue + "','" + reviewValue + "')";
                db.execSQL(q);
                    Toast.makeText(RatingActivity.this, "Succesfully Inserted Your Opinion "+mail, Toast.LENGTH_LONG).show();


                }
                catch(Exception e)
                {
                    Toast.makeText(RatingActivity.this, "Error :"+e, Toast.LENGTH_LONG).show();

                }


            }
        });

    }



    public void alertValidation(String str)
    {
        AlertDialog.Builder b1=new AlertDialog.Builder(RatingActivity.this);
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
