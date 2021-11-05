package com.example.calculadorasimples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Button btCalc;
    EditText oper1, oper2;
    TextView tvRes, oper1Error, oper2Error;
    int operacao=0; //Somar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Chamando o código da Activty mãe
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //Setando o Layout a ser carregado

        //Acessando os componentes
        tvRes= (TextView) findViewById(R.id.tV_res);
        oper1= (EditText) findViewById(R.id.edT_oper1);
        oper1Error= (TextView) findViewById(R.id.oper1Error);
        oper2= (EditText) findViewById(R.id.edT_oper2);
        oper2Error= (TextView) findViewById(R.id.oper2Error);
        btCalc= (Button) findViewById(R.id.button);
        btCalc.setOnClickListener(this);

        //Carregando o Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Definindo o Layout pro Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opersSpin, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Definindo o Adapter do Spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==btCalc){ //Gerenciando o evento onClick do Botão
            String textOper1 = oper1.getText().toString();
            String textOper2 = oper2.getText().toString();

            if(textOper1.isEmpty() || textOper1.length() == 0 || textOper1.equals("") || textOper1 == null) {
                oper1Error.setText("Insira um valor em operador 1");
            } else {
                oper1Error.setText("");
                if(textOper2.isEmpty() || textOper2.length() == 0 || textOper2.equals("") || textOper2 == null) {
                    oper2Error.setText("Insira um valor em operador 2");
                } else {
                    oper2Error.setText("");
                    double numOper1 = Double.parseDouble(oper1.getText().toString());
                    double numOper2 = Double.parseDouble(oper2.getText().toString());



                    double res=0;


                    switch (operacao){
                        case 0: //Soma
                            res= numOper1+numOper2;
                            break;
                        case 1: //Subtração
                            res= numOper1-numOper2;
                            break;
                        case 2: //Multiplicação
                            res= numOper1 * numOper2;
                            break;
                        case 3: //Divisão
                            if (numOper2 == 0) {
                                oper2Error.setText("O divisor não pode ser 0");
                            } else {
                                oper2Error.setText("");
                                res=numOper1/numOper2;
                            }
                            break;

                    }
                    tvRes.setText(""+res);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        operacao=i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    }