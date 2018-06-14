package com.example.ahmed.muslam;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class eshaa extends AppCompatActivity {
Button submit;
DatabaseHelper mdhelper;

    RadioGroup radioGroup;
    Spinner sona,traweeh;
    int radioVal = 0; //get radio button value
    int shafaAndWaterVal = 0; //shaf and el water value
    CheckBox shafaa;
    int shafaAndW; //shafaa and water
    int TotalVal=0;
    TextView header;
    Integer[] arr,arr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshaa);
        mdhelper=new DatabaseHelper(this);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        sona = (Spinner) findViewById(R.id.spinner);
        traweeh=findViewById(R.id.traweeh);
        submit=findViewById(R.id.button2);
        shafaa=findViewById(R.id.checkBox);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.only:
                        radioVal = 10;
                        break;
                    case R.id.group:
                        radioVal = 270;
                        break;
                    case R.id.masged:
                        radioVal = 300;
                        break;
                }
            }
        });

        //set Spinner
        arr = new Integer[]{0,2};
        arr1 = new Integer[]{0,4,8,16};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sona.setAdapter(adapter);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr1);
        traweeh.setAdapter(adapter1);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shafaa.isChecked()){
                    shafaAndW=3;
                }
                TotalVal=radioVal+Integer.valueOf(sona.getSelectedItem().toString())+(Integer.valueOf(traweeh.getSelectedItem().toString())*5)+shafaAndW;
                mdhelper.updateContact(contract.EntryData.Eshaa,mdhelper,TotalVal);
                Cursor cursor=mdhelper.getData(contract.EntryData.Eshaa);
                int currentVal=cursor.getColumnIndex(contract.EntryData.Eshaa);
                String col=cursor.getString(currentVal);
                Toast.makeText(eshaa.this,col,Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
