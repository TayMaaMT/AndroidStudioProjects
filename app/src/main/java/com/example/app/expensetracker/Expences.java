package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Expences extends AppCompatActivity {
    Db_Budget db =new Db_Budget(this);
    ListView lst ;
private addExpences addE =new addExpences();
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expences);
       lst =(ListView)(findViewById(R.id.list_Expenses));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.Expn_ces) {
            Toast.makeText(this, "Expences", Toast.LENGTH_SHORT).show();
        }*/

        if (id == R.id.Sal_ary) {
            Intent intent = new Intent(Expences.this , Salary1.class );
            startActivity(intent);
        }

        if (id == R.id.Cata_gory) {
            Intent intent = new Intent(Expences.this , Catagory.class );
            startActivity(intent);
        }


        if (id == R.id.Hist_ory) {
            //Intent intent = new Intent(Expences.this , History.class );
            // startActivity(intent);
        }

        if (id == R.id.Notifica_tion) {
            // Intent intent = new Intent(Expences.this , Notifiction.class );
            //startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void addExpen(View view) {
        Intent intent = new Intent(Expences.this , addExpences.class );
        startActivity(intent);
    }








    public void btn_day(View view) {
        Intent intent = new Intent(Expences.this , Catagory.class );
        startActivity(intent);

    }


    public void allPeriod(View view) {

       showData();
    }


    public void showData(){
        ArrayList<String> listData = db.getExpensesRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this ,android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);

    }



}
