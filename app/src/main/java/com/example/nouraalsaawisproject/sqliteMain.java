package com.example.nouraalsaawisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class sqliteMain extends AppCompatActivity {
    String weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=berlin&APPID=237ebccfb786de76125153dd53ba61cf&units=metric";
    JSONObject jsonObj;
    TextView city, temp, humid, clouds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

        Button addButton = findViewById(R.id.sqlAddButton);
        Button updateButton = findViewById(R.id.sqlUpdateButton);
        Button deleteButton = findViewById(R.id.sqlDeleteButton);
        Button viewAllButton = findViewById(R.id.sqlViewAllButton);

        Button sqlMain_homepage = findViewById(R.id.sqlMain_homepage);

        sqlMain_homepage.setOnClickListener(view -> startActivity(new Intent(sqliteMain.this, MainActivity.class)));

        addButton.setOnClickListener(view -> startActivity(new Intent(sqliteMain.this, sqlAdd.class)));

        updateButton.setOnClickListener(view -> startActivity(new Intent(sqliteMain.this, sqlUpdate.class)));
        deleteButton.setOnClickListener(view -> startActivity(new Intent(sqliteMain.this, sqlDelete.class)));

        viewAllButton.setOnClickListener(view -> startActivity(new Intent(sqliteMain.this, sqlViewAll.class)));

        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        humid = findViewById(R.id.humid);
        clouds = findViewById(R.id.clouds);

        SharedPreferences prefs = getSharedPreferences(
                "my.app.packagename_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("city", "berlin");
        editor.apply();

        String citySP = prefs.getString("city", null);
        citySP = citySP.substring(0, 1).toUpperCase() + citySP.substring(1);
        city.setText(citySP);
        weather_report(weatherWebserviceURL);
    }

    public void weather_report(String url) {
        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, response -> {
                    try {
                        JSONObject mainJ = response.getJSONObject("main");
                        JSONObject cloudsJ = response.getJSONObject("clouds");

                        double temp1 = mainJ.getDouble("temp");
                        temp.setText(String.format("%.2f", temp1) + " °C");

                        double hum = mainJ.getDouble("humidity");
                        humid.setText(hum + " %");

                        double cloud = cloudsJ.getDouble("all");
                        clouds.setText(String.valueOf(cloud));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {

                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String cityStoredData = sp.getString("city",null);
        cityStoredData = cityStoredData.substring(0, 1).toUpperCase() + cityStoredData.substring(1);
        city.setText(cityStoredData);

        weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q="+cityStoredData+"&APPID=237ebccfb786de76125153dd53ba61cf&units=metric";
        weather_report(weatherWebserviceURL);
    }
}