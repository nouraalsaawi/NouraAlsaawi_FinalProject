package com.example.nouraalsaawisproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class sqlDelete extends AppCompatActivity {

    final DatabaseHelper dbHelper = new DatabaseHelper(this);
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_delete);

        EditText idTf = findViewById(R.id.sql_idEditText_del);

        CheckBox idCb = findViewById(R.id.sql_idCB);
        CheckBox nameCb = findViewById(R.id.sql_nameCB);
        CheckBox fatherNameCb = findViewById(R.id.sql_fathernameCB);
        CheckBox surnameCb = findViewById(R.id.sql_surnameCB);
        CheckBox natIdCb = findViewById(R.id.sql_natIdCB);
        CheckBox dobCb = findViewById(R.id.sql_dobCB);
        CheckBox genderCb = findViewById(R.id.sql_genderCB);
        CheckBox allCb = findViewById(R.id.sql_allCB); //delete whole record

        Button delete = findViewById(R.id.sqlDelStudent);

        allCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    idCb.setEnabled(false);
                    nameCb.setEnabled(false);
                    fatherNameCb.setEnabled(false);
                    surnameCb.setEnabled(false);
                    natIdCb.setEnabled(false);
                    dobCb.setEnabled(false);
                    genderCb.setEnabled(false);
                } else {
                    idCb.setEnabled(true);
                    nameCb.setEnabled(true);
                    fatherNameCb.setEnabled(true);
                    surnameCb.setEnabled(true);
                    natIdCb.setEnabled(true);
                    dobCb.setEnabled(true);
                    genderCb.setEnabled(true);
                }

            }
        });

        delete.setOnClickListener(view -> {
            String id = idTf.getText().toString();
            if (TextUtils.isEmpty(id))
                idTf.setError("ID is required!");
            else {
                if (allCb.isChecked())
                    deleteAll(idTf.getText().toString());
                else
                    deleteAttribute(idTf.getText().toString(), idCb.isChecked(), nameCb.isChecked(), fatherNameCb.isChecked(), surnameCb.isChecked(), natIdCb.isChecked(), dobCb.isChecked(), genderCb.isChecked());
            }
        });
    }

    public void deleteAll(String id) {
        dbHelper.deleteStudent(id);
        Toasty.success(getBaseContext(), "Deleted record with ID " + id + " successfully", Toast.LENGTH_SHORT, true).show();
    }

    private void deleteAttribute(String idStr, Boolean id, Boolean name, Boolean fatherName, Boolean surname, Boolean natId, Boolean dob, Boolean gender) {

        if (id) {
            dbHelper.updateID(idStr, null);
        }
        if (name) {
            dbHelper.updateName(idStr, null);
        }
        if (fatherName) {
            dbHelper.updateFatherName(idStr, null);
        }
        if (surname) {
            dbHelper.updateSurname(idStr, null);
        }
        if (natId) {
            dbHelper.updateNatID(idStr, null);
        }
        if (dob) {
            dbHelper.updateDoB(idStr, null);
        }
        if (gender) {
            dbHelper.updateGender(idStr, null);
        }
        Toasty.success(getBaseContext(), "Selected attributes of Student with ID " + idStr + " has been deleted", Toast.LENGTH_SHORT, true).show();
    }
}