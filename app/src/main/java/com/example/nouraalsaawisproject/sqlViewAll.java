package com.example.nouraalsaawisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class sqlViewAll extends ListActivity {
    ArrayList<String> students = new ArrayList<>();
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_view_all);
        dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.ViewList();

        while (cursor.moveToNext()) {
            students.add("id: " + cursor.getString(1)
                    + "\n" + ("Name: " + cursor.getString(2)
                    + "\n" + ("Father Name: " + cursor.getString(3)
                    + "\n" + ("Surname: " + cursor.getString(4)
                    + "\n" + ("National ID: " + cursor.getString(5)
                    + "\n" + ("Date of Birth: " + cursor.getString(6)
                    + "\n" + ("Gender: " + cursor.getString(7)
            )))))));
        }
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_sql_view_all, R.id.student, students));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String[] selected = students.get(position).split("\n", 6);
        Toasty.info(getBaseContext(), selected[1] + "\n" + selected[2], Toast.LENGTH_SHORT, true).show();
    }
}