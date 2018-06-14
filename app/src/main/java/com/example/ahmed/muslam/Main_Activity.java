package com.example.ahmed.muslam;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;

public class Main_Activity extends AppCompatActivity {
    Button askarBtn, prayesBtn, quranBtn;
    DatabaseHelper helper ,mdhelper;
    SQLiteDatabase db;
    static String date, d;
    TextView t, total;
    String nameT;
    ArrayList<String> listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        mdhelper = new DatabaseHelper(this);
        t = findViewById(R.id.textView3);
        total = findViewById(R.id.total);
        Calendar calendar = Calendar.getInstance();
        d = DateFormat.getDateInstance().format(calendar.getTime());
        t.setText(d);

        welcome_Activity x = new welcome_Activity();
        x.finish();

        Cursor cursor=mdhelper.getData(contract.EntryData.Total);
        int currentVal=cursor.getColumnIndex(contract.EntryData.Total);
        //Toast.makeText(Main_Activity.this,String.valueOf(currentVal),Toast.LENGTH_LONG).show();
        if(currentVal > 0) {
            String col = cursor.getString(currentVal);
            total.setText(String.valueOf(col));
        }




        // create database
        helper = new DatabaseHelper(this);
        //check if it is a new day
        check();


        //buttons casting open other activities
//         Bind
        askarBtn = findViewById(R.id.askar);
        prayesBtn = findViewById(R.id.pray);
        quranBtn = findViewById(R.id.quran);


//        Listener
        askarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent AskarIntent = new Intent(Main_Activity.this, AskarActivity.class);
                startActivity(AskarIntent);
            }
        });


        prayesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent prayesIntent = new Intent(Main_Activity.this, PrayActivity.class);
                startActivity(prayesIntent);
            }
        });
        quranBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quranIntent = new Intent(Main_Activity.this, quran.class);
                startActivity(quranIntent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor=mdhelper.getData(contract.EntryData.Total);
        int currentVal=cursor.getColumnIndex(contract.EntryData.Total);
        //Toast.makeText(Main_Activity.this,String.valueOf(currentVal),Toast.LENGTH_LONG).show();
       // if(currentVal > 0) {
            String col = cursor.getString(currentVal);
            total.setText("Total :"+String.valueOf(col));
       // }
    }

    //update colo


    public void check() {
        Calendar calendar = Calendar.getInstance();
        date = DateFormat.getDateInstance().format(calendar.getTime());


        Cursor rs = helper.getData();


//        db.execSQL("insert into "+ contract.EntryData.table_name+ " values ( 1,'"+date+"','mmmmmm');" );


        Cursor s = helper.getData();
        s.moveToLast();
        int count = s.getPosition();

        if (count == -1) {
            helper.insertContact(date);
        }


        //int num= helper.numberOfRows();

        //Toast.makeText(Main_Activity.this, String.valueOf(count), Toast.LENGTH_LONG).show();


        Cursor cursor = helper.getData();
        int dateeee = cursor.getColumnIndex(contract.EntryData.date);

        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

        if (!date.equals(cursor.getString(dateeee)) && currentHour >= 3) {
            //Toast.makeText(Main_Activity.this, "new day", Toast.LENGTH_LONG).show();
            helper.insertContact(date);

        }


    }

    public void rep(View view) {
        Intent intent = new Intent(this,report.class);
        startActivity(intent);
    }


    private class ExportDatabaseCSVTask extends AsyncTask<String, String, String> {
        private final ProgressDialog dialog = new ProgressDialog(Main_Activity.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected String doInBackground(final String... args) {
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            readeFile();
            File file = new File(exportDir, nameT+".csv");
            try {

                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

                //data
                listdata = new ArrayList<String>();
                String[] arrStr1 = new String[]{"ID", "Date", "Asr", "Maghreb", "Doha", "Eshaa", "Fajr", "Zuhr", "Hamd", "Esteghfar", "Takbeer"};
                csvWrite.writeNext(arrStr1);
                //get data
                displayDatabase();
                // String arrStr1[] = {"First Name", "Last Name", "Address", "Email"};
                String arrStr[] = {listdata.get(0), listdata.get(1), listdata.get(2), listdata.get(3), listdata.get(4), listdata.get(5), listdata.get(6), listdata.get(7)
                        , listdata.get(8), listdata.get(9), listdata.get(10)};
                csvWrite.writeNext(arrStr);

                csvWrite.close();
                return "";
            } catch (IOException e) {
                Log.e("MainActivity", e.getMessage(), e);
                return "";
            }
        }

        @SuppressLint("NewApi")
        @Override
        protected void onPostExecute(final String success) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success.isEmpty()) {
                Toast.makeText(Main_Activity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Main_Activity.this, "Export failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //export DB
    private Cursor read() {
        SQLiteDatabase db = mdhelper.getReadableDatabase();

        String[] projection = {
                contract.EntryData._ID,
                contract.EntryData.date,
                contract.EntryData.Asr,
                contract.EntryData.Maghreb,
                contract.EntryData.Doha,
                contract.EntryData.Eshaa,
                contract.EntryData.fajr,
                contract.EntryData.Zuhr,
                contract.EntryData.Hamd,
                contract.EntryData.Esteghfar,
                contract.EntryData.Takbeer
        };

        Cursor cursor = db.query(contract.EntryData.table_name, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabase() {
        Cursor cursor = read();

        try {

            int id = cursor.getColumnIndex(contract.EntryData._ID);
            int dateindex = cursor.getColumnIndex(contract.EntryData.date);
            int asr = cursor.getColumnIndex(contract.EntryData.Asr);
            int maghreb = cursor.getColumnIndex(contract.EntryData.Maghreb);
            int doha = cursor.getColumnIndex(contract.EntryData.Doha);
            int eshaa = cursor.getColumnIndex(contract.EntryData.Eshaa);
            int fajr = cursor.getColumnIndex(contract.EntryData.fajr);
            int zuhr = cursor.getColumnIndex(contract.EntryData.Zuhr);
            int Hamd = cursor.getColumnIndex(contract.EntryData.Hamd);
            int Esteghfar = cursor.getColumnIndex(contract.EntryData.Esteghfar);
            int Takbeer = cursor.getColumnIndex(contract.EntryData.Takbeer);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(id);
                String date = cursor.getString(dateindex);
                int currentAsr = cursor.getInt(asr);
                int currentMaghreb = cursor.getInt(maghreb);
                int Currentdoha = cursor.getInt(doha);
                int Currenteshaa = cursor.getInt(eshaa);
                String currentfajr = cursor.getString(fajr);
                int cursorZuhr = cursor.getInt(zuhr);
                int CurrentHamd = cursor.getInt(Hamd);
                int CurrentEsteghraf = cursor.getInt(Esteghfar);
                int CurrentTakbeer = cursor.getInt(Takbeer);
                //set data base data
                listdata.add(String.valueOf(currentID));
                listdata.add(date);
                listdata.add(String.valueOf(currentAsr));
                listdata.add(String.valueOf(currentMaghreb));
                listdata.add(String.valueOf(Currentdoha));
                listdata.add(String.valueOf(Currenteshaa));
                listdata.add(String.valueOf(currentfajr));
                listdata.add(String.valueOf(cursorZuhr));
                listdata.add(String.valueOf(CurrentHamd));
                listdata.add(String.valueOf(CurrentEsteghraf));
                listdata.add(String.valueOf(CurrentTakbeer));
            }
        } finally {
            cursor.close();
        }
    }
    public void readeFile(){
        SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String val="";
        nameT=sharedPreferences.getString("name",val);
    }
}