package com.example.bcy.constellation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListExplainActivity  extends AppCompatActivity {

    TextView location, content;
    String locationStr, contentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page_explain);

        location = findViewById(R.id.location_id);
        content = findViewById(R.id.content_id);
        locationStr = ListActivity.location;
        contentStr = ListActivity.content;
        location.setText(locationStr);
        content.setText(contentStr);

        location.setMovementMethod(new ScrollingMovementMethod());
        content.setMovementMethod(new ScrollingMovementMethod());
    }

}