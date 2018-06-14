package com.example.ahmed.muslam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class quran extends AppCompatActivity {
    Button agzaa, kahf;
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    DatabaseHelper mdhelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        agzaa = findViewById(R.id.agzaa);
        kahf = findViewById(R.id.kahf);

        if (day != calendar.FRIDAY) {
            kahf.setEnabled(false);
        }
        Cursor cursor = mdhelper.getData(contract.EntryData.Quraan);
        int currentVal = cursor.getColumnIndex(contract.EntryData.Quraan);
        String col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            agzaa.setEnabled(false);
        }
        cursor = mdhelper.getData(contract.EntryData.Kahf);
        currentVal = cursor.getColumnIndex(contract.EntryData.Kahf);
        col=cursor.getString(currentVal);
        if (Integer.valueOf(col) != 0) {
            kahf.setEnabled(false);
        }
        agzaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quran.this,agzaa.class);
                startActivity(intent);
                finish();
            }
        });

        kahf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quran.this,kahf.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
