package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ImprimirActivity extends AppCompatActivity {

    //var
    private TextView tvReporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        // create and reference
        tvReporte = (TextView)findViewById(R.id.tvReporte);
        // retrive data from activity_del_farm
        Intent i = getIntent();
     //   String nomb = i.getStringExtra("vs_nom");
        String nombProd = i.getStringExtra("vs_nomProd");
        int cant = i.getIntExtra("vi_cant",0);
        double precioUnit = i.getDoubleExtra("vd_precUni",0.0);
        double impCom = i.getDoubleExtra("vd_impCom", 0.0);
        double igv = i.getDoubleExtra("vd_igv", 0.0);
        double impTot  = i.getDoubleExtra("vd_impTot", 0.0);

        tvReporte.setText( "Producto: " + nombProd +
                         "\nCantidad: " + cant +
                  "\nPrecio Unitario: " + precioUnit +
                "\nImporte de compra: " + impCom +
                              "\nIGV: " + igv +
                    "\nImporte Total: " + impTot );
    }
    // method back
    public void volver(View v)
    {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

}
