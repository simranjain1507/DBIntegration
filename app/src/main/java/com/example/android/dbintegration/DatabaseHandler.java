package com.example.android.dbintegration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simranjain1507 on 03/06/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASEVERSION=2;
    private static final String DATABASE_NAME="persondetails1.db";
    String TABLE="person";
    String KEY_ID="id";
    String KEY_NAME="name";
    String KEY_EMAIL="email";
    String KEY_PHNO="phonenumber";
    String KEY_GENDER="gender";
    String KEY_AREA="area";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PERSON_TABLE="CREATE TABLE " + TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
                KEY_EMAIL + " TEXT," + KEY_PHNO + " LONG," + KEY_GENDER + " TEXT" + ")";
                db.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
//        onCreate(db);
        if(newVersion>oldVersion) {
            db.execSQL("ALTER TABLE " + TABLE + " ADD COLUMN " + KEY_AREA + " TEXT;");
        }
        //onCreate(db);

    }

    void addDetails(PersonDetails personDetails){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, personDetails.getName());
        values.put(KEY_EMAIL, personDetails.getEmail());
        values.put(KEY_PHNO, personDetails.getPhone());
        values.put(KEY_GENDER, personDetails.getGender());
        values.put(KEY_AREA, personDetails.getArea());
        db.insert(TABLE, null, values);
        db.close();
    }

    public PersonDetails getPersonDetails(int id)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE, new String[]{ KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PHNO, KEY_GENDER, KEY_AREA}, KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        PersonDetails personDetails=new PersonDetails(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return  personDetails;
    }

    public List<PersonDetails> getAllDetails(){
        List<PersonDetails> personDetailsList=new ArrayList<PersonDetails>();
        String selectQuery="SELECT * FROM " + TABLE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                PersonDetails personDetails=new PersonDetails();
                personDetails.setId(Integer.parseInt((cursor.getString(0))));
                personDetails.setName((cursor.getString(1)));
                personDetails.setEmail(cursor.getString(2));
                personDetails.setPhone(cursor.getString(3));
                personDetails.setGender(cursor.getString(4));
                personDetails.setArea(cursor.getString(5));
                personDetailsList.add(personDetails);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return personDetailsList;
    }

    public int updateDetails(PersonDetails personDetails, int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values1=new ContentValues();
        values1.put(KEY_NAME, personDetails.getName());
        values1.put(KEY_EMAIL, personDetails.getEmail());
        values1.put(KEY_PHNO, personDetails.getPhone());
        values1.put(KEY_GENDER, personDetails.getGender());
        values1.put(KEY_AREA, personDetails.getArea());

        return db.update(TABLE, values1, KEY_ID +"="+id, null);
    }
    public void delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + "=" + id, null);
    }

}
