package com.example.ahmed.muslam;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PrayItem extends AppCompatActivity {
    RadioGroup radioGroup;
    Spinner sona;
    int radioVal = 0; //get radio button value
    int shafaAndWaterVal = 0; //shaf and el water value
    CheckBox shafaAndW; //shafaa and water
    int TotalVal=0;
    DatabaseHelper mdhelper;
    TextView header;
    Integer[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray_item);











        Calendar calendar=Calendar.getInstance();
        try {
            header = (TextView) findViewById(R.id.header);
          // Toast.makeText(this, calendar.get(Calendar.HOUR)+" / "+calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("pray", "onCreate: ",e );
        }
        mdhelper = new DatabaseHelper(this);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        shafaAndW = (CheckBox) findViewById(R.id.checkBox);
        sona = (Spinner) findViewById(R.id.spinner);
        try {
            setHeader();
        } catch (Exception e) {
            Log.e("sss", "onCreate: ", e);
        }








/*
        //spinner Array
        arr = new Integer[]{0,2};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sona.setAdapter(adapter);
*/
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
                        if(getIntent().getStringExtra("sala").toString().equals("maghrib"))
                            radioVal = 400;
                        else
                            radioVal = 300;
                        break;
                }
            }
        });
    }


    //insert Data to DB
    public void insertData(String COLUMN) {
        //to get current day

        Calendar calendar = Calendar.getInstance();
        //set shafa and water value
        if (shafaAndW.isChecked() == true) {
            shafaAndWaterVal = 3;
        }
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN, radioVal + shafaAndWaterVal + Integer.valueOf(sona.getSelectedItem().toString()));


        long newRowId = db.insert(contract.EntryData.table_name, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error in saving ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data saved successfully Row where saved is : " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    //test for read data base
    private Cursor read() {
        SQLiteDatabase db = mdhelper.getReadableDatabase();

        String[] projection = {
                contract.EntryData.fajr,
                contract.EntryData.Asr,
                contract.EntryData.Eshaa
        };

        Cursor cursor = db.query(contract.EntryData.table_name, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabase() {
        Cursor cursor = read();
        int idColumnIndex = cursor.getColumnIndex(contract.EntryData.fajr);

        int currentID = cursor.getInt(idColumnIndex);
        //Toast.makeText(this, String.valueOf(currentID), Toast.LENGTH_SHORT).show();
    }


    //set header
    public void setHeader() {
        if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.fajr)) {
            header.setText("Fagr");
            //set Spinner
            arr = new Integer[]{0,2};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
            sona.setAdapter(adapter);
        } else if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.Doha)) {
            header.setText("Doha");
        } else if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.Zuhr)) {
            header.setText("Zuhr");
            //set Spinner
            arr = new Integer[]{0,2,4,6};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
            sona.setAdapter(adapter);
        } else if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.Asr)) {
            header.setText("Asr");
            //set Spinner
            arr = new Integer[]{0,2};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
            sona.setAdapter(adapter);
        }
        if (getIntent().getStringExtra("sala").toString().equals("maghrib")) {
            header.setText("Maghreb");
            //set Spinner
            arr = new Integer[]{0,2};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
            sona.setAdapter(adapter);
        } else if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.Eshaa)) {
            header.setText("Eshaa");
            //set Spinner
            arr = new Integer[]{0,2};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
            sona.setAdapter(adapter);
        } else if (getIntent().getStringExtra("sala").toString().equals(contract.EntryData.Qiam)) {
            header.setText("Qiam");
        }
    }

    public void saveData(View view) {


       TotalVal= radioVal+Integer.valueOf(sona.getSelectedItem().toString());
        //insert or update FAGR data to this user in DB
        if (getIntent().getStringExtra("sala").toString().equals("fajr")) {
            mdhelper.updateContact(contract.EntryData.fajr,mdhelper,TotalVal);
            Cursor cursor=mdhelper.getData(contract.EntryData.fajr);
            int currentVal=cursor.getColumnIndex(contract.EntryData.fajr);
            String col=cursor.getString(currentVal);
            Toast.makeText(PrayItem.this,col,Toast.LENGTH_LONG).show();
            finish();

        }
        //insert or update Doha data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("doha")) {

        }
        //insert or update Zuhr data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("zuhr")) {
            mdhelper.updateContact(contract.EntryData.Zuhr,mdhelper,TotalVal);
            Cursor cursor=mdhelper.getData(contract.EntryData.Zuhr);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Zuhr);
            String col=cursor.getString(currentVal);
            Toast.makeText(PrayItem.this,col,Toast.LENGTH_LONG).show();
            finish();
        }
        //insert or update Asr data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("asr")) {
            mdhelper.updateContact(contract.EntryData.Asr,mdhelper,TotalVal);
            Cursor cursor=mdhelper.getData(contract.EntryData.Asr);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Asr);
            String col=cursor.getString(currentVal);
            Toast.makeText(PrayItem.this,col,Toast.LENGTH_LONG).show();
            finish();
        }
        //insert or update Maghreb data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("maghrib")) {
            mdhelper.updateContact(contract.EntryData.Maghreb,mdhelper,TotalVal);
            Cursor cursor=mdhelper.getData(contract.EntryData.Maghreb);
            int currentVal=cursor.getColumnIndex(contract.EntryData.Maghreb);
            String col=cursor.getString(currentVal);
            Toast.makeText(PrayItem.this,col,Toast.LENGTH_LONG).show();
            finish();
        }
        //insert or update Eshaaa data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("eshaa")) {

        }
        //insert or update Qiam data to this user in DB
        else if (getIntent().getStringExtra("sala").toString().equals("qiam")) {

        } else {
            Toast.makeText(this, "Error!! plz check your Data", Toast.LENGTH_SHORT).show();
        }


    }
// اعتبره كويري وراح
  /*  public void Check(String Contract){
        Calendar calendar=Calendar.getInstance();
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //long newRowId = db.insert(contract.EntryData._ID, null, values);
        if(==-1){
            Toast.makeText(this, "Empty init", Toast.LENGTH_SHORT).show();
            values.put(contract.EntryData.date,calendar.get(Calendar.DATE));
            newRowId = db.insert(contract.EntryData.table_name, null, values);
        }
        else {
            Toast.makeText(this, "not Empty ", Toast.LENGTH_SHORT).show();
            String date= DateFormat.getDateInstance().format(calendar.getTime());
        values.put(contract.EntryData.date,date);
           db.execSQL("insert into "+contract.EntryData.table_name+" ("+ contract.EntryData.date+")"+" values("+date+") "+" where " +contract.EntryData.date+"<>"+date+" AND "+calendar.get(Calendar.HOUR)+"="+3);
      //  }

    }*/
}

