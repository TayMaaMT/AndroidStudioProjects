package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class addDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
    }


    public void cancleadddate(View view) {
        Intent intent = new Intent(addDate.this ,Salary1.class );
        startActivity(intent);
    }
}
