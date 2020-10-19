package com.edu.phonetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PhoneService {
    private DatabaseHelper dbHelper;
    public PhoneService(Context context){
        dbHelper=new DatabaseHelper(context);
    }
    public boolean login(String phonename,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from phone where phonename=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{phonename,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean register(Phone phone){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into phone(phonename,password) values(?,?)";
        Object obj[]={phone.getPhonename(),phone.getPassword()};
        sdb.execSQL(sql,obj);
        return true;
    }
}
