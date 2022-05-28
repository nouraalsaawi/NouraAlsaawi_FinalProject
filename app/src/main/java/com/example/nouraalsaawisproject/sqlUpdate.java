package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class sqlUpdate extends AppCompatActivity {
    final DatabaseHelper dbHelper = new DatabaseHelper(this);
    String gender = "";
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_update);

        Button sqlUpdateStudent = findViewById(R.id.sqlUpdateStudent);
        EditText student_id = findViewById(R.id.sql_idEditText_update);

        Switch sql_idSW = findViewById(R.id.idSwitchSQLi);
        Switch sql_nameSW = findViewById(R.id.nameSwitchSQLi);
        Switch sql_fathernameSW = findViewById(R.id.fatherNameSwitchSQLi);
        Switch sql_surnameSW = findViewById(R.id.surnameSwitchSQLi);
        Switch sql_natIdSW = findViewById(R.id.natIdSwitchSQLi);
        Switch sql_dobSW = findViewById(R.id.dobSwitchSQLi);
        Switch sql_genderSW = findViewById(R.id.genderSwitchSQLi);

        EditText student_id2 = findViewById(R.id.sql_idEditText_update2);
        EditText student_name = findViewById(R.id.sql_nameEditText_update);
        EditText student_fatherName = findViewById(R.id.sql_fathernameEditText_update);
        EditText student_surname = findViewById(R.id.sql_surnameEditText_update);
        EditText student_natId = findViewById(R.id.sql_natIdEditText_update);

        Button dateButton = findViewById(R.id.sql_dateButton_update);
        c = Calendar.getInstance();
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

        dateButton.setOnClickListener(view -> new DatePickerDialog(sqlUpdate.this, d,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show());

        RadioGroup grp = findViewById(R.id.sqlgrp);
        RadioButton male = findViewById(R.id.sql_male_update);
        RadioButton female = findViewById(R.id.sql_female_update);

        sqlUpdateStudent.setOnClickListener(view -> {
            if (TextUtils.isEmpty(student_id.getText()))
                student_id.setError("ID is required!");

            // id
            if (sql_idSW.isChecked()) {
                if (TextUtils.isEmpty(student_id2.getText().toString()))
                    student_id2.setError("New ID is required!");
                else {
                    dbHelper.updateID(student_id.getText().toString(), student_id2.getText().toString());
                    Toasty.success(getBaseContext(), "Updated ID of Student with ID " + student_id.getText().toString() + " to " + student_id2.getText().toString(), Toast.LENGTH_SHORT, true).show();
                }
            }

            // name
            if (sql_nameSW.isChecked()) {
                if (TextUtils.isEmpty(student_name.getText().toString()))
                    student_name.setError("New Name is required!");
                else {
                    dbHelper.updateName(student_id.getText().toString(), student_name.getText().toString());
                    Toasty.success(getBaseContext(), "Updated Name of Student with ID " + student_id.getText().toString() + " to " + student_name.getText().toString(), Toast.LENGTH_SHORT, true).show();
                }
            }

            // father name
            if (sql_fathernameSW.isChecked()) {
                if (TextUtils.isEmpty(student_fatherName.getText()))
                    student_fatherName.setError("New Father Name is required!");
                else {
                    dbHelper.updateFatherName(student_id.getText().toString(), student_fatherName.getText().toString());
                    Toasty.success(getBaseContext(), "Updated Father Name of Student with ID " + student_id.getText().toString() + " to " + student_fatherName.getText().toString(), Toast.LENGTH_SHORT, true).show();
                }
            }

            // surname
            if (sql_surnameSW.isChecked()) {
                if (TextUtils.isEmpty(student_surname.getText()))
                    student_surname.setError("New Surname is required!");
                else {
                    dbHelper.updateSurname(student_id.getText().toString(), student_surname.getText().toString());
                    Toasty.success(getBaseContext(), "Updated Surname of Student with ID " + student_id.getText().toString() + " to " + student_surname.getText().toString(), Toast.LENGTH_SHORT, true).show();
                }
            }

            // national id
            if (sql_natIdSW.isChecked()) {
                if (TextUtils.isEmpty(student_natId.getText().toString()))
                    student_natId.setError("New National ID is required!");
                else {
                    dbHelper.updateNatID(student_id.getText().toString(), student_natId.getText().toString());
                    Toasty.success(getBaseContext(), "Updated National ID of Student with ID " + student_id.getText().toString() + " to " + student_natId.getText().toString(), Toast.LENGTH_SHORT, true).show();
                }
            }

            // DoB
            if (sql_dobSW.isChecked()) {
                if (TextUtils.isEmpty(fmtDate.format(c.getTime())))
                    dateButton.setError("New Date of Birth is required!");
                else {
                    dbHelper.updateDoB(student_id.getText().toString(), fmtDate.format(c.getTime()));
                    Toasty.success(getBaseContext(), "Updated Date of Birth of Student with ID " + student_id.getText().toString() + " to " + fmtDate.format(c.getTime()), Toast.LENGTH_SHORT, true).show();
                }
            }

            // Gender
            if (sql_genderSW.isChecked()) {
                if (male.isChecked())
                    gender = "Male";

                if (female.isChecked())
                    gender = "Female";

                if (TextUtils.isEmpty(gender)) {
                    female.setError("New Gender is required!");
                } else {
                    dbHelper.updateGender(student_id.getText().toString(), gender);
                    Toasty.success(getBaseContext(), "Updated Gender of Student with ID " + student_id.getText().toString() + " to " + gender, Toast.LENGTH_SHORT, true).show();
                }
            }
        });

        sql_idSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_id2.setVisibility(View.VISIBLE);
                } else
                    student_id2.setVisibility(View.INVISIBLE);
            }

        });

        sql_nameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    student_name.setVisibility(View.VISIBLE);
                else
                    student_name.setVisibility(View.INVISIBLE);
            }
        });

        sql_fathernameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    student_fatherName.setVisibility(View.VISIBLE);
                else
                    student_fatherName.setVisibility(View.INVISIBLE);
            }
        });

        sql_surnameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_surname.setVisibility(View.VISIBLE);
                } else
                    student_surname.setVisibility(View.INVISIBLE);
            }
        });

        sql_natIdSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_natId.setVisibility(View.VISIBLE);
                } else
                    student_natId.setVisibility(View.INVISIBLE);
            }
        });

        sql_dobSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    dateButton.setVisibility(View.VISIBLE);
                else
                    dateButton.setVisibility(View.INVISIBLE);
            }
        });

        sql_genderSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    grp.setVisibility(View.VISIBLE);
                else
                    grp.setVisibility(View.INVISIBLE);
            }
        });

    }

}