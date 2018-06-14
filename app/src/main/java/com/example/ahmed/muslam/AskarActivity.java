package com.example.ahmed.muslam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ahmed.muslam.AskarCounter;
import com.example.ahmed.muslam.DatabaseHelper;
import com.example.ahmed.muslam.R;
import com.example.ahmed.muslam.contract;

public class AskarActivity extends AppCompatActivity {

Button sabah,masaa, estghfar,hawl,tasbeeh,hamd,tawheed,takbeer,sala;
DatabaseHelper mdhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askar);

        mdhelper = new DatabaseHelper(this);

        sabah = findViewById(R.id.sabah);
        masaa = findViewById(R.id.masaa);
        estghfar = findViewById(R.id.estghfar);
        hawl = findViewById(R.id.hawl);
        tasbeeh = findViewById(R.id.tasbeeh);
        hamd = findViewById(R.id.hamd);
        tawheed = findViewById(R.id.tawheed);
        takbeer = findViewById(R.id.takbeer);
        sala = findViewById(R.id.sala);
        Cursor cursor = mdhelper.getData(contract.EntryData.Sabah);
        int currentVal = cursor.getColumnIndex(contract.EntryData.Sabah);
        String col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            sabah.setEnabled(false);
        }

        cursor = mdhelper.getData(contract.EntryData.Masaa);
        currentVal = cursor.getColumnIndex(contract.EntryData.Masaa);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            masaa.setEnabled(false);
        }

        sabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, sabah.class);
                intent.putExtra("zekr", "sabah");
                startActivity(intent);
                finish();
            }
        });
        masaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, sabah.class);
                intent.putExtra("zekr", "masaa");
                startActivity(intent);
                finish();
            }
        });
        estghfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, AskarCounter.class);
                intent.putExtra("zekr", "estghfar");
                startActivity(intent);
                finish();
            }
        });
        hawl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, AskarCounter.class);
                intent.putExtra("zekr", "hawl");
                startActivity(intent);
                finish();
            }
        });
        tasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, AskarCounter.class);
                intent.putExtra("zekr", "tasbeeh");
                startActivity(intent);
                finish();
            }
        });
        hamd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, AskarCounter.class);
                intent.putExtra("zekr", "hamd");
                startActivity(intent);
                finish();
            }
        });
        tawheed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this, AskarCounter.class);
                intent.putExtra("zekr", "tawheed");
                startActivity(intent);
                finish();
            }
        });
        takbeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this,AskarCounter.class);
                intent.putExtra("zekr", "takbeer");
                startActivity(intent);
                finish();
            }
        });
        sala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskarActivity.this,AskarCounter.class);
                intent.putExtra("zekr", "sala");
                startActivity(intent);
                finish();
            }
        });
    }
}
