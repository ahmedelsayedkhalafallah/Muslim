package com.example.ahmed.muslam;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class report extends AppCompatActivity {

    DatabaseHelper mdhelper;
    static String d;
    TextView t, total, sala, quraan, azkar,name;
    String nameT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mdhelper = new DatabaseHelper(this);
        t = findViewById(R.id.time);
        total = findViewById(R.id.total);
        sala = findViewById(R.id.sala);
        quraan = findViewById(R.id.quraan);
        azkar = findViewById(R.id.azkar);
        name = findViewById(R.id.name);
        Calendar calendar = Calendar.getInstance();
        d = DateFormat.getDateInstance().format(calendar.getTime());
        t.setText(d);


        Cursor cursor=mdhelper.getData(contract.EntryData.Total);
        int currentVal=cursor.getColumnIndex(contract.EntryData.Total);
        //Toast.makeText(Main_Activity.this,String.valueOf(currentVal),Toast.LENGTH_LONG).show();
        // if(currentVal > 0) {
        String col = cursor.getString(currentVal);
        total.setText("Total :"+String.valueOf(col));
        // }
        readeFile();
        name.setText(nameT);
        String values[]=new String[3];
        displayDatabase(values);
        sala.setText("Pray total: "+ values[0]);
        quraan.setText("Quraan total: "+ values[1]);
        azkar.setText("Azkar total: "+ values[2]);




    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        String values[]=new String[3];
//        displayDatabase(values);
//        Toast.makeText(report.this,values[0], Toast.LENGTH_LONG).show();
//
//    }

    public void displayDatabase(String values[]) {
        //salah total
        int salatotal = 0;
        Cursor cursor=mdhelper.getData(contract.EntryData.fajr);
        int currentVal=cursor.getColumnIndex(contract.EntryData.fajr);
        String col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Doha);
        currentVal=cursor.getColumnIndex(contract.EntryData.Doha);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Zuhr);
        currentVal=cursor.getColumnIndex(contract.EntryData.Zuhr);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Asr);
        currentVal=cursor.getColumnIndex(contract.EntryData.Asr);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Maghreb);
        currentVal=cursor.getColumnIndex(contract.EntryData.Maghreb);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Eshaa);
        currentVal=cursor.getColumnIndex(contract.EntryData.Eshaa);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Qiam);
        currentVal=cursor.getColumnIndex(contract.EntryData.Qiam);
        col = cursor.getString(currentVal);
        salatotal+= Integer.valueOf(col);


        //quraan total
        int quraantotal=0;
        cursor=mdhelper.getData(contract.EntryData.Quraan);
        currentVal=cursor.getColumnIndex(contract.EntryData.Quraan);
        col = cursor.getString(currentVal);
        quraantotal+= Integer.valueOf(col);
        cursor=mdhelper.getData(contract.EntryData.Kahf);
        currentVal=cursor.getColumnIndex(contract.EntryData.Kahf);
        col = cursor.getString(currentVal);
        quraantotal+= Integer.valueOf(col);


        //Azkar total
        int azkartotal = 0;
        cursor=mdhelper.getData(contract.EntryData.Sabah);
        currentVal=cursor.getColumnIndex(contract.EntryData.Sabah);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);
        cursor=mdhelper.getData(contract.EntryData.Masaa);
        currentVal=cursor.getColumnIndex(contract.EntryData.Masaa);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Hawl);
        currentVal=cursor.getColumnIndex(contract.EntryData.Hawl);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Tasbeeh);
        currentVal=cursor.getColumnIndex(contract.EntryData.Tasbeeh);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Tawheed);
        currentVal=cursor.getColumnIndex(contract.EntryData.Tawheed);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Sala);
        currentVal=cursor.getColumnIndex(contract.EntryData.Sala);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Hamd);
        currentVal=cursor.getColumnIndex(contract.EntryData.Hamd);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Esteghfar);
        currentVal=cursor.getColumnIndex(contract.EntryData.Esteghfar);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        cursor=mdhelper.getData(contract.EntryData.Takbeer);
        currentVal=cursor.getColumnIndex(contract.EntryData.Takbeer);
        col = cursor.getString(currentVal);
        azkartotal+= Integer.valueOf(col);

        values[0] = String.valueOf(salatotal);
        values[1] = String.valueOf(quraantotal);
        values[2] = String.valueOf(azkartotal);
    }
    public void readeFile(){
        SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String val="";
        nameT=sharedPreferences.getString("name",val);
    }
}
