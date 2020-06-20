package com.example.first_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText userName, userPass;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this, secondActivity.class));
        }
        else{
            //Do nothing
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//
//        sharedPref = getSharedPreferences("BenApp",Context.MODE_PRIVATE);
//        editor = sharedPref.edit();
//
//        //if the user is already connected isLogin will be true
//        //and the user get the second page
//        boolean isLogin = sharedPref.getBoolean("isLogin", false);
//        if(isLogin){
//            startActivity(new Intent(MainActivity.this, secondActivity.class));
//        }
        //Connect the views to the xml.
        userName = findViewById(R.id.userName);
        userPass = findViewById(R.id.userPass);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(userName.getText().toString(), userPass.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
                //Check user name and pass
//                if (userName.getText().toString().equals("ben")&&userPass.getText().toString().equals("123")){
//                    //Working with shared preferences
//                    editor.putString("userName",userName.getText().toString());
//                    editor.putString("userPass",userPass.getText().toString());
//                    editor.putBoolean("isLogin", true);
//                    editor.apply();
//                    //Example for making a toast
//                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
//
//                    //Send Sms activity
//                    Intent i = new Intent(MainActivity.this,secondActivity.class);
//                    //Guessing game
////                    Intent i = new Intent(MainActivity.this,SecondActivity.class);
////                    //Saving values with Intent: i.putExtra("userName",userName.getText().toString());
//                    startActivity(i);
                //}
            }
        });

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to third activity
                //startActivity(new Intent(MainActivity.this,thirdActivity.class));

                //List view activity
                startActivity(new Intent(MainActivity.this,thirdActivity.class));
            }
        });


    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            startActivity(new Intent(MainActivity.this, secondActivity.class));
        }
        else{
            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
        }
    }
}