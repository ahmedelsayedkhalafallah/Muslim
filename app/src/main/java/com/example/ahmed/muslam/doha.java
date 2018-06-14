package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ahmed.muslam.DatabaseHelper;
import com.example.ahmed.muslam.R;
import com.example.ahmed.muslam.contract;

public class doha extends AppCompatActivity {
Button done;
    DatabaseHelper mdhelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doha);

        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdhelper.updateContact(contract.EntryData.Doha,mdhelper,10);
                Cursor cursor=mdhelper.getData(contract.EntryData.Doha);
                int currentVal=cursor.getColumnIndex(contract.EntryData.Doha);
                String col=cursor.getString(currentVal);
                Toast.makeText(doha.this,col,Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void UpdateData() {
        //to get current day

    }
}
