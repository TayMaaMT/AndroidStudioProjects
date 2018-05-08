package com.example.app.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Db_Budget extends SQLiteOpenHelper {

    public static final String db_budget = "Budget.db";


    public Db_Budget(Context context) {
        super(context, db_budget, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table salary (id INTEGER PRIMARY KEY AUTOINCREMENT  ,salaryAmount DOUBLE ,dayOfReset INTEGER,monthOfReset INTEGER,yearOfReset INTEGER)");
        db.execSQL("create table expenses (id INTEGER PRIMARY KEY AUTOINCREMENT , expensesName TEXT , expensesAmount INTEGER ,catagoryName TEXT )");//ناكدي بدكو تحطو الكاتجوري ولا لاء
        db.execSQL("create table catagory (id INTEGER PRIMARY KEY AUTOINCREMENT , catagoryName TEXT ,catagoryPercentage DOUBLE )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS salary");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS catagory");
        onCreate(db);

    }

    public void deletCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS salary");
        db.execSQL("create table salary (id INTEGER PRIMARY KEY AUTOINCREMENT  ,salaryAmount DOUBLE ,dayOfReset INTEGER,monthOfReset INTEGER,yearOfReset INTEGER)");

    }


    public boolean insertSalaryData(double EditText_salayAmot, int EditText_dayOfReset, int EditText_monthOfReset, int EditText_yearOfReset) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue1 = new ContentValues();
        contentvalue1.put("salaryAmount", EditText_salayAmot);
        contentvalue1.put("dayOfReset", EditText_dayOfReset);
        contentvalue1.put("monthOfReset", EditText_monthOfReset);
        contentvalue1.put("yearOfReset", EditText_yearOfReset);

        long result1 = db.insert("salary", null, contentvalue1);
        if (result1 == -1)
            return false;
        return true;

    }

    public boolean updateSalaryData(double EditText_salayAmot, int EditText_dayOfReset, int EditText_monthOfReset, int EditText_yearOfReset) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue1 = new ContentValues();
        contentvalue1.put("salaryAmount", EditText_salayAmot);
        contentvalue1.put("dayOfReset", EditText_dayOfReset);
        contentvalue1.put("monthOfReset", EditText_monthOfReset);
        contentvalue1.put("yearOfReset", EditText_yearOfReset);

        long result1 = db.update("salary", contentvalue1, null, null);
        if (result1 == -1)
            return false;
        return true;

    }

    public boolean insertExpensesData(String EditText_expenseName, int EditText_expenseAmot, String EditText_catName) {
        ContentValues contentvalue2 = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentvalue2.put("expensesName", EditText_expenseName);
        contentvalue2.put("expensesAmount", EditText_expenseAmot);
        contentvalue2.put("catagoryName", EditText_catName);
        long result2 = db.insert("expenses", null, contentvalue2);
        if (result2 == -1)
            return false;
        return true;
    }

    public boolean insertCatagoryData(String catagoryName, double catpercent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue3 = new ContentValues();
        contentvalue3.put("catagoryName", catagoryName);
        contentvalue3.put("catagoryPercentage", catpercent);
        long result3 = db.insert("catagory", null, contentvalue3);
        if (result3 == -1)
            return false;
        return true;

    }

    public ArrayList getExpensesRecord() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Expenses", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(1);//exname
            String t2 = res.getString(2);//examount
            String t3 = res.getString(3);//catagname

            arrayList.add("\n" + t3 + "\n" + "\n" + "\n" + t1 + "                                                   $ " + t2);
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getCatagoryList() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from catagory", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(res.getColumnIndex("catagoryName"));//Catname
            String t2 = res.getString(res.getColumnIndex("catagoryPercentage"));


            arrayList.add("\n" + t1 + "\n" + "                                                    " + t2 + " % ");
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<Category> getCatagoryList2() {
        ArrayList<Category> arrayList = new ArrayList();
        Category category = new Category();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from catagory", null);
        while (res.moveToNext()) {
            category = new Category();
            category.setName(res.getString(res.getColumnIndex("catagoryName")));//Catname
            category.setId(res.getInt(res.getColumnIndex("id")));
            category.setPercent(res.getDouble(res.getColumnIndex("catagoryPercentage")));
            arrayList.add(category);
        }
        return arrayList;
    }

    public Salary SalaryDB() {
        double sal = 0.0;
        Salary salary = new Salary();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from salary", null);
        res.moveToFirst();
        if (res.getCount() == 0)
            return salary;
        Double t1 = res.getDouble(res.getColumnIndex("salaryAmount"));
        while (res.moveToNext()) {
            double salary_amount = res.getDouble(res.getColumnIndex("salaryAmount"));
            int day = res.getInt(res.getColumnIndex("dayOfReset"));
            int month = res.getInt(res.getColumnIndex("monthOfReset"));
            int year = res.getInt(res.getColumnIndex("yearOfReset"));
            int id = res.getInt(res.getColumnIndex("id"));

            salary.setDay(day);
            salary.setId(id);
            salary.setMonth(month);
            salary.setYear(year);
            salary.setSalary(salary_amount);

            Log.d("print_salary", t1 + "");
        }
        sal = t1;

        return salary;
    }

    public Calendar getDate() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from salary", null);
        res.moveToFirst();
        if (res.getCount() == 0)
            return null;
        int day = res.getInt(res.getColumnIndex("dayOfReset"));
        int month = res.getInt(res.getColumnIndex("monthOfReset"));
        int year = res.getInt(res.getColumnIndex("yearOfReset"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }

    public double getSumExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        int sum = 0;
        Cursor res = db.rawQuery("select * from expenses", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(res.getColumnIndex("expensesAmount"));
            sum += Integer.parseInt(t1);
            res.moveToNext();
        }
        return sum;
    }

    public String showDate() {
        String Rdate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from salary", null);
        res.moveToFirst();
        int day;
        int month;
        int year;

        while (res.moveToNext()) {
            day = res.getInt(res.getColumnIndex("dayOfReset"));
            month = res.getInt(res.getColumnIndex("monthOfReset"));
            year = res.getInt(res.getColumnIndex("yearOfReset"));

            int id = res.getInt((res.getColumnIndex("id")));
            Log.d("myid", id + "");
            Rdate += day + "/" + month + "/" + year;
        }
        return Rdate;


    }


}


