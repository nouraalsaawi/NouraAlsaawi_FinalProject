package com.example.nouraalsaawisproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class firebaseSearch extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://nouraalsaawi-s-project-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();

    TextView id;
    TextView name;
    TextView fatherName;
    TextView surname;
    TextView natID;
    TextView dob;
    TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_search);

        id = findViewById(R.id.searchId);
        name = findViewById(R.id.searchName);
        fatherName = findViewById(R.id.searchFatherName);
        surname = findViewById(R.id.searchSurname);
        natID = findViewById(R.id.searchNatID);
        dob = findViewById(R.id.searchDoB);
        gender = findViewById(R.id.searchGender);

        Button search = findViewById(R.id.fbSearchStudent);
        Button fbSearch_fbMain = findViewById(R.id.fbSearch_fbMain);
        View searchLayout = findViewById(R.id.searchLayout);

        EditText idTf = findViewById(R.id.fb_idEditText_search);

        fbSearch_fbMain.setOnClickListener(view -> {
            startActivity(new Intent(firebaseSearch.this, firebaseMain.class));
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(idTf.getText()))
                    idTf.setError("ID is required!");
                else {
                    searchLayout.setVisibility(View.VISIBLE);
                    search(idTf.getText().toString(), id, name, fatherName, surname, natID, dob, gender);
                }

            }
        });
    } // end of onCreate

    private void search(String ID, TextView idText, TextView name, TextView father,
                        TextView surname, TextView natID, TextView dob, TextView gender) {
        dbRef = db.getReference().child("Student").child(ID);
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

                idText.setText(idS);
                name.setText(nameS);
                father.setText(fatherNameS);
                surname.setText(surnameS);
                natID.setText(natIDS);
                dob.setText(dobS);
                gender.setText(genderS);

                Toasty.info(getBaseContext(), "Student " + idS + " Information", Toast.LENGTH_SHORT, true).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toasty.error(getBaseContext(), "Failed to retrieve information", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}