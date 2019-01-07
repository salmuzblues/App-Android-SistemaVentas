package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_datos);
    }

    public void cliente (View v)
    {
        Intent i = new Intent(this, ClienteActivity.class);
        startActivity(i);
    }
    public void producto (View v)
    {
      Intent x  = new Intent(this, ProductoActivity.class);
        startActivity(x);
    }
    public void volver (View v)
    {
        Intent z = new Intent(this, MenuActivity.class);
        startActivity(z);
    }
}
