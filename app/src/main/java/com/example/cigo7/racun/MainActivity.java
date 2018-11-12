package com.example.cigo7.racun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 732;
    private ArrayList<Artikl> artiklArrayList;

    TableLayout tableLayout;
    Button unos;
    TextView ukupno;

    String ukupnoPrefixTekst = "UKUPNO = ";
    Float ukupnaCijena = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artiklArrayList = new ArrayList<>();
        unos = (Button) findViewById(R.id.button2);
        ukupno = (TextView) findViewById(R.id.ukupno_text_view);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);


        // inicijaliziram gumb listener
        onClickListeners();

        updateTable();
    }

    private void updateUkupno() {
        ukupno.setText(ukupnoPrefixTekst + ukupnaCijena);
    }

    private void updateTable() {

        tableLayout.removeAllViews();

        for (Artikl artikl : artiklArrayList) {
            TableRow tableRow = new TableRow(this);

            TextView nazivTextView = new TextView(this);
            nazivTextView.setText(artikl.getNaziv());
            nazivTextView.setPadding(5, 0, 40, 0);

            TextView kolicinaTextView = new TextView(this);
            kolicinaTextView.setText(String.valueOf(artikl.getKolicina()));
            kolicinaTextView.setPadding(5, 0, 5, 0);

            TextView cijenaTextView = new TextView(this);
            cijenaTextView.setText(String.valueOf(artikl.getCijena()));
            cijenaTextView.setPadding(5, 0, 5, 0);

            tableRow.addView(nazivTextView);
            tableRow.addView(kolicinaTextView);
            tableRow.addView(cijenaTextView);

            tableLayout.addView(tableRow);

        }

    }

    private void onClickListeners() {

        unos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UnosActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Artikl artikl = new Artikl();
                artikl.setNaziv(data.getStringExtra("naziv"));
                artikl.setKolicina(Integer.parseInt(data.getStringExtra("kolicina")));
                artikl.setCijena(Float.parseFloat(data.getStringExtra("cijena")));

                ukupnaCijena = ukupnaCijena + (artikl.getKolicina() * artikl.getCijena());
                updateUkupno();

                artiklArrayList.add(artikl);

                //kada se unese neka se update-a layout

                updateTable();

            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "Artikl nije spremljen.", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
