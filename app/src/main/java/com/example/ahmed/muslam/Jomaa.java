package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Jomaa extends AppCompatActivity {
    RadioGroup radioGroup;
    int radioVal = 0;
    DatabaseHelper mdhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jomaa);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        mdhelper = new DatabaseHelper(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.before:
                        radioVal = 1000;
                        break;
                    case R.id.after:
                        radioVal = 300;
                        break;
                    case R.id.alone:
                        radioVal = 10;
                        break;
                    case R.id.group:
                        radioVal = 270;
                        break;

                }
            }
        });
    }

    public void jbtn(View view) {
        mdhelper.updateContact(contract.EntryData.Jomaa,mdhelper,radioVal);
        Cursor cursor=mdhelper.getData(contract.EntryData.Jomaa);
        int currentVal=cursor.getColumnIndex(contract.EntryData.Jomaa);
        String col=cursor.getString(currentVal);
        Toast.makeText(Jomaa.this,col,Toast.LENGTH_LONG).show();
        finish();

    }
}
