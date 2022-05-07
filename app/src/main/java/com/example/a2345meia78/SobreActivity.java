package com.example.a2345meia78;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SobreActivity extends AppCompatActivity {

    ImageButton btVoltar, btAlterarPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        btVoltar=findViewById(R.id.btVoltar);
        btAlterarPin=findViewById(R.id.btAlterarPin);

        btAlterarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent id=new Intent(SobreActivity.this, AlterarActivity.class);
                startActivity(id);
                finish();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}