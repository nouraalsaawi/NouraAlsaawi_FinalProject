package com.example.nouraalsaawisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class city_weather extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();

        EditText cityTf = findViewById(R.id.cityTf);
        Button changeCity = findViewById(R.id.changeCity);

        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityTf.getText().toString();

                if (TextUtils.isEmpty(city))
                    cityTf.setError("New Name is required!");
                else {
                    editor.clear();
                    editor.putString("city", city);
                    editor.commit();

                    Toasty.success(getBaseContext(), "Changed City successfully", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}