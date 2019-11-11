package com.example.apurvbanka.sqlitedb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText collegeID,pass;
    Button btn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDb = new DatabaseHelper(this);
        btn = (Button) findViewById(R.id.button2);
        collegeID = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText7);
//        Toast.makeText(this, collegeID.getText().toString(), Toast.LENGTH_SHORT).show();


        getAllData();
    }

        public void getAllData(){
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.apurvbanka.sqlitedb", Context.MODE_PRIVATE);

                        sharedPreferences.edit().putString("college", collegeID.getText().toString()).apply();

                        String coll = sharedPreferences.getString("college","");
                        Cursor res = myDb.getAllData();
//                        Log.v("college",collegeID.getText().toString());
//                        if(res.getCount() == 0){
//                            Toast.makeText(Main2Activity.this, "No Data", Toast.LENGTH_SHORT).show();
//                            return;
//                        }

                        int i=0;
                        while(res.moveToNext()){
                            if(Objects.equals(res.getString(2).toString(),collegeID.getText().toString()) && Objects.equals(res.getString(4).toString(),pass.getText().toString())){
                                i=i+1;
                            }
                        }
                        if(i>0){
//                            Toast.makeText(Main2Activity.this,"Welcome!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Main3Activity.class));}
                        else {
                            Toast.makeText(Main2Activity.this, "CollegeID or Password is Incorrect", Toast.LENGTH_SHORT).show();
                            if (Objects.equals(collegeID.getText().toString(), "admin") && Objects.equals(pass.getText().toString(), "admin")) {
                                startActivity(new Intent(getApplicationContext(), Main4Activity.class));
                            }
                        }
                    }


                }
        );
    }





}
