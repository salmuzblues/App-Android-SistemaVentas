package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void tomaPedido (View v )
    {
        Intent i = new Intent(this, TomaPedidoActivity.class);
         startActivity(i);
    }

    public void consProd(View v )
    {
        Intent x = new Intent(this, ConsProdActivity.class);
        startActivity(x);
    }
    public void actDato(View v)
    {
        Intent y = new Intent(this, ActDatosActivity.class );
        startActivity(y);
    }
    public void salir (View v )
    {
        Intent c = new Intent(this, LoginActivity.class);
        startActivity(c);
    }
    public void cambiar (View v)
    {
        Intent d = new Intent(this, CurrencyActivity.class);
        startActivity(d);
    }
}
