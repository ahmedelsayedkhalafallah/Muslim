package com.example.ahmed.muslam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class PrayActivity extends AppCompatActivity {
    Button fajr, doha, zuhr, asr, maghrib, eshaa, qiam, jomaa;
    DatabaseHelper mdhelper = new DatabaseHelper(this);
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray);
        fajr = findViewById(R.id.fajr);
        doha = findViewById(R.id.doha);
        zuhr = findViewById(R.id.zuhr);
        jomaa = findViewById(R.id.jomaa);
        asr = findViewById(R.id.asr);
        maghrib = findViewById(R.id.maghrib);
        eshaa = findViewById(R.id.eshaa);
        qiam = findViewById(R.id.qiam);


        Cursor cursor = mdhelper.getData(contract.EntryData.fajr);
        int currentVal = cursor.getColumnIndex(contract.EntryData.fajr);
        String col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            fajr.setEnabled(false);
        }

        cursor = mdhelper.getData(contract.EntryData.Doha);
        currentVal = cursor.getColumnIndex(contract.EntryData.Doha);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            doha.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Zuhr);
        currentVal = cursor.getColumnIndex(contract.EntryData.Zuhr);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0 || day == calendar.FRIDAY) {
            zuhr.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Jomaa);
        currentVal = cursor.getColumnIndex(contract.EntryData.Jomaa);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0 || day != calendar.FRIDAY) {
            jomaa.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Asr);
        currentVal = cursor.getColumnIndex(contract.EntryData.Asr);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            asr.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Maghreb);
        currentVal = cursor.getColumnIndex(contract.EntryData.Maghreb);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            maghrib.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Eshaa);
        currentVal = cursor.getColumnIndex(contract.EntryData.Eshaa);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            eshaa.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Qiam);
        currentVal = cursor.getColumnIndex(contract.EntryData.Qiam);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            qiam.setEnabled(false);
        }
        //open activities
        fajr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, PrayItem.class);
                intent.putExtra("sala", "fajr");
                startActivity(intent);
                finish();
            }
        });
        doha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, doha.class);
                intent.putExtra("sala", "doha");
                startActivity(intent);
                finish();
            }
        });
        zuhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, PrayItem.class);
                intent.putExtra("sala", "zuhr");
                startActivity(intent);
                finish();
            }
        });
        jomaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, Jomaa.class);
                intent.putExtra("sala", "jomaa");
                startActivity(intent);
                finish();
            }
        });
        asr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, PrayItem.class);
                intent.putExtra("sala", "asr");
                startActivity(intent);
                finish();
            }
        });
        maghrib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, PrayItem.class);
                intent.putExtra("sala", "maghrib");
                startActivity(intent);
                finish();
            }
        });
        eshaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, eshaa.class);
                intent.putExtra("sala", "eshaa");
                startActivity(intent);
                finish();
            }
        });
        qiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrayActivity.this, qiam.class);
                intent.putExtra("sala", "qiam");
                startActivity(intent);
                finish();
            }
        });

    }
}
