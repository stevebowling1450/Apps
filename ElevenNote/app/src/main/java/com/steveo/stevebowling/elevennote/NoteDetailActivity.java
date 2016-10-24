package com.steveo.stevebowling.elevennote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by stevebowling on 10/18/16.
 */

public class NoteDetailActivity extends AppCompatActivity {


    public EditText noteTitle;
    public EditText noteText;
    public Button saveButton;
    public int index;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        noteTitle = (EditText)findViewById(R.id.note_title);
        noteText = (EditText)findViewById(R.id.note_text);
        saveButton = (Button)findViewById(R.id.save_button);

        Intent intent = getIntent();

        noteTitle.setText(intent.getStringExtra("Title"));
        noteText.setText(intent.getStringExtra("Text"));
        index= intent.getIntExtra("Index", -1);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("Title", noteTitle.getText().toString());
                intent.putExtra("Text", noteText.getText().toString());
                intent.putExtra("Index", index);
                setResult(RESULT_OK, intent);
                finish();
            }
            })










    ;}
}




