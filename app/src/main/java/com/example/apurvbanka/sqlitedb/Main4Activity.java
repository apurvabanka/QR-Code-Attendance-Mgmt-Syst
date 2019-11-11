package com.example.apurvbanka.sqlitedb;

import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText date,code;
    Button button,viewButton;
    DatabaseHelper_2 myDb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        myDb2 = new DatabaseHelper_2(this);

        date = (EditText) findViewById(R.id.editText8);
        code = (EditText) findViewById(R.id.editText9);

        button = (Button)findViewById(R.id.button4);
        viewButton = (Button)findViewById(R.id.button6);

        addData();
        viewAllArg();

    }
    public void addData(){
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInsert = myDb2.insertData2(date.getText().toString(),code.getText().toString());

                        if(isInsert==true)
                            Toast.makeText(Main4Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Main4Activity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
    public void viewAllArg(){
        viewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res2 = myDb2.viewData();
                        if (res2.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res2.moveToNext()){
                            buffer.append("Date : "+res2.getString(0)+"\n");
                            buffer.append("Code : "+res2.getString(1)+"\n");

                        }
                        showMessage("Data",buffer.toString());
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
