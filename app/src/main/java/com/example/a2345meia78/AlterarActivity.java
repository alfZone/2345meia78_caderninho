package com.example.a2345meia78;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity {

    SharedPreferences key;
    SharedPreferences.Editor keyEditor;
    ImageButton btVoltar, btAlterar;
    EditText etPinAt,etPinNovo, etPinConfirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        btAlterar=findViewById(R.id.btGravarAlterarPin);
        btVoltar=findViewById(R.id.btVoltarDoPIN);
        etPinAt=findViewById(R.id.etPINatual);
        etPinNovo=findViewById(R.id.etPINnovo);
        etPinConfirma=findViewById(R.id.etPINconfirma);


        key= getSharedPreferences(getString(R.string.pin), Context.MODE_PRIVATE);
        String pinAct= key.getString(getString(R.string.pin), "1234");

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPinAt.getText().toString().equals(pinAct)){
                    if (etPinNovo.getText().toString().equals(etPinConfirma.getText().toString())){
                        keyEditor = key.edit();
                        keyEditor.putString(getString(R.string.pin),etPinNovo.getText().toString());
                        keyEditor.apply();
                        Toast.makeText(AlterarActivity.this, "O seu PIN foi alterado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AlterarActivity.this, "O novo PIN e a sua confirmação são diferentes!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AlterarActivity.this, "O PIN está incorrecto!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}