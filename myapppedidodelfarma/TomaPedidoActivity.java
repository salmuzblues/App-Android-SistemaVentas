package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class TomaPedidoActivity extends AppCompatActivity {
// vars Global

    private  Spinner spCliente, spFromPag;
    private EditText etLosadel, etEndGrip, etPasModel, etMiodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toma_pedido);

        // references
        spCliente = (Spinner)findViewById(R.id.spCliente);
        spFromPag = (Spinner)findViewById(R.id.spFormPago);
        etLosadel = (EditText) findViewById(R.id.etLosadel);
        etEndGrip = (EditText)findViewById(R.id.etEndGrip);
        etPasModel = (EditText)findViewById(R.id.etEpasModel);
        etMiodel = (EditText)findViewById(R.id.etMiodel);
        ListaFormPago();
        ListaCliente();
    }
    // create method Array
    private void ListaCliente()
    {
        String [] cliente = {"Botica 24 Horas","Botica Adrian", "Botica Adric", "Botica Ahorro Salud", "Botica Aldani", "Botica Alexander","Botica Alicia" ,
            "Botica Alys Farma", "Botica Amistad", "Botica Ana Jesus","Botica Anderson"};
        ArrayAdapter<String> adpCliente = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cliente );
        spCliente.setAdapter(adpCliente);
    }

    private void ListaFormPago () {
        String [] formPag = {"Credito", "Contado"};
        ArrayAdapter<String> adpFormPg = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,formPag);
        spFromPag.setAdapter(adpFormPg);
    }
    public void calcular (View v)
    {
        // vars
        String strCliente;
        String strFormaPago;
        double  Losadel, EndGrip, pasModel, Miodel, total, igv,precLosadel, precEndGrip,
                precPassMo,precMiodel, imp;
        // Read
        Losadel = Double.parseDouble(etLosadel.getText().toString());
        EndGrip = Double.parseDouble(etEndGrip.getText().toString());
        pasModel = Double.parseDouble(etPasModel.getText().toString());
        Miodel = Double.parseDouble(etMiodel.getText().toString());
        strFormaPago = spFromPag.getSelectedItem().toString();
        strCliente = spCliente.getSelectedItem().toString();
        precLosadel = 20.00;
        precEndGrip = 15.80;
        precPassMo =  17.80;
        precMiodel = 18.00;
        // Process
        imp = Losadel*precLosadel + EndGrip*precEndGrip + pasModel * precPassMo + Miodel * precMiodel;

        igv = imp * 0.18;

        total = imp + igv;

        // create Intent

        Intent i = new Intent(this, ImprimirTomaPedidoActivity.class);
        // sending all data to another activity
        i.putExtra("vs_formaPago", strFormaPago);
        i.putExtra("vs_cliente", strCliente);
        i.putExtra("vd_CantLosadel", Losadel);
        i.putExtra("vd_CantEndGrip", EndGrip);
        i.putExtra("vd_CantPassModel", pasModel);
        i.putExtra("vd_CantMiodel", Miodel);
        i.putExtra("vd_imp", imp);
        i.putExtra("vd_igv", igv);
        i.putExtra("vd_total", total);

        startActivity(i);
    }

    public void volver(View v)
    {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
