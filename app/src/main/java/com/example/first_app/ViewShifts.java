package com.example.first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ViewShifts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shifts);

        findViewById(R.id.btnAddShift).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewShifts.this,InsertShifts.class));
            }
        });

        ListView lv = findViewById(R.id.listView);

        String foods[] = {"Egg","Pizza","Pasta"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.each_item_row,
                R.id.item,foods);
    }
}
