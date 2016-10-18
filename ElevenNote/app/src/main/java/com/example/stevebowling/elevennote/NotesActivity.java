package com.example.stevebowling.elevennote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    private ListView notesList;
    String [] notes = new String[]{"note 1", "note2", "note3"};
    private NotesArrayAdapter notesArrayAdapter;
    private ArrayList<Note> notesArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

       setupNotes();


       notesList =(ListView)  findViewById(R.id.listView);
        notesArrayAdapter = new NotesArrayAdapter(this, R.layout.notes_list_item, notesArray);
        notesList.setAdapter(notesArrayAdapter);

       //notesList.setAdapter( new ArrayAdapter<>(this,R.layout.notes_textview_list_item, notes));

    }

    private void setupNotes() {
        notesArray = new ArrayList<>();
        Note note1 = new Note("Note 1", "This is a note", new Date());
        notesArray.add(note1);
        notesArray.add(new Note("Note 2", "This is another note", new Date()));
        notesArray.add(new Note("Note 3", "This is another note", new Date()));
    }
}
