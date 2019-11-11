package com.example.apurvbanka.sqlitedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editcollegeID,editemail,editpassword,editcontact;
    Button btnAddData,btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText);
        editcollegeID = (EditText) findViewById(R.id.editText2);
        editemail = (EditText) findViewById(R.id.editText4);
        editpassword = (EditText) findViewById(R.id.editText5);
        editcontact = (EditText) findViewById(R.id.editText6);
        btnAddData = (Button) findViewById(R.id.button);
        btn = (Button) findViewById(R.id.button3);
        AddData();
        ViewData();

    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInsert = myDb.insertData(editName.getText().toString(),editcollegeID.getText().toString(),editemail.getText().toString(),editpassword.getText().toString(),editcontact.getText().toString());

                        if(isInsert==true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void ViewData(){
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                    }
                }
        );
    }
}
