package com.example.dotlun.demosms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dotlun on 13/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "numberDb";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE = "create table "+DbContacts.TABLE_NAME+
            "(id integer primary key autoincrement,"+DbContacts.INCOMING_NUMBER+" text);";
    private static final String DROP_TABLE = "drop table if exists "+ DbContacts.TABLE_NAME;
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void saveNumber(String number,SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContacts.INCOMING_NUMBER,number);
        database.insert(DbContacts.TABLE_NAME,null,contentValues);
    }
    public Cursor readNumber(SQLiteDatabase database)
    {
        String[] projection = {"id",DbContacts.INCOMING_NUMBER};
        return (database.query(DbContacts.TABLE_NAME,projection,null,null,null,null,null));
    }
}
