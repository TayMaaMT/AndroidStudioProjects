package com.example.app.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static cn.pedant.SweetAlert.SweetAlertDialog.ERROR_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;

public class Salary1 extends AppCompatActivity {

    EditText salayAmot;
    EditText dayOfReset;
    EditText monthOfReset;
    EditText yearOfReset;
    Db_Budget db = new Db_Budget(this);
    TextView salatxt;
   // TextView Remainder;
    TextView restDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Salary salary = new Db_Budget(this).SalaryDB() ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary1);
        dayOfReset = (EditText) (findViewById(R.id.EditText_dayOfReset));
        salayAmot = (EditText) (findViewById(R.id.EditText_salayAmot));
        monthOfReset = (EditText) (findViewById(R.id.EditText_monthOfReset));
        yearOfReset = (EditText) (findViewById(R.id.EditText_yearOfReset));
        salatxt = (TextView) (findViewById(R.id.textView_Salary));
       // Remainder =findViewById(R.id.textView_Remainder);
        restDate =findViewById(R.id.textView_dateOfReset);




        Calendar resetdate = new Db_Budget(this).getDate();
        Calendar dateNow = Calendar.getInstance();
        dateNow.setTime(new Date());
        if (resetdate != null) {

            if ((dateNow.get(Calendar.DAY_OF_MONTH) == salary.getDay())
                    && (dateNow.get(Calendar.MONTH) + 1 == salary.getMonth())
                    && (dateNow.get(Calendar.YEAR) == salary.getYear())) {
                new Db_Budget(this).deletCategory();
            }
        }


        salatxt.setText(salary.getSalary()+"");
        restDate.setText(salary.getDay()+"/"+salary.getMonth()+"/"+salary.getYear()+"");




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
            Intent intent = new Intent(Salary1.this, Expences.class);
            startActivity(intent);
        }


        if (id == R.id.Cata_gory) {
            Intent intent = new Intent(Salary1.this, Catagory.class);
            startActivity(intent);
        }


        if (id == R.id.Hist_ory) {
             Intent intent = new Intent(Salary1.this ,History.class );
             startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }



    public void SaveSalary(View view) {
        if (TextUtils.isEmpty(salayAmot.getText())) {
            salayAmot.setError("add total price ");
            return;
        }
        if (TextUtils.isEmpty(yearOfReset.getText())) {
            yearOfReset.setError("add year ");
            return;
        }
        if (TextUtils.isEmpty(monthOfReset.getText())) {
            monthOfReset.setError("add month ");
            return;
        }
        if (TextUtils.isEmpty(dayOfReset.getText())) {
            dayOfReset.setError("add day ");
            return;
        }

        double n1 = Double.parseDouble(salayAmot.getText().toString());
        int n2 = Integer.parseInt(dayOfReset.getText().toString());
        int n3 = Integer.parseInt(monthOfReset.getText().toString());
        int n4 = Integer.parseInt(yearOfReset.getText().toString());
//        if(new Db_Budget(this).SalaryDB()==0.0){
            boolean result = db.insertSalaryData(n1, n2, n3, n4);
//
//        }else{
//
//        }

        if (result) {
         Utils.Toast(this,"Add new salary","Added successfully !!",SUCCESS_TYPE);
            salayAmot.setText("");
            dayOfReset.setText("");
            monthOfReset.setText("");
            yearOfReset.setText("");
            salatxt.setText(n1 + "");
            Salary salary = new Db_Budget(this).SalaryDB() ;
            salatxt.setText(salary.getSalary()+"");
            restDate.setText(salary.getDay()+"/"+salary.getMonth()+"/"+salary.getYear()+"");


        } else
            System.out.println("");
         //   Utils.Toast(this,"Add new salary","There is a problem try agin",ERROR_TYPE);

    }

}

