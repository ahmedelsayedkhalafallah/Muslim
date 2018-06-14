package com.example.ahmed.muslam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AskarCounter extends AppCompatActivity {
    public static int counter = 0;
    Button plus;
    TextView t , header;
    DatabaseHelper mdhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askar_counter);
        plus = findViewById(R.id.plus);
        mdhelper = new DatabaseHelper(this);
        t = findViewById(R.id.textView2);
        header = findViewById(R.id.textView);
        setHeader();
        try {
            setHeader();
        } catch (Exception e) {
            Log.e("sss", "onCreate: ", e);
        }
    }
    //set header
    public void setHeader() {

         if (getIntent().getStringExtra("zekr").toString().equals("estghfar")) {
             header.setText("استغفر الله");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("hawl")) {
             header.setText("لا حول ولا قوة إلا بالله");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("tasbeeh")) {
             header.setText("سبحان الله");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("hamd")) {
             header.setText("الحمدلله");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("tawheed")) {
             header.setText("لا إله إلا الله");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("takbeer")) {
             header.setText("الله أكبر");
         }
         else if (getIntent().getStringExtra("zekr").toString().equals("sala")) {
             header.setText("صلى الله على محمد");
         }
    }

    public void plus(View view) {
        counter++;
        t.setText(String.valueOf(counter));
    }

    public void done(View view) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        double value = 0.2;
        if(day == calendar.FRIDAY)
            value = 0.5;
        if (getIntent().getStringExtra("zekr").toString().equals("estghfar")) {
            mdhelper.updateContact(contract.EntryData.Esteghfar,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Esteghfar);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Esteghfar);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }
        else if (getIntent().getStringExtra("zekr").toString().equals("hawl")) {
            mdhelper.updateContact(contract.EntryData.Hawl,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Hawl);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Hawl);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }

        else if (getIntent().getStringExtra("zekr").toString().equals("tasbeeh")) {
            mdhelper.updateContact(contract.EntryData.Tasbeeh,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Tasbeeh);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Tasbeeh);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }
        else if (getIntent().getStringExtra("zekr").toString().equals("hamd")) {
            mdhelper.updateContact(contract.EntryData.Hamd,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Hamd);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Hamd);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }
        else if (getIntent().getStringExtra("zekr").toString().equals("tawheed")) {
            mdhelper.updateContact(contract.EntryData.Tawheed,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Tawheed);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Tawheed);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }
        else if (getIntent().getStringExtra("zekr").toString().equals("takbeer")) {
            mdhelper.updateContact(contract.EntryData.Takbeer,mdhelper,(int)(counter*0.2));
            Cursor cursor=mdhelper.getData(contract.EntryData.Takbeer);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Takbeer);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }


        else if (getIntent().getStringExtra("zekr").toString().equals("sala")) {
            mdhelper.updateContact(contract.EntryData.Sala,mdhelper,(int)(counter*value));
            Cursor cursor=mdhelper.getData(contract.EntryData.Sala);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Sala);
            String col=cursor.getString(currentVal);
            Toast.makeText(AskarCounter.this,col,Toast.LENGTH_LONG).show();
            counter = 0;
            finish();

        }
    }
}
