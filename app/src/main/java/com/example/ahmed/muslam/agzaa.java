package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.muslam.DatabaseHelper;
import com.example.ahmed.muslam.R;
import com.example.ahmed.muslam.contract;

public class agzaa extends AppCompatActivity {
    DatabaseHelper mdhelper = new DatabaseHelper(this);
    Integer arr[];
    Spinner sona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agzaa);

        sona = findViewById(R.id.qiamsn);
        arr = new Integer[]{0,1,2,3,4,5,6};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sona.setAdapter(adapter);
    }

    public void ndone(View view) {

            mdhelper.updateContact(contract.EntryData.Quraan,mdhelper,(Integer.valueOf(sona.getSelectedItem().toString())*100));
            Cursor cursor=mdhelper.getData(contract.EntryData.Quraan);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Quraan);
            String col=cursor.getString(currentVal);
            Toast.makeText(agzaa.this,col,Toast.LENGTH_LONG).show();
            finish();
    }
}
