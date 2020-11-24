package com.androidjson.sqlitelogin_androidjsoncom.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public abstract class DatabaseManager {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx){
        helper = new DatabaseHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void email(){
        db.close();
    }

    abstract public void eliminate(String id);
    abstract public void eliminateall();
    abstract public Cursor cargarCursor();
    abstract boolean checkregister(String id);

    public DatabaseHelper getHelper() {
        return helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setHelper(DatabaseHelper helper) {
        this.helper = helper;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

}

