package com.example.root.myapppedidodelfarma;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {

    private EditText etCodigo,etNombre, etDireccion, etDistrito, etRuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        etCodigo= (EditText)findViewById(R.id.etCodigo);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etDireccion=(EditText)findViewById(R.id.etDireccion);
        etDistrito = (EditText)findViewById(R.id.etDistrito);
        etRuc=(EditText)findViewById(R.id.etRuc);

       }

    public void limpiar(){
        etCodigo.setText("");
        etNombre.setText("");
        etDireccion.setText("");
        etDistrito.setText("");
        etRuc.setText("");
        etCodigo.requestFocus();
    }

    public void grabar(View v){
        try {
            //Instanciar la clase
            ClienteSQLiteOpenHelper cli=new ClienteSQLiteOpenHelper(this,"bdCliente",null,1);
            SQLiteDatabase db=cli.getWritableDatabase();
            String cod=etCodigo.getText().toString().trim();
            String nom=etNombre.getText().toString().trim();
            String dir=etDireccion.getText().toString().trim();
            String ruc=etRuc.getText().toString().trim();
            String dis=etDistrito.getText().toString().trim();
            //Crear contenedor de valores
            ContentValues cv=new ContentValues();
            cv.put("id",cod);
            cv.put("nombre",nom);
            cv.put("direccion",dir);
            cv.put("distrito",dis);
            cv.put("ruc",ruc);
            db.insert("cliente",null,cv);
            db.close();
            limpiar();
            Toast.makeText(this,"Se registro correctamente",Toast.LENGTH_LONG).show();

        }   catch(Exception ex){
            Toast.makeText(this,"Error al Grabar: " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }


    public void Consultar(View v){
        try{
            ClienteSQLiteOpenHelper cli=new
                    ClienteSQLiteOpenHelper(this,"bdCliente",null,1);
            SQLiteDatabase db=cli.getWritableDatabase();
            //Cursor
            String id=etCodigo.getText().toString().trim();
            Cursor fila=db.rawQuery("select nombre, direccion, distrito, ruc from cliente where id=" + id,null);
            if (fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etDireccion.setText(fila.getString(1));
                etDistrito.setText(fila.getString(2));
                etRuc.setText(fila.getString(3));
            }else{
                Toast.makeText(this,"ID no existe",Toast.LENGTH_SHORT).show();
                limpiar();
            }
            db.close();

        }catch(Exception ex){
            Toast.makeText(this,"Error al consultar",Toast.LENGTH_LONG).show();
        }

    }

    public void Eliminar(View v){
        try{
            ClienteSQLiteOpenHelper cli=new
                    ClienteSQLiteOpenHelper(this,"bdCliente",null,1);
            SQLiteDatabase db=cli.getWritableDatabase();
            //Cursor
            String id=etCodigo.getText().toString().trim();
            int cant=db.delete("cliente","id="+id,null);
            db.close();
            limpiar();
            if (cant==1)
                Toast.makeText(this,"Se elimino el cliente",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"No existe el cliente en la BD",Toast.LENGTH_LONG).show();

        }catch (Exception ex){
            Toast.makeText(this,"Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View v){
        try{
            ClienteSQLiteOpenHelper cli=new ClienteSQLiteOpenHelper(this,"bdCliente",null,1);
            SQLiteDatabase db=cli.getWritableDatabase();
            //Cursor
            String id=etCodigo.getText().toString().trim();
            String nom=etNombre.getText().toString().trim();
            String dir=etDireccion.getText().toString().trim();
            String dis=etDistrito.getText().toString().trim();
            String ruc=etRuc.getText().toString().trim();

            ContentValues cv=new ContentValues();
            cv.put("nombre",nom);
            cv.put("direccion",dir);
            cv.put("distrito",dis);
            cv.put("ruc",ruc);

            int cant=db.update("cliente",cv,"id="+id,null);
            db.close();
            limpiar();
            if(cant==1)
                Toast.makeText(this,"Se actualizo correctamente", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"No se encontro el id", Toast.LENGTH_LONG).show();

        }catch(Exception ex){
            Toast.makeText(this,"Error al actualizar", Toast.LENGTH_LONG).show();
        }

    }

    public void volver (View v)
    {
        Intent x = new Intent(this, ActDatosActivity.class);
        startActivity(x);
    }

}
