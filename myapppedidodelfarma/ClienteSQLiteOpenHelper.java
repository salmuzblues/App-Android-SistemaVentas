package com.example.root.myapppedidodelfarma;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Invitado1 on 03/12/2017.
 */

public class ClienteSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sqlCreate="create table cliente (id integer primary key,nombre text, direccion text, distrito text, ruc text)";
    private String sqlDrop="dro table if exists cliente";

    public ClienteSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Constructor
    public ClienteSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate);
    }
}
