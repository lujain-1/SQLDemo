package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper  extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";

    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context,"customer db",null,1);
    }

    //this is called the first time a database is accessed. there should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT )";
        db.execSQL(createTableStatement);


    }
    //this is called if the database version number changes . it prevents previous users apps from breaking when you change the database design

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, CustomerModel.getAge());

         long insert=db.insert(CUSTOMER_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList = new ArrayList<>();
        // get data from database
        String queryString = "Select * from "+ CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            // loop through cursor results
            do{
                int customerID = cursor.getInt(0); // student ID
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);


                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge);
                returnList.add(newCustomer);
            }while (cursor.moveToNext());
        } else{
            // nothing happens. no one is added.
        }
        //close
        cursor.close();
        db.close();
        return returnList;
    }

}



