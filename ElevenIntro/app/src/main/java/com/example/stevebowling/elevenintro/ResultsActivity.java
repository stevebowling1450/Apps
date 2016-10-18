package com.example.stevebowling.elevenintro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stevebowling on 10/17/16.
 */

public class ResultsActivity extends AppCompatActivity {

    TextView resultText;
    Button resumeButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);
        Log.d("------", "onCreate RESULT SCREEN");

        resultText= (TextView) findViewById(R.id. resultText);
        resumeButton= (Button) findViewById(R.id.resumeButton);


        resumeButton.setOnClickListener(resumeButtonOnClick);

        Intent i = this.getIntent();
        char symbol = i.getCharExtra("char", ' ');

        resultText.setText("The winner is " + symbol);

    }
    private View.OnClickListener resumeButtonOnClick =new View.OnClickListener(){

        public void onClick (View v ){
            
        }

    };



}
