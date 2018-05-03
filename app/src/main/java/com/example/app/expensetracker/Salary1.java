package com.example.app.expensetracker;

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

public class Salary1 extends AppCompatActivity {

    EditText salayAmot;
    EditText dayOfReset;
    EditText monthOfReset;
    EditText yearOfReset;
    Db_Budget db = new Db_Budget(this);
    TextView salatxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary1);
        dayOfReset = (EditText) (findViewById(R.id.EditText_dayOfReset));
        salayAmot = (EditText) (findViewById(R.id.EditText_salayAmot));
        monthOfReset = (EditText) (findViewById(R.id.EditText_monthOfReset));
        yearOfReset = (EditText) (findViewById(R.id.EditText_yearOfReset));
        salatxt = (TextView) (findViewById(R.id.textView_Salary));

//        if()


        Calendar resetdate = new Db_Budget(this).getDate();
        Calendar dateNow = Calendar.getInstance();
        dateNow.setTime(new Date());
        if (resetdate != null) {

            if ((dateNow.get(Calendar.DAY_OF_MONTH) == resetdate.get(Calendar.DAY_OF_MONTH))
                    && (dateNow.get(Calendar.MONTH) + 1 == resetdate.get(Calendar.MONTH))
                    && (dateNow.get(Calendar.YEAR) == resetdate.get(Calendar.YEAR))) {
                new Db_Budget(this).deletCategory();
            }
        }

        salatxt.setText(new Db_Budget(this).SalaryDB() + "");

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

       /* if (id == R.id.Sal_ary) {
            Intent intent = new Intent(Salary1.this ,Salary1.class );
            startActivity(intent);
        }*/

        if (id == R.id.Cata_gory) {
            Intent intent = new Intent(Salary1.this, Catagory.class);
            startActivity(intent);
        }


        if (id == R.id.Hist_ory) {
            // Intent intent = new Intent(Salary1.this ,History.class );
            // startActivity(intent);
        }

        if (id == R.id.Notifica_tion) {
            //Intent intent = new Intent(Salary1.this ,Notification.class );
            // startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void date(View view) {
        Intent intent = new Intent(Salary1.this, addDate.class);
        startActivity(intent);
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

        //  salatxt.setText(String.valueOf(db.SalaryDB()));

        double n1 = Double.parseDouble(salayAmot.getText().toString());
        int n2 = Integer.parseInt(dayOfReset.getText().toString());
        int n3 = Integer.parseInt(monthOfReset.getText().toString());
        int n4 = Integer.parseInt(yearOfReset.getText().toString());

        boolean result = db.insertSalaryData(n1, n2, n3, n4);
        if (result) {
            Toast.makeText(Salary1.this, "OK", Toast.LENGTH_SHORT).show();
            salayAmot.setText("");
            dayOfReset.setText("");
            salatxt.setText(n1 + "");

        } else
            Toast.makeText(Salary1.this, "NO", Toast.LENGTH_SHORT).show();

    }
   /* public void showSalaryData(){


    }
*/

}

