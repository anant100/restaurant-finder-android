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

public class HomeActivity_RestaurantOwner extends AppCompatActivity {

    Button m_rest,m_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__restaurant_owner);

        SessionManager session=new SessionManager(HomeActivity_RestaurantOwner.this,"");
        String mail=session.getsessiontitle();

        m_rest=(Button)findViewById(R.id.Managerest);
        m_profile=(Button)findViewById(R.id.ManageProfile);


        m_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feed=new Intent(getApplicationContext(),view_myResDetails.class);
                startActivity(feed);
            }
        });

        m_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager session=new SessionManager(HomeActivity_RestaurantOwner.this,"");
                String mail=session.getsessiontitle();
                Toast.makeText(HomeActivity_RestaurantOwner.this,""+session.getsessiontitle(),Toast.LENGTH_LONG).show();

                Intent myacc=new Intent(getApplicationContext(),view_ROprofile.class);
                startActivity(myacc);

            }
        });





        ImageView imgMenu=(ImageView)findViewById(R.id.Img2);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeActivity_RestaurantOwner.this,menu_dialog_Rowner.class);
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



    }
}
