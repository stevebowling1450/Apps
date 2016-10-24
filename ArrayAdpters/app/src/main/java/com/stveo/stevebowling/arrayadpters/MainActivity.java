package com.stveo.stevebowling.arrayadpters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private ListView List;
    String [] list = new String[]{"list1", "list2","list3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List =(ListView) findViewById(R.id.listview);
        List.setAdapter(new ArrayAdapter<>(this,R.layout.item,list));

    }


}
