package com.androidjson.sqlitelogin_androidjsoncom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.androidjson.sqlitelogin_androidjsoncom.entity.User;

import java.util.ArrayList;
import java.util.List;
public class DatabaseManagerUser extends DatabaseManager {

    private static final String NAME_TABLE = "demo";
    private static final String CN_ID = "_id";
    private static final String E_MAIL = "email";
    private static final String PASSWORD = "password";
    private static final String IMAGE = "image";
    private static final String NAME = "name";

    public static final String CREATE_TABLE = "create table " + NAME_TABLE + " ("
            + CN_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + E_MAIL + " text NULL, "
            + PASSWORD + " text NULL, "
            + IMAGE + " BLOB NULL, "
            + NAME + " text NOT NULL "
            + ");";

    public DatabaseManagerUser(Context ctx) {
        super(ctx);
    }

    @Override
    public void email() {
        super.getDb().close();
    }

    private ContentValues generarContentValues(String id, String email, String password, byte[] image, String name){
        ContentValues values = new ContentValues();
        values.put(CN_ID, id);
        values.put(E_MAIL, email);
        values.put(PASSWORD, password);
        values.put(IMAGE, image);
        values.put(NAME, name);

        return values;
    }


    public void insert_parameters(String id, String email, String password, byte[] image, String name) {
        Log.d("User_Insert", super.getDb().insert(NAME_TABLE,null,generarContentValues(id, email, password, image, name))+"");
    }

    public void update_parameters(String id, String email, String pass, byte[] image, String name) {

        ContentValues values = new ContentValues();
        values.put(CN_ID, id);
        values.put(E_MAIL, email);
        values.put(PASSWORD, pass);
        values.put(IMAGE, image);
        values.put(NAME, name);

        String[] args = new String[]{id};

        Log.d("update", super.getDb().update(NAME_TABLE, values,"_ID=?", args)+"");
    }

    @Override
    public void eliminate(String id) {

        super.getDb().delete(NAME_TABLE, CN_ID +"=?", new String[]{id});
    }

    @Override
    public void eliminateall() {

        super.getDb().execSQL("DELETE FROM "+ NAME_TABLE+";");
    }

    @Override
    public Cursor cargarCursor() {

        String[] columnas = new String[]{CN_ID, E_MAIL, PASSWORD, IMAGE, NAME};

        return super.getDb().query(NAME_TABLE, columnas, null, null, null, null, null);
    }

    @Override
    public boolean checkregister(String email) {

        boolean esta;
        Cursor resultSet = super.getDb().rawQuery("SELECT email FROM demo" + " WHERE email='"+email+"'", null);

        if(resultSet.getCount()<=0){
            esta = false;
        }else{
            esta = true;
        }
        return esta;
    }

    public List<User> getUserList(){

        List<User> list = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()){
            User user = new User();

            user.setId(c.getString(0));
            user.setemail(c.getString(1));
            user.setPassword(c.getString(2));
            user.setBytes(c.getBlob(3));
            user.setName(c.getString(4));
            list.add(user);
        }

        return list;
    }

    public User getUser(String ident){

        Cursor c1 = super.getDb().rawQuery("SELECT _id, email, password, image, name FROM demo WHERE email" + "='" + ident+ "'", null);

        User user = new User();

        c1.moveToNext();

        user.setId(c1.getString(0));
        user.setemail(c1.getString(1));
        user.setPassword(c1.getString(2));
        user.setBytes(c1.getBlob(3));
        user.setName(c1.getString(4));
        return user;
    }
}
