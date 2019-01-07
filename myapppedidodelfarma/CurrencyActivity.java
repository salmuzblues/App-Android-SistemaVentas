package com.example.root.myapppedidodelfarma;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CurrencyActivity extends AppCompatActivity {

    private Spinner spin, spin1;
    private EditText etDate, edtCant;
    private Button btnObtener;
    private TextView tvResultado;
    private String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        spin = (Spinner)findViewById(R.id.spin);
        spin1 = (Spinner)findViewById(R.id.spin1);
        etDate = (EditText) findViewById(R.id.etDate);
        edtCant = (EditText)findViewById(R.id.etCant);
        btnObtener = (Button) findViewById(R.id.btnObtener);
        tvResultado = (TextView)findViewById(R.id.tvResultado);
        // spin moneda de
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.monedaDe, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(adapter1);
        // spin moneda a
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monedaA, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin1.setAdapter(adapter);

        // create event click for button
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instanciar la clase Asincrona
                MyTask t = new MyTask();
                //ejecutar la clase
                t.execute();
            }
        });

    }

    // Create method user for consumering wsdl
    //METODO DE USUARIO
    private void consumir(){
        // Metodo que queremos ejecutar en el servicio web
        String metodo = "GetConversionAmount";
        // Namespace definido en el servicio web
        String nameSpace = "http://tempuri.org/";
        // namespace + metodo
        String accionSoap = "http://tempuri.org/GetConversionAmount";
        // Fichero de definicion del servcio web
        String url = "http://currencyconverter.kowabunga.net/converter.asmx";
        try {
            // Modelo el request
            SoapObject request = new SoapObject(nameSpace, metodo);
            // si hay mas parametos se escribe mas.
            request.addProperty("CurrencyFrom",spin.getSelectedItem().toString());
            request.addProperty("CurrencyTo",spin1.getSelectedItem().toString());
            request.addProperty("RateDate",etDate.getText().toString());
            request.addProperty("Amount",edtCant.getText().toString());

            // Modelo el envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //estos dice para consumir .net
            envelope.dotNet = true; // para visual estudio
            envelope.setOutputSoapObject(request);
            // Modelo el transporte
            HttpTransportSE transport = new HttpTransportSE(url);
            // Llamada
            transport.call(accionSoap, envelope);
            // Resultado
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resultado = response.toString();

        }catch(Exception e){
            resultado = "Error Web Service: " + e.getMessage();
        }
    }
    // create task asincrona
    private class MyTask extends AsyncTask<Void, Void, Void> {
        // reducir los segundos de respuestas
        @Override
        protected Void doInBackground(Void... voids) {
            consumir();
            return null;
        }

        // para los resultados de las opeaciones
        @Override
        protected void onPostExecute(Void aVoid) {
            // enviar el resultados de ws
            tvResultado.setText("Cambio a: " + resultado);
            super.onPostExecute(aVoid);
        }
    }

    public void volver (View v)
    {
        Intent t = new Intent(this, MenuActivity.class);
        startActivity(t);
    }
}
