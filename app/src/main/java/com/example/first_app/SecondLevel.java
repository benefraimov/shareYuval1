package com.example.first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondLevel extends AppCompatActivity {

    TextView userName, counter;
    EditText guessingNumber;
    Button btnGuess;
    SharedPreferences sharedPref;
    int rand_int;
    Random rand = new Random();
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level);
        userName = findViewById(R.id.userNameWelcomePage);
        counter = findViewById(R.id.counter);
        guessingNumber = findViewById(R.id.guessingNumber);
        btnGuess = findViewById(R.id.btnGuess);
        // Generate random integers in range 1 to 10
        rand_int = (rand.nextInt(20)) + 1;

        sharedPref = getSharedPreferences("BenApp", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String name = sharedPref.getString("userName","Not Found!!");

        userName.setText("Second stage you have to\nGuess number between 1-20");

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(),rand_int + "", Toast.LENGTH_LONG).show();
                    int guessNumber = Integer.parseInt(guessingNumber.getText().toString());
                    if(guessNumber == rand_int){
                        editor.putInt("Level", 1);
                        editor.apply();
                        startActivity(new Intent(SecondLevel.this,WinningPage.class));
                    }
                }catch(Exception ex) {
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
                    int counterLeft = Integer.parseInt(counter.getText().toString());
                    counterLeft--;
                    counter.setText(counterLeft + "");
                    if(counterLeft == 0){
                        startActivity(new Intent(SecondLevel.this,secondActivity.class));
                    }

            }
        });
    }
}