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

import static cn.pedant.SweetAlert.SweetAlertDialog.ERROR_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;

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
        finish();
    }


    public void savectagory(View view) {


        String n1 = addCat.getText().toString();
        double n2 = Double.parseDouble(addPercent.getText().toString());

        if(!checkPercent(n2)){
            Utils.Toast(this,"failed","التصنيفات تجاوزت 100% !!",ERROR_TYPE);

        }else {

            boolean result = db.insertCatagoryData(n1, n2);
            if (result) {
                Utils.Toast(this, new Runnable() {
                    @Override
                    public void run() {
                        finish();

                    }
                }, "Add", "Added successfully !!", SUCCESS_TYPE);

                addCat.setText("");
                addPercent.setText("");

            } else
                Toast.makeText(addCatagory.this, "NO", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPercent(double newCategory) {
        ArrayList<Category> list = new Db_Budget(this).getCatagoryList2();

        double sum = 0.0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getPercent();
        }
        if (sum < 100)
            return true;
        return false;
    }


}






