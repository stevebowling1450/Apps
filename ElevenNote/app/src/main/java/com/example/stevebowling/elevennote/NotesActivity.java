package com.example.stevebowling.elevennote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Note note = notesArray.get(position);

                Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
                intent.putExtra("Title",note.getTitle());
                intent.putExtra("Text",note.getText());
                intent.putExtra("Index",position);

                startActivityForResult(intent, 1);


            }
        });


       //notesList.setAdapter( new ArrayAdapter<>(this,R.layout.notes_textview_list_item, notes));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int index = data.getIntExtra("Index", -1);
            Note note = new Note(data.getStringExtra("Title"),
                    data.getStringExtra("Text"), new Date());
            if (index== -1) {
                notesArray.add(note);
            }
                else {
                notesArray.set(index, note);
            }
            notesArrayAdapter.updateAdapter(notesArray);
        }
    }

    private void setupNotes() {
        notesArray = new ArrayList<>();
        Note note1 = new Note("Note 1", "This is a note", new Date());
        notesArray.add(note1);
        notesArray.add(new Note("Note 2", "This is another note", new Date()));
        notesArray.add(new Note("Note 3", "This is another note", new Date()));
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
            intent.putExtra("Title", "");
            intent.putExtra("Text", "");

                    startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
