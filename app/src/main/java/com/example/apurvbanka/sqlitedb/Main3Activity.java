package com.example.apurvbanka.sqlitedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Main3Activity extends AppCompatActivity {

    DatabaseHelper myDb;
    DatabaseHelper_2 my_Db2;
    DatabaseHelper_3 my_Db3;
    private Button scan_btn,check1;
    String coll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        scan_btn = (Button) findViewById(R.id.scan_btn);
        check1 = (Button) findViewById(R.id.button5);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });


        myDb = new DatabaseHelper(this);


        Cursor res = myDb.getAllData();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.apurvbanka.sqlitedb", Context.MODE_PRIVATE);

        String coll = sharedPreferences.getString("college","");

        while(res.moveToNext()){
            if(Objects.equals(res.getString(2).toString(),coll)){
                Toast.makeText(this, "Welcome " + res.getString(1).toString(), Toast.LENGTH_SHORT).show();
            }
        }
        aug();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.apurvbanka.sqlitedb",Context.MODE_PRIVATE);

//        sharedPreferences.edit().putString("college",result.getContents()).apply();

        coll = sharedPreferences.getString("college","");
        my_Db2 = new DatabaseHelper_2(this);
        my_Db3 = new DatabaseHelper_3(this);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Cursor res2 = my_Db2.viewData();
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                while (res2.moveToNext()) {
                    if (Objects.equals(result.getContents().toString(), res2.getString(1).toString()))
                        if (Objects.equals(date.toString(), res2.getString(0).toString())) {
                            boolean isInsert = my_Db3.insertData3(coll.toString());
                            if (isInsert==true){
                                Toast.makeText(this, "Attendence Marked", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(this, "Some error Ocured", Toast.LENGTH_SHORT).show();
                        }
//                    else {
//                        Toast.makeText(this, "Some error Ocured", Toast.LENGTH_SHORT).show();
//                    }
//                    Toast.makeText(this, res2.getString(1).toString(), Toast.LENGTH_SHORT).show();


                }
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }

//        Log.i("url",url);
    }
    public void aug(){
        check1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),Main5Activity.class));
                    }
                }
        );
    }
}