package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class sqlAdd extends AppCompatActivity {
    final DatabaseHelper dbHelper = new DatabaseHelper(this);
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_add);

        EditText idTf = findViewById(R.id.sqliAddTf);
        Button add = findViewById(R.id.sqliAdd);


        add.setOnClickListener(view -> {
            String id = idTf.getText().toString();
            if (TextUtils.isEmpty(id))
                idTf.setError("ID is required!");
            else
                add(id);
        });
    }

    private void add(String id) {
        dbRef = db.getReference().child("Student").child(id);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String idS = snapshot.child("id").getValue().toString();
                String nameS = snapshot.child("name").getValue().toString();
                String fatherNameS = snapshot.child("fatherName").getValue().toString();
                String surnameS = snapshot.child("surname").getValue().toString();
                String natIDS = snapshot.child("natID").getValue().toString();
                String dobS = snapshot.child("dob").getValue().toString();
                String genderS = snapshot.child("gender").getValue().toString();

                dbHelper.addStudent(idS, nameS, fatherNameS, surnameS, natIDS, dobS, genderS);
                Toasty.success(getBaseContext(), "Added Student with ID " + idS + " to the local database", Toast.LENGTH_SHORT, true).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getBaseContext(), "Failed to get data", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}