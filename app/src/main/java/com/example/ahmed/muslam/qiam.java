package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class qiam extends AppCompatActivity {
    DatabaseHelper mdhelper = new DatabaseHelper(this);
    Integer arr[];
    Spinner sona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiam);
        sona = findViewById(R.id.qiamsn);
        arr = new Integer[]{0,2,4,6,8,10,12,14,16};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sona.setAdapter(adapter);
    }

    public void qsub(View view) {
        mdhelper.updateContact(contract.EntryData.Qiam,mdhelper,(Integer.valueOf(sona.getSelectedItem().toString())*20));
        Cursor cursor=mdhelper.getData(contract.EntryData.Qiam);
        int currentVal=cursor.getColumnIndex(contract.EntryData.Qiam);
        String col=cursor.getString(currentVal);
        Toast.makeText(qiam.this,col,Toast.LENGTH_LONG).show();
        finish();
    }
}
