package com.example.stevebowling.elevenintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("------", "onCreate");
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d("------", "onStart");
    }

    @Override
    protected void onResume(){
        super.onStart();
        Log.d("------", "onResume");
    }
    @Override
    protected void onPause(){
        super.onStart();
        Log.d("------", "onPause");
    }
    @Override
    protected void onStop(){
        super.onStart();
        Log.d("------", "onStop");
    }
    @Override
    protected void onDestroy(){
        super.onStart();
        Log.d("------", "onDestroy");
    }




}
