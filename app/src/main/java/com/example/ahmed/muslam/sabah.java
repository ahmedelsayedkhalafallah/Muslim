package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sabah extends AppCompatActivity {

    Button b ;
    DatabaseHelper mdhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabah);
        mdhelper = new DatabaseHelper(this);
        b= findViewById(R.id.done);
        if (getIntent().getStringExtra("zekr").toString().equals("sabah")) {
            b.setText("قمت بأداء أذكار الصباح");
        }
        else if (getIntent().getStringExtra("zekr").toString().equals("masaa")) {
            b.setText("قمت بأداء أذكار المساء");
        }

    }

    public void done(View view) {
        if (getIntent().getStringExtra("zekr").toString().equals("sabah")) {
            mdhelper.updateContact(contract.EntryData.Sabah,mdhelper,50);
            Cursor cursor=mdhelper.getData(contract.EntryData.Sabah);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Sabah);
            String col=cursor.getString(currentVal);
            Toast.makeText(sabah.this,col,Toast.LENGTH_LONG).show();
            finish();

        }
        else if (getIntent().getStringExtra("zekr").toString().equals("masaa")) {
            mdhelper.updateContact(contract.EntryData.Masaa,mdhelper,50);
            Cursor cursor=mdhelper.getData(contract.EntryData.Masaa);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Masaa);
            String col=cursor.getString(currentVal);
            Toast.makeText(sabah.this,col,Toast.LENGTH_LONG).show();
            finish();

        }
    }
}
