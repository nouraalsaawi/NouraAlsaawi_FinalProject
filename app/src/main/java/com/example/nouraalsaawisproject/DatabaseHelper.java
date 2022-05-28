package com.example.nouraalsaawisproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "student";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FATHERNAME = "fatherName";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NATID = "natID";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_POSITION + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        COLUMN_ID + " TEXT NOT NULL," +
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_FATHERNAME + " TEXT NOT NULL," +
                        COLUMN_SURNAME + " TEXT NOT NULL," +
                        COLUMN_NATID + " TEXT NOT NULL," +
                        COLUMN_DOB + " TEXT NOT NULL," +
                        COLUMN_GENDER + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor ViewList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return x;
    }

    //I ADDED A METHOD TO MAKE THINGS EASIER FOR MYSELF
    public Cursor ViewListDesc(int getID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT descr FROM " + TABLE_NAME + " WHERE id = " + getID, null);
        return x;
    }

    public void addStudent(String id, String name, String fatherName, String surname, String natID, String dob, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_FATHERNAME, fatherName);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_NATID, natID);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);

        db.insert(TABLE_NAME, null, values);
    }

    public boolean updateID(String id, String id_input) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET id = " + "'" + id_input + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateName(String id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET name = " + "'" + name + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateFatherName(String id, String fatherName) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET fatherName = " + "'" + fatherName + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateSurname(String id, String surname) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET surname = " + "'" + surname + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateNatID(String id, String natID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET natID = " + "'" + natID + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateDoB(String id, String dob) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET dob = " + "'" + dob + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public boolean updateGender(String id, String gen) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET gender = " + "'" + gen + "' " + "WHERE id = " + "'" + id + "'");
        return true;
    }

    public Integer deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{id});
    }
}