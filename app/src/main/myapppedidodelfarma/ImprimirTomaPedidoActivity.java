package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ImprimirTomaPedidoActivity extends AppCompatActivity {

    private TextView tvReporte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir_toma_pedido);

        // create and reference
        tvReporte = (TextView)findViewById(R.id.tvReporte);
        // retrive data from activity_del_farm
        Intent i = getIntent();
        String strCliente = i.getStringExtra("vs_cliente");
        String strFormaPag = i.getStringExtra("vs_formaPago");
        double cantLosadel = i.getDoubleExtra("vd_CantLosadel",0.0);
        double cantEndGrip = i.getDoubleExtra("vd_CantEndGrip", 0.0);
        double cantPassModel = i.getDoubleExtra("vd_CantPassModel", 0.0);
        double cantMiodel  = i.getDoubleExtra("vd_CantMiodel", 0.0);
        double  igv = i.getDoubleExtra("vd_igv", 0.0);
        double imp = i.getDoubleExtra("vd_imp", 0.0);
        double  total = i.getDoubleExtra("vd_total", 0.0);

        tvReporte.setText( "Cliente: " + strCliente
               +   "\nForma de Pago: " + strFormaPag
 +"\nCantidad x Caja  Losadel de 50: " + cantLosadel
 + "\nCantidad x Caja EndGrip Forte: " + cantEndGrip
 +     "\nCantidad x Caja EpasModel: " + cantPassModel
 +  "\nCantidad x Caja Miodel Relax: " + cantMiodel +
                  "\nImporte Compra: S/ " + imp +
                             "\nIGV: S/ " + igv +
                   "\nImporte Total: S/ " + total );
    }
    // method back
    public void volver(View v)
    {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
    }

