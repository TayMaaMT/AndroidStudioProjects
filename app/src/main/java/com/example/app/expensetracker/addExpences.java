package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class addExpences extends AppCompatActivity {

    Db_Budget db =new Db_Budget(this);
    //ListView lst ;
    EditText expenseName,expenseAmount;
    Spinner CatName;
    String n1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expences);
        CatName =(Spinner)(findViewById(R.id.EditText_CatName));
        expenseName=(EditText)(findViewById(R.id. EditText_expenseName));
        expenseAmount=(EditText)(findViewById(R.id.EditText_expenseAmot));

        ArrayList<String> listData = new ArrayList<>();
        listData.add("Select category");
        listData.addAll(db.getCatagoryList2());
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listData){
            @Override
            public boolean isEnabled(int position) {
                if(position==0)
                    return false;
                else
                    return true;

            }
        };
        CatName.setAdapter(arrayAdapter);

        CatName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                n1=arrayAdapter.getItem(position);
                Log.d("array_item",n1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void canceladdexpences(View view) {
        Intent intent = new Intent(addExpences.this ,Expences.class );
        startActivity(intent);
    }

    public void expenseSave(View view) {
//        String n1 = CatName.getText().toString();

        String n2 = expenseName.getText().toString();
        int n3 = Integer.parseInt(expenseAmount.getText().toString());
        boolean result =  db.insertExpensesData( n2,  n3,  n1);
        if (result) {
            Toast.makeText(addExpences.this, "OK", Toast.LENGTH_SHORT).show();
//            CatName.setText("");
            expenseName.setText("");
            expenseAmount.setText("");
        }
        else
            Toast.makeText(addExpences.this, "NO", Toast.LENGTH_SHORT).show();

    }

}
