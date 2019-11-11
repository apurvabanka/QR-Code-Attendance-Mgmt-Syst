package com.example.apurvbanka.sqlitedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btn1=(Button) findViewById(R.id.button9);
        btn2=(Button) findViewById(R.id.button10);
        login();
        register();
    }
    public void login()
    {
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                    }
                }
        );
    }

    public void register()
    {
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }
        );
    }

}
