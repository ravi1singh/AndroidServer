package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    Button sendBtn, acceptBtn;
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.portEt);
    }
    public void start(View v){
//        Server server = new Server(5000);
        String portNumber = et.getText().toString();
        System.out.println(portNumber);
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("portNumber", portNumber);
        startService(serviceIntent);
    }
    public void stop(View v){
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}