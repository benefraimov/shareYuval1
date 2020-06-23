package com.example.first_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsertShifts extends AppCompatActivity {

    EditText startTime, endTime;
    Button addShift;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    String shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_shifts);

        findViewById(R.id.btnViewShift).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertShifts.this,ViewShifts.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).child("Shifts");

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        addShift = findViewById(R.id.addShift);



        addShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shift = "start: " + startTime.getText().toString() + " end: " + endTime.getText().toString() + "  ";
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String eachShift = ds.getValue(String.class);
                            list.add(eachShift);
                        }
                        list.add(shift);
                        myRef.setValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                myRef.addListenerForSingleValueEvent(valueEventListener);
            }
        });
    }
}
