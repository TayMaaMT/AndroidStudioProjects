package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Catagory extends AppCompatActivity {

    Db_Budget db = new Db_Budget(this);
    public ListView lstCatagory;

 private  addCatagory cat =new addCatagory();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        lstCatagory = (ListView) (findViewById(R.id.ListView_Cats));
        showCatoagoryList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Expn_ces) {
            Intent intent = new Intent(Catagory.this, Expences.class);
            startActivity(intent);

        }

        if (id == R.id.Sal_ary) {
            Intent intent = new Intent(Catagory.this, Salary1.class);
            startActivity(intent);

        }

        // if (id == R.id.Cata_gory) {

      //  }


        if (id == R.id.Hist_ory) {
            Intent intent = new Intent(Catagory.this ,History.class );
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }


    public void addcaragory(View view) {
        Intent intent = new Intent(Catagory.this, addCatagory.class);
        startActivity(intent);
    }
    public void showCatoagoryList() {
        ArrayList<String> listData = db.getCatagoryList();
         arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        lstCatagory.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> listData = db.getCatagoryList();
        arrayAdapter.clear();
        arrayAdapter.addAll(listData);
        arrayAdapter.notifyDataSetChanged();
    }
}