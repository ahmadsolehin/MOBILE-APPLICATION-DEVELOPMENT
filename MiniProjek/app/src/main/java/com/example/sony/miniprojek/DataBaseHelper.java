package com.example.sony.miniprojek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sony on 11/29/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String DATABASE_TABLE = "mainTable";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "TIME";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + DATABASE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT , DESCRIPTION TEXT, DATE TEXT , TIME TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String a , String b , String c, String d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ctx = new ContentValues();
        ctx.put(COL_2 , a);
        ctx.put(COL_3 , b);
        ctx.put(COL_4 , c);
        ctx.put(COL_5 , d);
        long result = db.insert(DATABASE_TABLE , null , ctx);

        if (result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DATABASE_TABLE,null);
        return res;
    }


    public Cursor getoneData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM mainTable WHERE TRIM(NAME) = '"+name.trim()+"'", null);
        return res;
    }

    public boolean updateData(String id, String name, String description, String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ctx = new ContentValues();

        ctx.put(COL_1 , id);
        ctx.put(COL_2 , name);
        ctx.put(COL_3 , description);
        ctx.put(COL_4 , date);
        ctx.put(COL_5 , time);
        db.update(DATABASE_TABLE , ctx , "ID = ?", new String [] { id });
        return true;
    }

        // Delete a row from the database, by rowId (primary key)
    public Integer deleteRow(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(DATABASE_TABLE, "NAME = ?", new String[] { name });
    }
    

}
