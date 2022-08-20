package ru.startandroid.listviewanddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Jadval malumotlari:
    // JADVAL YARATGANDA AGAR JADVAL NOMI IKKITA SO'ZDAN IBORAT BO'LSA ULARNI QO'SHIB YOZISH SHART BO'LMASA JADVAL YARAITISHDA XATOL BO'LADI (PROBEL QO'YGANIMIZ UCHUN)
    private static final String TABLE_NAME = "MahsulotlarJadvali";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";


    //
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    // jadval yaratildi
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "
                + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +" TEXT)" ;
        db.execSQL(createTable);
    }

    // bazani yangilash
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    // bazaga malumot qo'shish (ADD)
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase(); // bazaga ulanish
        ContentValues contentValues = new ContentValues(); // bazaga malumot olib borib keladigan obyekt
        contentValues.put(COL2, item); // malumot yuklatildi

        long result = db.insert(TABLE_NAME, null, contentValues); // bazaga malumot qo'shish holati olinadi

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // bazadagi tanlangan jadvalni malumotlarini qaytarish uchun (READ)
    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
