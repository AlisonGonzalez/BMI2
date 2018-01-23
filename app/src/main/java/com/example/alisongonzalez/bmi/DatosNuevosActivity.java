package com.example.alisongonzalez.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class DatosNuevosActivity extends Activity {
    private EditText peso, altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_nuevos);

        peso = (EditText)findViewById(R.id.peso);
        altura = (EditText)findViewById(R.id.altura);
    }

    public void calcula(View view){
        Persona p = new Persona();
        String pesoStr = peso.getText().toString();
        String alturaStr = altura.getText().toString();
        if(TextUtils.isEmpty(pesoStr)){
            peso.setError("Ingresa un valor");
            return;
        }
        if(TextUtils.isEmpty(alturaStr)){
            altura.setError("Ingresa un valor");
            return;
        }
        p.peso = Double.parseDouble(pesoStr);
        p.altura = Double.parseDouble(alturaStr);

        Intent intent = new Intent();
        intent.putExtra("persona", p);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
