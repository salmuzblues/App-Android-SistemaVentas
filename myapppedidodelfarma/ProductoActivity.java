package com.example.root.myapppedidodelfarma;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoActivity extends AppCompatActivity {

    private EditText etCodigo, etNombre, etPrecio, etPorc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        etCodigo = (EditText)findViewById(R.id.etCodigo);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etPrecio = (EditText)findViewById(R.id.etPrecio);
        etPorc = (EditText)findViewById(R.id.etPorc);
    }

    public void grabar (View v)
    {
        try{
            // class instance
            ProductosSQLiteOpenHelper pro = new ProductosSQLiteOpenHelper(this, "bdPedido", null, 1 );
            SQLiteDatabase db = pro.getWritableDatabase();
            //create variables for retriving data
            String cod = etCodigo.getText().toString().trim();
            String nomb = etNombre.getText().toString().trim();
            String pre = etPrecio.getText().toString().trim();
            String porc = etPorc.getText().toString().trim();

            // create a container for my data

            ContentValues cv = new ContentValues();
            cv.put("id", cod);
            cv.put("nombre", nomb);
            cv.put("precio", pre);
            cv.put("porc", porc);
            db.insert("producto",null, cv);
            db.close();
            limpiar();
            Toast.makeText(this, "Se registro el producto", Toast.LENGTH_LONG).show();
        } catch (Exception ex){
        Toast.makeText(this, "Error a Grabar: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(){
        etCodigo.setText("");
        etNombre.setText("");
        etPrecio.setText("");
        etPorc.setText("");
        etCodigo.requestFocus();
    }

    public void consultar (View v)
    {
        try{
            ProductosSQLiteOpenHelper pro = new ProductosSQLiteOpenHelper(this, "bdPedido",  null, 1);
            SQLiteDatabase db = pro.getWritableDatabase();
            // creata cursos para recoger datos
            String id = etCodigo.getText().toString().trim();
            Cursor fila = db.rawQuery("select nombre,precio,porc from producto where id=" + id, null);
            if (fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etPrecio.setText(fila.getString(1));
                etPorc.setText(fila.getString(2));
            }else {
                Toast.makeText(this, "ID no existe" , Toast.LENGTH_SHORT).show();
                limpiar();
            }
            db.close();
        }catch(Exception ex)
        {
            Toast.makeText(this, "Error consultar: " + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View v){
        try{
            ProductosSQLiteOpenHelper pro=new
                    ProductosSQLiteOpenHelper(this,"bdPedido",null,1);
            SQLiteDatabase db=pro.getWritableDatabase();
            //Cursor
            String id=etCodigo.getText().toString().trim();
            int cant=db.delete("producto","id="+id,null);
            db.close();
            limpiar();
            if (cant==1)
                Toast.makeText(this,"Se elimino el producto",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"No existe el producto en la BD",Toast.LENGTH_LONG).show();

        }catch (Exception ex){
            Toast.makeText(this,"Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View v){
        try{
            ProductosSQLiteOpenHelper pro=new ProductosSQLiteOpenHelper(this,"bdPedido",null,1);
            SQLiteDatabase db=pro.getWritableDatabase();
            //Cursor
            String id=etCodigo.getText().toString().trim();
            String nom=etNombre.getText().toString().trim();
            String ape=etPrecio.getText().toString().trim();
            String tel=etPorc.getText().toString().trim();

            ContentValues cv=new ContentValues();
            cv.put("nombre",nom);
            cv.put("precio",ape);
            cv.put("porc",tel);

            int cant=db.update("producto",cv,"id="+id,null);
            db.close();
            limpiar();
            if(cant==1)
                Toast.makeText(this,"Se actualizo correctamente", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"No se encontro el producto", Toast.LENGTH_LONG).show();

        }catch(Exception ex){
            Toast.makeText(this,"Error al actualizar", Toast.LENGTH_LONG).show();
        }

    }

    public void volver (View v )
    {
        Intent i = new Intent(this, ActDatosActivity.class);
        startActivity(i);
    }


}
