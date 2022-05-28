package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class firebaseAdd extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_add);

        Button fbAddStudent = findViewById(R.id.fbAddStudent);
        Button fbAdd_fbMain = findViewById(R.id.fbAdd_fbMain);

        EditText student_id = findViewById(R.id.fb_idEditText);
        EditText student_name = findViewById(R.id.fb_nameEditText);
        EditText student_fatherName = findViewById(R.id.fb_fathernameEditText);
        EditText student_surname = findViewById(R.id.fb_surnameEditText);
        EditText student_natId = findViewById(R.id.fb_natIdEditText);

        Button dateButton = findViewById(R.id.dateButton);
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };

        dateButton.setOnClickListener(view -> new DatePickerDialog(firebaseAdd.this, d,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show());


        RadioButton male = findViewById(R.id.fb_male);
        RadioButton female = findViewById(R.id.fb_female);

        fbAddStudent.setOnClickListener(view -> {
            String id = student_id.getText().toString();
            String name = student_name.getText().toString();
            String fatherName = student_fatherName.getText().toString();
            String surname = student_surname.getText().toString();
            String natId = student_natId.getText().toString();
            String dob = fmtDate.format(c.getTime());
            String gender;

            if (id.isEmpty() || name.isEmpty() || fatherName.isEmpty() || surname.isEmpty() || natId.isEmpty() || dob.isEmpty() || male.isChecked() == false && female.isChecked() == false) {
                Toasty.error(getBaseContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT, true).show();
            } else {
                if (male.isChecked()) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }

                insertStudent(id, name, fatherName, surname, natId, dob, gender);
                Toasty.success(getBaseContext(), "Added Successfully", Toast.LENGTH_SHORT, true).show();
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fbAdd_fbMain.setOnClickListener(view -> {
            startActivity(new Intent(firebaseAdd.this, firebaseMain.class));
        });
    }

    private void insertStudent(String id,
                               String name,
                               String surname,
                               String fatherName,
                               String natID,
                               String dob,
                               String gender) {
        Student student = new Student(id, name, surname, fatherName, natID, dob, gender);
        dbRef.child("Student").child(id).setValue(student);
    }

    public class Student {
        public String id;
        public String name;
        public String fatherName;
        public String surname;
        public String natID;
        public String dob;
        public String gender;

        public Student(String id, String name, String fatherName, String surname, String natID, String dob, String gender) {
            this.id = id;
            this.name = name;
            this.fatherName = fatherName;
            this.surname = surname;
            this.natID = natID;
            this.dob = dob;
            this.gender = gender;
        }
    }
}