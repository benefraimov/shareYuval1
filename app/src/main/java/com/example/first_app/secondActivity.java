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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

// ThIS is the welcome page
public class secondActivity extends AppCompatActivity {

    TextView userName, counter;
    EditText guessingNumber;
    Button btnGuess;
    SharedPreferences sharedPref;
    int rand_int;
    Random rand = new Random();
    SharedPreferences.Editor editor;
    FirebaseAuth mAuth;
    TextView hello;
    //Reference for Users
    DatabaseReference myRefUsers = FirebaseDatabase.getInstance().getReference("Users");
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        hello = findViewById(R.id.userNameWelcomePage);

        // Read from the database
        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.child(user.getUid()).getValue(User.class);
                hello.setText("hello, " + value.getFullName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        userName = findViewById(R.id.userNameWelcomePage);
        counter = findViewById(R.id.counter);
        guessingNumber = findViewById(R.id.guessingNumber);
        btnGuess = findViewById(R.id.btnGuess);
        // Generate random integers in range 1 to 10
        rand_int = (rand.nextInt(10)) + 1;



//        sharedPref = getSharedPreferences("BenApp", Context.MODE_PRIVATE);
//        editor = sharedPref.edit();
//        String name = sharedPref.getString("userName","Not Found!!");
//
//        int level = sharedPref.getInt("Level", 1);
//        if(level==2){
//            startActivity(new Intent(secondActivity.this, SecondLevel.class));
//        }
//
//        userName.setText("Hello, " + name + "\nWelcome to Guessing game.\nFirst stage you have to\nGuess number between 1-10");

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(),rand_int + "", Toast.LENGTH_LONG).show();
                    int guessNumber = Integer.parseInt(guessingNumber.getText().toString());
                    if(guessNumber == rand_int){
                        editor.putInt("Level",2);
                        editor.apply();
                        startActivity(new Intent(secondActivity.this,SecondLevel.class));
                    }
                }catch(Exception ex) {
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_LONG).show();
                    }
                    int counterLeft = Integer.parseInt(counter.getText().toString());
                    counterLeft--;
                    counter.setText(counterLeft + "");
                    if(counterLeft == 0){
                        rand_int = (rand.nextInt(10)) + 1;
                        counter.setText("5");
                    }

            }
        });

        (findViewById(R.id.btnLogOut)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.remove("isLogin");
//                editor.apply();
                mAuth.signOut();
                startActivity(new Intent(secondActivity.this,MainActivity.class));
            }
        });

        // Get the Saved Values By Intent ****************************************
        //String name = getIntent().getStringExtra("userName");
        //************************************************************************
    }

    @Override
    public void onBackPressed() {
        //1. log out and then go back to login page
        //2. close the App
    }
}