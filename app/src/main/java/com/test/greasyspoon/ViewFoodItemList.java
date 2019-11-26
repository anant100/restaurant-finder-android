package com.test.greasyspoon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFoodItemList extends AppCompatActivity {
    SQLiteDatabase db;
    Sqlitehelper hlpr;
    String s,br,yr,sen,sem;
    ListView lv;
    String[] data2,data1,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_item_list);
        try {
            hlpr = new Sqlitehelper(ViewFoodItemList.this);
            db= hlpr.getWritableDatabase();

            String sql = "select * from food_item";
          //  Toast.makeText(ViewFoodItemList.this,"Welcome to Food item page.."+sql,Toast.LENGTH_LONG).show();


            final Cursor crs = db.rawQuery(sql, null);
            crs.moveToFirst();
            final ArrayList<String> a = new ArrayList<>();
            final ArrayList<String> a1 = new ArrayList<>();
            final ArrayList<String> a2 = new ArrayList<>();

            while (!crs.isAfterLast())
            {
                String itemname=crs.getString(1);

                a.add(itemname);
                String itemprice=crs.getString(2);
                a1.add(itemprice);
                crs.moveToNext();
                a2.add(itemname+"\n"+"Price :"+itemprice);

            }

            data = new String[a.size()];
            data = a.toArray(data);

            data1 = new String[a1.size()];
            data1 = a1.toArray(data1);

            data2=new String[a2.size()];
            data2= a2.toArray(data2);

            lv = (ListView) findViewById(R.id.listView3);

            ArrayAdapter adapter = new ArrayAdapter(ViewFoodItemList.this,R.layout.listdata,R.id.textView51,data2);
            lv.setAdapter(adapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                  //  Toast.makeText(ViewFoodItemList.this,"Item Price :"+data1[position],Toast.LENGTH_LONG).show();
                    //Toast.makeText(ViewFoodItemList.this,"Item Name :"+data[position],Toast.LENGTH_LONG).show();
                    //Write here put extra code and intent

                  //  String itemName=data[position];
                   // String itemPrice=data1[position];
                    Intent i=new Intent(ViewFoodItemList.this,mycart.class);
                    i.putExtra("ItemName",data[position]);
                    i.putExtra("ItemPrice",data1[position]);

                    startActivity(i);

                }
            });



        }catch (Exception e) {

            Toast.makeText(ViewFoodItemList.this,""+e,Toast.LENGTH_LONG).show();

        }

    }

}

