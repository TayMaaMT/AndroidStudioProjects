package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addCatagory extends AppCompatActivity {

    private EditText addCat, addPercent;
   // public ListView lstCatagory;
    Db_Budget db = new Db_Budget(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catagory);

        addCat = (EditText) (findViewById(R.id.EditText_addCat));
        addPercent = (EditText) (findViewById(R.id.EditText_percent));

       // lstCatagory = (ListView) (findViewById(R.id.ListView_Cats));

    }

    public void cancleaddcatagory(View view) {
        Intent intent = new Intent(addCatagory.this, Catagory.class);
        startActivity(intent);
    }



        public void savectagory(View view){

            String n1 = addCat.getText().toString();
            double n2 = Double.parseDouble(addPercent.getText().toString());

            boolean result = db.insertCatagoryData(n1, n2);
            if (result) {
                Toast.makeText(addCatagory.this, "OK", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addCatagory.this, Catagory.class);
                startActivity(intent);
                addCat.setText("");
                addPercent.setText("");

            } else
                Toast.makeText(addCatagory.this, "NO", Toast.LENGTH_SHORT).show();

        }



}






