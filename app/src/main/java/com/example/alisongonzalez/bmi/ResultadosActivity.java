package com.example.alisongonzalez.bmi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadosActivity extends Activity {

    private TextView nombre, imc, ideal, energia;
    private ImageView imageView;
    private final int REQUEST_CODE = 7007;
    private boolean isMujer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        nombre = (TextView)findViewById(R.id.nombreUsuario);
        imc = (TextView)findViewById(R.id.imcUsuario);
        ideal = (TextView)findViewById(R.id.pesoIdeal);
        energia = (TextView)findViewById(R.id.energia);
        imageView = (ImageView)findViewById(R.id.imageView);

        Persona p = (Persona)getIntent().getSerializableExtra("persona");
        if(!p.sexo.equalsIgnoreCase("mujer")){
            isMujer = false;
        }else{
            isMujer = true;
        }
        nombre.setText(p.nombre);

        calculate(p);

    }

    private void calculate(Persona p){
        double imcCalc = p.peso / Math.pow(p.altura, 2.0);
        double idealCalc = Math.pow(p.altura,2.0) * 22;
        double energiaCalc = idealCalc * 30;

        imc.setText("IMC: " + String.valueOf(imcCalc));
        ideal.setText("Peso ideal : " + String.valueOf(idealCalc));
        energia.setText("Energía: " + String.valueOf(energiaCalc));

        if(isMujer){
            if(imcCalc <= 17.5){
                imageView.setImageResource(R.drawable.woman_bmi_17_5);
            } else if (imcCalc > 17.5 && imcCalc <= 18.5){
                imageView.setImageResource(R.drawable.woman_bmi_18_5);
            } else if (imcCalc > 18.5 && imcCalc <= 22.0){
                imageView.setImageResource(R.drawable.woman_bmi_22);
            } else if (imcCalc > 22.0 && imcCalc <= 24.9){
                imageView.setImageResource(R.drawable.woman_bmi_24_9);
            } else if (imcCalc > 24.9 && imcCalc <= 30.0){
                imageView.setImageResource(R.drawable.woman_bmi_30);
            } else {
                imageView.setImageResource(R.drawable.woman_bmi_40);
            }
        } else {
            if(imcCalc <= 17.5){
                imageView.setImageResource(R.drawable.men_bmi_17_5);
            } else if (imcCalc > 17.5 && imcCalc <= 18.5){
                imageView.setImageResource(R.drawable.men_bmi_18_5);
            } else if (imcCalc > 18.5 && imcCalc <= 22.0){
                imageView.setImageResource(R.drawable.men_bmi_22_0);
            } else if (imcCalc > 22.0 && imcCalc <= 24.9){
                imageView.setImageResource(R.drawable.men_bmi_24_9);
            } else if (imcCalc > 24.9 && imcCalc <= 30.0){
                imageView.setImageResource(R.drawable.men_bmi_30);
            } else {
                imageView.setImageResource(R.drawable.men_bmi_40);
            }
        }
    }

    public void recalculte(View view){
        Intent intent = new Intent(this, DatosNuevosActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void openBMI(View view){
        String url = "https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("persona")){
                Persona p = (Persona)data.getSerializableExtra("persona");
                calculate(p);
            }
        }
    }
}
