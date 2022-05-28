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

public class firebaseMain extends AppCompatActivity {
    String weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=berlin&APPID=237ebccfb786de76125153dd53ba61cf&units=metric";
    JSONObject jsonObj;
    TextView city, temp, humid, clouds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        Button addButton = findViewById(R.id.firebaseAddButton);
        Button updateButton = findViewById(R.id.firebaseUpdateButton);
        Button deleteButton = findViewById(R.id.firebaseDeleteButton);

        Button searchButton = findViewById(R.id.firebaseSearchButton);
        Button viewAllButton = findViewById(R.id.firebaseViewAllButton);

        Button fbMain_homepage = findViewById(R.id.fbMain_homepage);

        fbMain_homepage.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, MainActivity.class));
        });

        addButton.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, firebaseAdd.class));
        });

        updateButton.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, firebaseUpdate.class));
        });
        deleteButton.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, firebaseDelete.class));
        });

        searchButton.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, firebaseSearch.class));
        });

        viewAllButton.setOnClickListener(view -> {
            startActivity(new Intent(firebaseMain.this, firebaseViewAll.class));
        });

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
                        temp.setText(String.valueOf(String.format("%.2f", temp1)) + " °C");

                        double hum = mainJ.getDouble("humidity");
                        humid.setText(String.valueOf(hum) + " %");

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
    public void onResume() {
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String cityStoredData = sp.getString("city", null);
        cityStoredData = cityStoredData.substring(0, 1).toUpperCase() + cityStoredData.substring(1);
        city.setText(cityStoredData);

        weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=" + cityStoredData + "&APPID=237ebccfb786de76125153dd53ba61cf&units=metric";
        weather_report(weatherWebserviceURL);
    }
}