package com.example.a2345meia78;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etPin;
    Button btLogin;

    SharedPreferences key;
    //SharedPreferences.Editor keyEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin=findViewById(R.id.btLogin);
        etPin=findViewById(R.id.etPin);

        key= getSharedPreferences(getString(R.string.pin), Context.MODE_PRIVATE);
        String pinAct= key.getString(getString(R.string.pin), "1234");

        //Toast.makeText(this,"sdsd",Toast.LENGTH_LONG).show();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(LoginActivity.this,"sss",Toast.LENGTH_LONG).show();


                if (etPin.getText().toString().equals(pinAct)){
                    Toast.makeText(LoginActivity.this, "muito bem!", Toast.LENGTH_SHORT).show();
                    Intent id=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(id);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "O pin está incorrecto!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}