package com.example.registr;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "users.db";
    public DBHelper(Context context) {
        super(context,"users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(mail TEXT primary key, password TEXT, name TEXT, surname TEXT, middle_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean insertData(String mail, String password, String name, String surname, String middle_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mail", mail);
        values.put("password", password);
        values.put("name", name);
        values.put("surname", surname);
        values.put("middle_name", middle_name);

        long result = db.insert("users", null, values);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkLogin(String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where mail=?", new String[] {mail});
        if (cursor.getCount()>0) {
            return true;
        }
        else{
            return  false;
        }
    }

    public Boolean checkUserPassword(String mail, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where mail=? and password=?", new String[] {mail,password});
        if (cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }
}

