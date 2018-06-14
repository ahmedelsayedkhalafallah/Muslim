package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class kahf extends AppCompatActivity {
    Button done;
    DatabaseHelper mdhelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kahf);

        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdhelper.updateContact(contract.EntryData.Kahf,mdhelper,250);
                Cursor cursor=mdhelper.getData(contract.EntryData.Kahf);
                int currentVal=cursor.getColumnIndex(contract.EntryData.Kahf);
                String col=cursor.getString(currentVal);
                Toast.makeText(kahf.this,col,Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
