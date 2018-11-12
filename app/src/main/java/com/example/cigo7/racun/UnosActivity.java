package com.example.cigo7.racun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UnosActivity extends AppCompatActivity {

    EditText naziv;
    EditText kolicina;
    EditText cijena;

    Button spremi;
    Button odustani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos);

        naziv = (EditText) findViewById(R.id.naziv_edit_text);
        kolicina = (EditText) findViewById(R.id.kolicina_edit_text);
        cijena = (EditText) findViewById(R.id.cijena_edit_text);

        spremi = (Button) findViewById(R.id.button3);
        odustani = (Button) findViewById(R.id.button);

        onClickListeners();

    }

    private void onClickListeners() {
        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nazivArtikla = naziv.getText().toString();
                String kolicinaArtikla = kolicina.getText().toString();
                String cijenaArtikla = cijena.getText().toString();

                if (nazivArtikla.isEmpty() || kolicinaArtikla.isEmpty() || cijenaArtikla.isEmpty()) {
                    Toast.makeText(UnosActivity.this, "Nisu popunjena sva polja.", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        Integer.parseInt(kolicinaArtikla);
                        Float.parseFloat(cijenaArtikla);
                    } catch (Exception e) {
                        Toast.makeText(UnosActivity.this, "Neispravni unos", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Intent intent = new Intent(UnosActivity.this, MainActivity.class);
                    intent.putExtra("naziv", nazivArtikla);
                    intent.putExtra("kolicina", kolicinaArtikla);
                    intent.putExtra("cijena", cijenaArtikla);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnosActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });


    }
}
