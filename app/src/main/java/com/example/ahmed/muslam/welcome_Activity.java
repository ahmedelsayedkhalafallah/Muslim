package com.example.ahmed.muslam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class welcome_Activity extends AppCompatActivity {
    EditText name, pass;
    DatabaseHelper dbhelber;
    String nameT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);


        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);

        try {
            readeFile();
            if (!nameT.trim().equals("")){
                startActivity(new Intent(welcome_Activity.this,Main_Activity.class));
            finish();
            }
        }catch (Exception e){
            Log.e("main", "onCreate: ",e );
        }
        dbhelber = new DatabaseHelper(this);
    }

    //save in file to check on it
    public void saveInFile(){
        SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",name.getText().toString()+"_"+pass.getText().toString());
        editor.commit();
    }


    public void readeFile(){
        SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String val="";
        nameT=sharedPreferences.getString("name",val);
    }

    public void submit(View view) {
        if(!name.getText().toString().trim().equals("")||!pass.getText().toString().trim().equals("")) {
            saveInFile();
            startActivity(new Intent(welcome_Activity.this, Main_Activity.class));
            finish();
        }else Toast.makeText(this, "please enter your name!", Toast.LENGTH_SHORT).show();
    }
}
