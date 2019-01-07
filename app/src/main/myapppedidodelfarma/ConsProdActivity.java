package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ConsProdActivity extends AppCompatActivity {

    // creat variables Global
    private ListView lvProduct;
   // private EditText etNomb;
    private EditText etCant;
    private String nomProd;

    private void ListarProductos(){
        String[] Productos={"LOSADEL 50", "ENDGRIP FORTE","EPASMODEL","MIODEL RELAX"};
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Productos);
        lvProduct.setAdapter(adaptador);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_prod);
        // create and reference
        lvProduct = (ListView)findViewById(R.id.lvProdtuct);
        etCant = (EditText)findViewById(R.id.etCant);


        ListarProductos();
        // create an event click for the listview
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String desc = null;
                switch(position){
                    case 0: desc = "1 Tableta Losadel 5O, Precio: S/2.00"; break;
                    case 1: desc = "1 Tableta EndGrip Forte, Precio: S/1.50"; break;
                    case 2: desc = "1 Tableta EpasModel, Precio: S/1.70"; break;
                    case 3: desc = "1 Tableta Miodel Relax, Precio: S/1.80"; break;
                }
                // storing position that we chose.
                lvProduct.setItemChecked(position, true);
                nomProd = lvProduct.getAdapter().getItem(position).toString();
                Toast.makeText(getApplicationContext(),"Descripción: " + desc, Toast.LENGTH_LONG).show();
            }
        });

    }
    // create method calcular
    public void calcular (View v)
    {

        // create Variables
        String nom=null;
        int prod;
        int cant=0;


        double imp=0.0,prec=0.0,igv=0.0,impt=0.0;
        //  retriving into String cli and prod
     //   nom = etNomb.getText().toString();
        cant = Integer.parseInt(etCant.getText().toString());
        // retrieve position
        prod=lvProduct.getCheckedItemPosition();

        switch (prod)
        {
            case 0: prec = 2.00; break;
            case 1: prec = 1.50; break;
            case 2: prec = 1.70; break;
            case 3: prec = 1.80;break;
        }


        imp=prec*cant;


        igv=imp*0.18;

        impt=imp+igv;

        // create intent
        Intent i = new Intent(this, ImprimirActivity.class);
        // sending all data to another activity
       // i.putExtra("vs_nom", nom);
        i.putExtra("vs_nomProd", nomProd);
        i.putExtra("vi_cant", cant);
        i.putExtra("vd_precUni", prec);
        i.putExtra("vd_impCom", imp);
        i.putExtra("vd_igv", igv);
        i.putExtra("vd_impTot", impt);

        startActivity(i);


    }
}
