package com.stveo.stevebowling.elevenmapper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.webkit.WebView;

/**
 * Created by stevebowling on 10/21/16.
 */

public class DirectionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_directions);

        Intent myIntent = this.getIntent();
        String HtmlDir = myIntent.getStringExtra("Directions");

        WebView myWebView = (WebView)findViewById(R.id.webview);
       // myWebView.loadData(HtmlDir, "text/html", "UTF-8");
        myWebView.loadUrl("http//www.yahoo.com");


    }
}
