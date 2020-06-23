package com.example.first_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class thirdActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText userEmail, userFullName, userPass, userSecondPass, userPhone;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activty);
        //Connect the views to the xml.
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPass);
        userSecondPass = findViewById(R.id.userSecondPass);
        userFullName = findViewById(R.id.userFullName);
        userPhone = findViewById(R.id.userPhone);


        //DataBase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("message");

        findViewById(R.id.btnSignUpPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPass.getText().toString())
                            .addOnCompleteListener(thirdActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Write a message to the database
                                        User newUser = new User(userEmail.getText().toString(), userFullName.getText().toString(), userPhone.getText().toString());
                                        //myRef.setValue("Hello, World!");

                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        myRef = database.getReference("Users").child(user.getUid());
                                        myRef.setValue(newUser);
                                        updateUI(user);

                                        Toast.makeText(thirdActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                                        //Toast.makeText(thirdActivity.this, "Authentication good.",Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(thirdActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });
                } catch (Exception ex) {
                    Toast.makeText(thirdActivity.this, "You have empty fields.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            startActivity(new Intent(thirdActivity.this, MainActivity.class));
        }
        else{
            Toast.makeText(thirdActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
