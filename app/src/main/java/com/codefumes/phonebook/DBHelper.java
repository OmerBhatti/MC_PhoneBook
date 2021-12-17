package com.codefumes.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CONTACT_NAME = "Name";
    public static final String CONTACT_PHONE = "Phone";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_ID = "ContactID";
    public static final String CONTACT_TABLE = "Contacts";

    public DBHelper(@Nullable Context context) {
        super(context, "Contacts.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSTatement = "CREATE TABLE " + CONTACT_TABLE + "(" + CONTACT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + CONTACT_NAME + " Text, " + CONTACT_PHONE + " Text, " + CONTACT_EMAIL + " TEXT) ";
        db.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME, contact.getName());
        cv.put(CONTACT_EMAIL, contact.getEmail());
        cv.put(CONTACT_PHONE, contact.getPhoneNumber());
        db.insert(CONTACT_TABLE, null, cv);
        db.close();
    }

    public void updateActivity(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME,contact.getName());
        cv.put(CONTACT_EMAIL,contact.getEmail());
        cv.put(CONTACT_PHONE,contact.getPhoneNumber());
        db.update(CONTACT_TABLE,cv,CONTACT_ID+"= ?",new String[]{Integer.toString(contact.getId())});

    }

    public void removeContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACT_TABLE,CONTACT_ID+"= ?",new String[]{Integer.toString(id)});
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + CONTACT_TABLE + " WHERE "+ CONTACT_ID +" = "+Integer.toString(id), null);

        cursorCourses.moveToFirst();
        Contact contact = new Contact(cursorCourses.getString(1),cursorCourses.getString(2),cursorCourses.getString(3));
        contact.setId(cursorCourses.getInt(0));

        cursorCourses.close();
        return contact;
    }

    public ArrayList<Contact> getAllContacts() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + CONTACT_TABLE, null);
        ArrayList<Contact> contacts = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                Contact contact = new Contact(cursorCourses.getString(1),cursorCourses.getString(2),cursorCourses.getString(3));
                contact.setId(cursorCourses.getInt(0));
                contacts.add(contact);
            } while (cursorCourses.moveToNext());
        }

        cursorCourses.close();
        return contacts;
    }

}
