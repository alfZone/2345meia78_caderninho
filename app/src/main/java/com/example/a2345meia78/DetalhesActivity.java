package com.example.a2345meia78;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2345meia78.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;

public class DetalhesActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Handler mainHandler= new Handler();
    ProgressDialog progressDialog;

    Intent i;
    TextView tvNome;
    TextView tvEmail;
    TextView tvTelemovel;
    TextView tvFoto;
    ImageView ivFoto;
    ImageButton btEditar, btNovo, btEliminar, btLigar, btVoltar;
    int x;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_detalhes);
        //setContentView(binding.getRoot());

        tvNome= findViewById(R.id.etNome);
        tvEmail= findViewById(R.id.etEmail);
        tvTelemovel= findViewById(R.id.etTelemovel);
        tvFoto= findViewById(R.id.etFoto);
        btEditar= findViewById(R.id.btEditar);
        btNovo=findViewById(R.id.btNovo);
        btEliminar=findViewById(R.id.btApagar);
        btLigar=findViewById(R.id.btTelefonar);
        btVoltar=findViewById(R.id.btVoltardoDetalhe);
        ivFoto=findViewById(R.id.ivFoto);

        //String url="https://cdn.maxima.pt/images/2020-04/img_660x1200$2020_04_16_19_17_36_416422.jpg";
        //new FetchImage(url).start();



        i=getIntent();

        Contato ct = (Contato)i.getSerializableExtra("contacto");


        Bundle extras = i.getExtras();

        tvNome.setText(ct.getNome());
        tvEmail.setText(ct.getEmail());
        tvTelemovel.setText(ct.getTelefone());
        tvFoto.setText(ct.getFoto());

        x=extras.getInt("i");

        if (ct.getFoto()!=""){
            //String url="https://cdn.maxima.pt/images/2020-04/img_660x1200$2020_04_16_19_17_36_416422.jpg";
            String url=ct.getFoto();
            new FetchImage(url).start();
        }

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DetalhesActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    String  uri= "tel:" + "919064760";
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse(uri));
                    startActivity(call);
                }else{
                    Toast.makeText(DetalhesActivity.this, "Tuu tuu tuu... Não consigo ligar!", Toast.LENGTH_SHORT).show();
                }

            }

        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i.putExtra("i", x);
                setResult(4,i);
                finish();
            }
        });

        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contato ct= new Contato(tvNome.getText().toString(),
                        tvEmail.getText().toString(),
                        tvTelemovel.getText().toString(),
                        tvFoto.getText().toString());
                i.putExtra("contacto", ct);
                i.putExtra("i", x);
                setResult(3,i);
                finish();
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contato ct= new Contato(tvNome.getText().toString(),
                        tvEmail.getText().toString(),
                        tvTelemovel.getText().toString(),
                        tvFoto.getText().toString());
                i.putExtra("contacto", ct);
                i.putExtra("i", x);
                setResult(2,i);
                finish();
            }
        });


    }

    class FetchImage extends Thread{

        String URL;
        Bitmap bitmap;

        FetchImage(String URL){

            this.URL=URL;

        }

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog=new ProgressDialog(DetalhesActivity.this);
                    progressDialog.setMessage("A carregar a foto");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            InputStream inputStream=null;
            try {
                inputStream=new java.net.URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    ivFoto.setImageBitmap(bitmap);

                }
            });

        }
    }

}