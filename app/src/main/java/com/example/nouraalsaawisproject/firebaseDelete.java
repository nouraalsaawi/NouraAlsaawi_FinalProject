package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class firebaseDelete extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_delete);

        Button fbDelStudent = findViewById(R.id.fbDelStudent);
        Button fbDel_fbMain = findViewById(R.id.fbDel_fbMain);
        EditText student_id = findViewById(R.id.fb_idEditText_del);

        CheckBox fb_idCB = findViewById(R.id.fb_idCB);
        CheckBox fb_nameCB = findViewById(R.id.fb_nameCB);
        CheckBox fb_fathernameCB = findViewById(R.id.fb_fathernameCB);
        CheckBox fb_surnameCB = findViewById(R.id.fb_surnameCB);
        CheckBox fb_natIdCB = findViewById(R.id.fb_natIdCB);
        CheckBox fb_dobCB = findViewById(R.id.fb_dobCB);
        CheckBox fb_genderCB = findViewById(R.id.fb_genderCB);
        CheckBox fb_allCB = findViewById(R.id.fb_allCB);

        fb_allCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    fb_idCB.setEnabled(false);
                    fb_nameCB.setEnabled(false);
                    fb_fathernameCB.setEnabled(false);
                    fb_surnameCB.setEnabled(false);
                    fb_natIdCB.setEnabled(false);
                    fb_dobCB.setEnabled(false);
                    fb_genderCB.setEnabled(false);
                } else {
                    fb_idCB.setEnabled(true);
                    fb_nameCB.setEnabled(true);
                    fb_fathernameCB.setEnabled(true);
                    fb_surnameCB.setEnabled(true);
                    fb_natIdCB.setEnabled(true);
                    fb_dobCB.setEnabled(true);
                    fb_genderCB.setEnabled(true);
                }

            }
        });

        fbDel_fbMain.setOnClickListener(view -> {
            startActivity(new Intent(firebaseDelete.this, firebaseMain.class));
        });

        fbDelStudent.setOnClickListener(view -> {
            if (fb_allCB.isChecked())
                deleteAll(student_id.getText().toString());
            else
                deleteAttribute(student_id.getText().toString(), fb_idCB.isChecked(), fb_nameCB.isChecked(), fb_fathernameCB.isChecked(), fb_surnameCB.isChecked(), fb_natIdCB.isChecked(), fb_dobCB.isChecked(), fb_genderCB.isChecked());
        });

    } //end of onCreate

    private void deleteAll(String id) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("Student").child(id);

        Task<Void> mtask = dbRef.removeValue();

        mtask.addOnSuccessListener(aVoid -> {
            Toasty.success(getBaseContext(), "Deleted Successfully", Toast.LENGTH_SHORT, true).show();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(getBaseContext(), "Failed to Deleted", Toast.LENGTH_SHORT, true).show();
            }
        });
    } //end of deleteAll

    private void deleteAttribute(String idStr, Boolean id, Boolean name, Boolean fatherName, Boolean surname, Boolean natId, Boolean dob, Boolean gender) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("Student").child(idStr);

        Task<Void> mtask;

        if (id) {
            mtask = dbRef.child("id").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted ID Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete ID", Toast.LENGTH_SHORT, true).show();
                }
            });
        }

        if (name) {
            mtask = dbRef.child("name").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted Name Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete Name", Toast.LENGTH_SHORT, true).show();
                }
            });
        }
        if (fatherName) {
            mtask = dbRef.child("fatherName").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted Father Name Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete Father Name", Toast.LENGTH_SHORT, true).show();
                }
            });
        }

        if (surname) {
            mtask = dbRef.child("surname").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted Surname Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete Surname", Toast.LENGTH_SHORT, true).show();
                }
            });
        }

        if (natId) {
            mtask = dbRef.child("natID").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted National ID Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete National ID", Toast.LENGTH_SHORT, true).show();
                }
            });
        }
        if (dob) {
            mtask = dbRef.child("dob").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted Date of Birth Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete Date of Birth", Toast.LENGTH_SHORT, true).show();
                }
            });
        }
        if (gender) {
            mtask = dbRef.child("gender").setValue("null");
            mtask.addOnSuccessListener(aVoid -> {
                Toasty.success(getBaseContext(), "Deleted Gender Successfully", Toast.LENGTH_SHORT, true).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(getBaseContext(), "Failed to Delete Gender", Toast.LENGTH_SHORT, true).show();
                }
            });
        }

    } //end of deleteAttribute
}
