package com.test.greasyspoon;

/**
 * Created by User on 22/03/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

public class Sqlitehelper extends SQLiteOpenHelper {
    static String dbname = "greasyspoon.db";
    static int dbversion = 1;
    Context context;
    public Sqlitehelper(Context context) {
              super(context,context.getExternalFilesDir(null).getAbsolutePath()+"/"+dbname, null, dbversion);
        this.context = context;

// TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

        try {
            String query = "create table register(name varchar(50),email varchar(50) primary key,psw varchar(50),contact varchar(50))";
            db.execSQL(query);

            String q_regR = "create table reg_ROwner(name varchar(50),email varchar(50),psw varchar(50),contact varchar(50))";
            db.execSQL(q_regR);

            String res_query = "create table RestaurantDetails(R_Name varchar(50),R_Address varchar(50),R_City varchar(50),R_Cuisine varchar(50),R_Openhour varchar(50),R_avgcost varchar(50),R_person varchar(50),R_Email varchar(50),R_ContactNo varchar(50))";
            db.execSQL(res_query);

            String query1 = "create table bookmark(email varchar(50),address varchar(50))";
            db.execSQL(query1);

            String query2 = "create table addfavourite(email varchar(50),res_name varchar(50))";
            db.execSQL(query2);

            String query3 = "create table Res_review(email varchar(50),res_name varchar(50),res_rating varchar(50),res_review varchar(50))";
            db.execSQL(query3);

            String query4 = "create table city(city_name varchar(50),desc varchar(50))";
            db.execSQL(query4);

            String query5 = "create table cuisine(cuisine_id  integer primary key autoincrement, cuisine_name varchar(50),desc varchar(50))";
            db.execSQL(query5);

            String query6 = "create table offer(offer_id  integer primary key autoincrement, start_date date,end_date date,desc varchar(50))";
            db.execSQL(query6);

            String query7 = "create table credifinfo(credit_id  integer primary key autoincrement, U_id integer references register(email),card_no varchar(50),name_card varchar(50),cvv varchar(50),exp_month varchar(10),exp_year varchar(10))";
            //db.execSQL("PRAGMA foreign key=ON");
            db.execSQL(query7);

        String query8 = "create table food_item(item_id  integer primary key autoincrement,item_name varchar(50),item_price integer(10),item_desc varchar(50),offer_id integer references offer(offer_id),cuisine_id integer references cuisine(cuisine_id) )";
        db.execSQL(query8);

            String query9 = "create table shipping_details(s_address varchar(50),s_city varchar(50),s_pincode varchar(50),s_state varchar(50),s_country varchar(50),u_id integer references register(email),item_id integer references food_item(item_id) )";
            db.execSQL(query9);

        }
        catch (Exception e)
        {
            Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
        }





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }
}
