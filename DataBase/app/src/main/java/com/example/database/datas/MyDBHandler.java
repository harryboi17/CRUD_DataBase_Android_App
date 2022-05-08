package com.example.database.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.database.models.contacts;
import com.example.database.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                        + Params.KEY_ID + " INTEGER PRIMARY KEY, "
                        + Params.KEY_NAME + " TEXT, "
                        + Params.KEY_PHONE + " TEXT, " + Params.KEY_YEAR + " TEXT, " + Params.KEY_BRANCH + " TEXT" + ")";
        Log.d("DB_Harshit", "Query being run is :\n" + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddContact(contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());
        values.put(Params.KEY_YEAR, contact.getYear());
        values.put(Params.KEY_BRANCH, contact.getBranch());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("DB_Harshit", "Successfully inserted " + contact.getId() + " " + contact.getName());
        db.close();
    }

    public List<contacts> getAllContacts(){
        List<contacts> contactsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Generating query to read from DataBase
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor  = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                contacts contact = new contacts();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setYear(cursor.getString(3));
                contact.setBranch(cursor.getString(4));
                contactsList.add(contact);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return contactsList;
    }

    public int updateContact(contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());
        values.put(Params.KEY_YEAR, contact.getYear());
        values.put(Params.KEY_BRANCH, contact.getBranch());

        //Updating
        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContactbyID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContact(contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
}
