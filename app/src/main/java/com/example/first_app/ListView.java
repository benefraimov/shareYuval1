package com.example.first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListView extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

//        lv = findViewById(R.id.Listview);
//
//        String foods[] = {"Egg","Pizza","Pasta"};
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.each_item_row,R.id.item,foods);
//
//        lv.setAdapter(arrayAdapter);

    }
}
