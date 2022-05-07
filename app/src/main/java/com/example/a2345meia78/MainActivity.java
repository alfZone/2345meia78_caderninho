package com.example.a2345meia78;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView lvContatos;
    ArrayList<Contato> listContatos;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ArrayAdapter<Contato> adapter;
   // Button btSobre, btAZ,btZA;
    ImageButton ibSobre, ibAZ,ibZA, ibNovo, ibSair;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Contato ct;
        int id;

        switch (resultCode){
            case 2:
                // editar registo
                ct = (Contato)data.getSerializableExtra("contacto");
                id=data.getExtras().getInt("i");
                listContatos.set(id,ct);
                break;
            case 3:
                //novo registo
                ct = (Contato)data.getSerializableExtra("contacto");
                listContatos.add(ct);
                break;
            case 4:
                //apagar registo
                id=data.getExtras().getInt("i");
                listContatos.remove(id);
                //activityResultLauncher.notifyAll();
                adapter.notifyDataSetChanged();
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContatos = findViewById(R.id.lvContatos);
        ibAZ = findViewById(R.id.ibAZ);
        ibZA = findViewById(R.id.ibZA);
        ibSobre=findViewById(R.id.ibSobre);
        ibNovo=findViewById(R.id.ibNovoMain);
        ibSair=findViewById(R.id.ibSairAplica);

        listContatos = new ArrayList<>();
        listContatos.add(new Contato("Ana Maria","saiu@namorado.pt","913345678", "https://cdn.maxima.pt/images/2020-04/img_660x1200$2020_04_16_19_17_36_416422.jpg"));
        listContatos.add(new Contato("Dani","gamadinha@mim.pt","919876990", "https://www.fantasiascriativas.com.br/wp-content/uploads/2014/05/damadora.jpg"));
        listContatos.add(new Contato("Guta","guta@vovo.pt","919876990", "https://www.istoedinheiro.com.br/wp-content/uploads/sites/17/Reuters_Direct_Media/BrazilOnlineReportEntertainmentNews/tagreuters.com2021binary_LYNXMPEH9I0V7-BASEIMAGE-e1634655423521.jpg"));
        listContatos.add(new Contato("Bianca","cara@carranca.pt","922345756", "https://st.depositphotos.com/1763233/3200/i/600/depositphotos_32009455-stock-photo-portrait-an-ugly-woman-with.jpg"));
        listContatos.add(new Contato("Zulmira","jesus@cristo.pt","922345756", "https://www.festasparty.pt/i/a/l/v/veu-de-freira.jpg"));
        listContatos.add(new Contato("Elizabete","ta@comendo.pt","922345756", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1DUKol4wDZaRNOKm1VrIPb7WDAZvISdLPGg&usqp=CAU"));
        listContatos.add(new Contato("Francisca","mulher@policia.pt","922345756", "https://br.web.img3.acsta.net/newsv7/20/04/17/20/58/3150014.png"));

        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()==2){

                            Contato ct = (Contato)result.getData().getSerializableExtra("contacto");
                            int id=result.getData().getExtras().getInt("i");
                            listContatos.set(id,ct);
                            //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listContatos);
        lvContatos.setAdapter(adapter);

        ibSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        ibZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListDesc();


            }
        });

        ibAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListAsc();
                

            }
        });

        ibSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent id=new Intent(MainActivity.this, SobreActivity.class);
                startActivity(id);
            }
        });

        ibNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contato ct= new Contato("");
                Intent id=new Intent(MainActivity.this, DetalhesActivity.class);
                id.putExtra("i",-1);
                id.putExtra("contacto", ct);
                activityResultLauncher.launch(id);
            }
        });

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Contato ct= new Contato(listContatos.get(i));

                Intent id=new Intent(MainActivity.this, DetalhesActivity.class);
                //id.putExtra("nome",listContatos.get(i).getNome());
                //id.putExtra("email",listContatos.get(i).getEmail());
                //id.putExtra("tlm",listContatos.get(i).getTelefone());
                //id.putExtra("foto",listContatos.get(i).getFoto());
                id.putExtra("i",i);
                id.putExtra("contacto", ct);
                //startActivityForResult(id,1);
                activityResultLauncher.launch(id);
            }
        });
    }

    private void sortArrayListAsc() {
        Collections.sort(listContatos, new Comparator<Contato>() {
            @Override
            public int compare(Contato c1, Contato c2) {
                return c1.getNome().compareTo(c2.getNome());
            }
        });

        adapter.notifyDataSetChanged();

    }

    private void sortArrayListDesc() {
        Collections.sort(listContatos, new Comparator<Contato>() {
            @Override
            public int compare(Contato c1, Contato c2) {
                return c2.getNome().compareTo(c1.getNome());
            }
        });

        adapter.notifyDataSetChanged();

    }


}