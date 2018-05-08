package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    Db_Budget db =new Db_Budget(this);
    ListView lst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lst =(ListView)(findViewById(R.id.List_history));
        showData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Expn_ces) {
            Intent intent = new Intent(History.this , Expences.class );
            startActivity(intent);
        }

        if (id == R.id.Sal_ary) {
            Intent intent = new Intent(History.this , Salary1.class );
            startActivity(intent);
        }

        if (id == R.id.Cata_gory) {
            Intent intent = new Intent(History.this , Catagory.class );
            startActivity(intent);
        }


        if (id == R.id.Hist_ory) {
            //Intent intent = new Intent(Expences.this , History.class );
            // startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
    public void showData(){
        ArrayList<String> listData = db.getExpensesRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this ,android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);

    }
}
