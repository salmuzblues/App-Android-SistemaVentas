package com.example.root.myapppedidodelfarma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Invitado1 on 03/12/2017.
 */

public class ProductosSQLiteOpenHelper extends SQLiteOpenHelper
{ // var
    private String sqlCreate = "create table producto(id integer primary key, nombre text, precio text, porc text) ";
    private String sqlDrop = "drop table if exists producto";

    public ProductosSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate) ;
    }
}
