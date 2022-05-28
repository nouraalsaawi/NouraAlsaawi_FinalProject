package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class firebaseUpdate extends AppCompatActivity {
    String gender = "";
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_update);

        Button fbUpdateStudent = findViewById(R.id.fbUpdateStudent);
        Button fbUpdate_fbMain = findViewById(R.id.fbUpdate_fbMain);
        EditText student_id = findViewById(R.id.fb_idEditText_update);

        Switch fb_idSW = findViewById(R.id.idSwitch);
        Switch fb_nameSW = findViewById(R.id.nameSwitch);
        Switch fb_fathernameSW = findViewById(R.id.fatherNameSwitch);
        Switch fb_surnameSW = findViewById(R.id.surnameSwitch);
        Switch fb_natIdSW = findViewById(R.id.natIdSwitch);
        Switch fb_dobSW = findViewById(R.id.dobSwitch);
        Switch fb_genderSW = findViewById(R.id.genderSwitch);

        EditText student_id2 = findViewById(R.id.fb_idEditText_update2);
        EditText student_name = findViewById(R.id.fb_nameEditText_update);
        EditText student_fatherName = findViewById(R.id.fb_fathernameEditText_update);
        EditText student_surname = findViewById(R.id.fb_surnameEditText_update);
        EditText student_natId = findViewById(R.id.fb_natIdEditText_update);

        Button dateButton = findViewById(R.id.dateButton_update);
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

        dateButton.setOnClickListener(view -> new DatePickerDialog(firebaseUpdate.this, d,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show());

        RadioGroup grp = findViewById(R.id.grp);
        RadioButton male = findViewById(R.id.fb_male_update);
        RadioButton female = findViewById(R.id.fb_female_update);


        fbUpdate_fbMain.setOnClickListener(view -> {
            startActivity(new Intent(firebaseUpdate.this, firebaseMain.class));
        });

        fbUpdateStudent.setOnClickListener(view -> {
            if (TextUtils.isEmpty(student_id.getText()))
                student_id.setError("ID is required!");

            // id
            if (fb_idSW.isChecked()) {
                if (TextUtils.isEmpty(student_id2.getText().toString()))
                    student_id2.setError("New ID is required!");
                else
                    updateStudent(student_id.getText().toString(), "id", student_id2.getText().toString());
            }

            // name
            if (fb_nameSW.isChecked()) {
                if (TextUtils.isEmpty(student_name.getText().toString()))
                    student_name.setError("New Name is required!");
                else
                    updateStudent(student_id.getText().toString(), "name", student_name.getText().toString());
            }

            // father name
            if (fb_fathernameSW.isChecked()) {
                if (TextUtils.isEmpty(student_fatherName.getText()))
                    student_fatherName.setError("New Father Name is required!");
                else
                    updateStudent(student_id.getText().toString(), "fatherName", student_fatherName.getText().toString());
            }

            // surname
            if (fb_surnameSW.isChecked()) {
                if (TextUtils.isEmpty(student_surname.getText()))
                    student_surname.setError("New Surname is required!");
                else
                    updateStudent(student_id.getText().toString(), "surname", student_surname.getText().toString());
            }

            // national id
            if (fb_natIdSW.isChecked()) {
                if (TextUtils.isEmpty(student_natId.getText().toString()))
                    student_natId.setError("New National ID is required!");
                else
                    updateStudent(student_id.getText().toString(), "natID", student_natId.getText().toString());
            }

            // DoB
            if (fb_dobSW.isChecked()) {
                if (TextUtils.isEmpty(fmtDate.format(c.getTime())))
                    dateButton.setError("New Date of Birth is required!");
                else
                    updateStudent(student_id.getText().toString(), "dob", fmtDate.format(c.getTime()));
            }

            // Gender
            if (fb_genderSW.isChecked()) {
                if (male.isChecked())
                    gender = "Male";

                if (female.isChecked())
                    gender = "Female";

                if (TextUtils.isEmpty(gender)) {
                    female.setError("New Gender is required!");
                } else
                    updateStudent(student_id.getText().toString(), "gender", gender);

            }
        });

        fb_idSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_id2.setVisibility(View.VISIBLE);
                } else
                    student_id2.setVisibility(View.INVISIBLE);
            }

        });

        fb_nameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    student_name.setVisibility(View.VISIBLE);
                else
                    student_name.setVisibility(View.INVISIBLE);
            }
        });

        fb_fathernameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    student_fatherName.setVisibility(View.VISIBLE);
                else
                    student_fatherName.setVisibility(View.INVISIBLE);
            }
        });

        fb_surnameSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_surname.setVisibility(View.VISIBLE);
                } else
                    student_surname.setVisibility(View.INVISIBLE);
            }
        });

        fb_natIdSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    student_natId.setVisibility(View.VISIBLE);
                } else
                    student_natId.setVisibility(View.INVISIBLE);
            }
        });

        fb_dobSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    dateButton.setVisibility(View.VISIBLE);
                else
                    dateButton.setVisibility(View.INVISIBLE);
            }
        });

        fb_genderSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    grp.setVisibility(View.VISIBLE);
                else
                    grp.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void updateStudent(String id, String attributeToUpdate, String newValue) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("Student").child(id);

        Task<Void> mtask = dbRef.child(attributeToUpdate).setValue(newValue);

        mtask.addOnSuccessListener(aVoid -> {
            Toasty.success(getBaseContext(), "Updated " + attributeToUpdate + " Successfully", Toast.LENGTH_SHORT, true).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getBaseContext(), "Failed to Update " + attributeToUpdate, Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}