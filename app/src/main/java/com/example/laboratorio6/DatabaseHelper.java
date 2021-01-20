package com.example.laboratorio6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="Usuarios.db";
    public static final String TABLE_NAME="tabla_registro_usuarios";
    public static final String COL_1="ID";
    public static final String COL_2="NOMBRECOMPLETO";
    public static final String COL_3="USUARIO";
    public static final String COL_4="CORREO";
    public static final String COL_5="CONTRASENA";
    public static final String COL_6="GENERO";

    public static final int DATABASE_VERSION =1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NOMBRECOMPLETO TEXT, USUARIO TEXT, CORREO TEXT, CONTRASENA TEXT, GENERO INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void initData(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1,1);
    }

    public boolean insertData(Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, u.getNombreCompleto());
        contentValues.put(COL_3, u.getUsuario());
        contentValues.put(COL_4, u.getCorreo());
        contentValues.put(COL_5, u.getContraseña());
        contentValues.put(COL_6, u.getGenero());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
    }

    public Cursor findDataByUser(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE " + COL_3+ " = '"+ usuario+"' " , null);
    }
}
