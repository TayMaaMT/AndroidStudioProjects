package com.example.app.expensetracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Expences extends AppCompatActivity {
    Db_Budget db = new Db_Budget(this);
    ListView lst;
    TextView DisplayDate;
    int yy;
    int mm;
    int dd;
    TextView Remainder;
    TextView Total;

    private addExpences addE = new addExpences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expences);
        lst = (ListView) (findViewById(R.id.list_Expenses));
        showData();
        DisplayDate = (TextView) findViewById(R.id.Date);
        Remainder = findViewById(R.id.textView_Remainder);
        double remaider = (new Db_Budget(this).SalaryDB().getSalary()) - (new Db_Budget(this).getSumExpenses());
        Remainder.setText(String.valueOf(remaider));
        double total = (new Db_Budget(this).getSumExpenses());
        Total = findViewById(R.id.textView_Total);
        Total.setText(String.valueOf(total));

        if(remaider<=0){
            showNotification();
        }
// this is for update the Date
        final Calendar c = Calendar.getInstance();
        yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        dd = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        DisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(dd).append("/").append(mm + 1).append("/")
                .append(yy));


        new CountDownTimer(1000 * 60 * 3, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                showData();

            }

            @Override
            public void onFinish() {
                Toast.makeText(Expences.this, "finish", Toast.LENGTH_SHORT).show();
                DeletData();

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.Expn_ces) {
            Toast.makeText(this, "Expences", Toast.LENGTH_SHORT).show();
        }*/

        if (id == R.id.Sal_ary) {
            Intent intent = new Intent(Expences.this, Salary1.class);
            startActivity(intent);
        }

        if (id == R.id.Cata_gory) {
            Intent intent = new Intent(Expences.this, Catagory.class);
            startActivity(intent);
        }


        if (id == R.id.Hist_ory) {
            Intent intent = new Intent(Expences.this, History.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    public void addExpen(View view) {
        Intent intent = new Intent(Expences.this, addExpences.class);
        startActivity(intent);
    }

    public void showData() {
        ArrayList<String> listData = db.getExpensesRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        lst.setAdapter(arrayAdapter);
        //lst.setBackgroundColor(Color.parseColor("#ffffff"));


    }

    public void DeletData() {
        ArrayList<String> listData = db.getExpensesRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        arrayAdapter.clear();
        lst.setAdapter(arrayAdapter);

    }

    void showNotification() {
        int NOTIFICATION_ID = 1;
        final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("لقد تجاوزت الرصيد الموجود")
                .setContentText("لقد استهلكت الراتب كله انت في خطر ");

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    @Override
    protected void onResume() {
        super.onResume();
        double remaider = (new Db_Budget(this).SalaryDB().getSalary()) - (new Db_Budget(this).getSumExpenses());
        Remainder.setText(String.valueOf(remaider));
        double total = (new Db_Budget(this).getSumExpenses());
        Total.setText(String.valueOf(total));

    }
}



