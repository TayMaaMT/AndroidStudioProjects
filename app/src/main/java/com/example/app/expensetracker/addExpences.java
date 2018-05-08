package com.example.app.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static cn.pedant.SweetAlert.SweetAlertDialog.ERROR_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;

public class addExpences extends AppCompatActivity {

    Db_Budget db = new Db_Budget(this);
    //ListView lst ;
    EditText expenseName, expenseAmount;
    Spinner CatName;
    String n1;
    private ArrayList<Category> categories;
    Category category;
    int pos ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expences);
        CatName = (Spinner) (findViewById(R.id.EditText_CatName));
        expenseName = (EditText) (findViewById(R.id.EditText_expenseName));
        expenseAmount = (EditText) (findViewById(R.id.EditText_expenseAmot));

        ArrayList<String> listData = new ArrayList<>();
        listData.add("Select category");
        categories = db.getCatagoryList2();

        for (int i = 0; i < categories.size(); i++) {
            listData.add(categories.get(i).getName());
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                else
                    return true;

            }
        };
        CatName.setAdapter(arrayAdapter);

        CatName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                n1 = arrayAdapter.getItem(position);
                pos =position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void canceladdexpences(View view) {
       finish();
    }

    public void expenseSave(View view) {
        if (TextUtils.isEmpty(expenseName.getText())) {
            expenseName.setError("add expense ");
            return;
        }
        if (TextUtils.isEmpty(expenseAmount.getText())) {
            expenseAmount.setError("add expense ");
            return;
        }
        if (!checkSalary(Double.parseDouble(expenseAmount.getText().toString()))) {
            Utils.Toast(this,"SALARY","خلص الراتب !! !!",ERROR_TYPE);
            return;
        }

        String n2 = expenseName.getText().toString();
        int n3 = Integer.parseInt(expenseAmount.getText().toString());
        boolean result = db.insertExpensesData(n2, n3, n1);

        if (result) {
            Utils.Toast(this,"Add ","Added successfully !!",SUCCESS_TYPE);
            Intent intent = new Intent(addExpences.this, Expences.class);
            startActivity(intent);
            finish();
//            CatName.setText("");
            expenseName.setText("");
            expenseAmount.setText("");
        } else

            Toast.makeText(addExpences.this, "NO", Toast.LENGTH_SHORT).show();


    }

    boolean checkSalary(double expnse) {
        category = categories.get(pos-1);

        double salary = new Db_Budget(this).SalaryDB().getSalary();
        if (category != null) {
            if (expnse != 0) {
                double exPercent = category.getPercent() * salary/100;
                if (exPercent >= expnse)
                    return true;

            }
        } else {
            Utils.Toast(this,"category failed","choose category !!",ERROR_TYPE);
        }

        return false;
    }

}
