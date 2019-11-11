package com.example.apurvbanka.sqlitedb;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Main5Activity extends AppCompatActivity {

    DatabaseHelper_3 myDb3;
    Button btn,btn1;
    String coll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.apurvbanka.sqlitedb", Context.MODE_PRIVATE);

        coll = sharedPreferences.getString("college","");
        myDb3 = new DatabaseHelper_3(this);

        btn = (Button)findViewById(R.id.button7);
        btn1 = (Button)findViewById(R.id.button8);

        getJuly();
        getAug();

    }

    public void getJuly(){
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res3 = myDb3.viewData3();
                        StringBuffer buffer = new StringBuffer();


                        while(res3.moveToNext()){
                            if(Objects.equals(res3.getString(0),coll.toString())){
                                buffer.append(res3.getString(1)+"\n");

                            }
                        }
                        showMessage("Days Present",buffer.toString());
                    }
                }
        );
    }
    public void getAug(){
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StringBuffer buffer1 = new StringBuffer();
                        showMessage("Days Present",buffer1.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
