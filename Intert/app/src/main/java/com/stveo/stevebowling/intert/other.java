package com.stveo.stevebowling.intert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by stevebowling on 10/21/16.
 */

public class other extends AppCompatActivity {
    private Button Return;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        intent = this.getIntent();
        intent.putExtra("data", "Howdy");
        setResult(RESULT_OK, intent);

                Return = (Button)findViewById(R.id.goback);
                Return.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
