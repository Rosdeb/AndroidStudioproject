package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class singout extends AppCompatActivity {
    TextInputEditText nameout,emialout;
    private FirebaseAuth auths;
    ImageView imageViewout;
    ProgressBar progressBarout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singout);

        nameout= findViewById(R.id.namesingoutid);
        imageViewout= findViewById(R.id.backout);
        auths= FirebaseAuth.getInstance();
        emialout = findViewById(R.id.emailsingoutid);
        auths=FirebaseAuth.getInstance();
        progressBarout = findViewById(R.id.progressbarout);

        imageViewout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    public  void singoutbutton(View view){
        datasavesout();
    }

public void datasavesout(){

    String namein=nameout.getText().toString().trim();
    String emailin=emialout.getText().toString().trim();
    progressBarout.setVisibility(View.VISIBLE);

    if (namein.isEmpty()){
        nameout.requestFocus();
        nameout.setError("Enter your name");
        return;

    }
    if (namein.length()<6){
        nameout.requestFocus();
        nameout.setError("Enter your name should be 6 character");
        return;

    }
    if (emailin.isEmpty()){
        emialout.requestFocus();
        emialout.setError("Enter your name");
        return;

    }
    if (!Patterns.EMAIL_ADDRESS.matcher(emailin).matches()){
        nameout.requestFocus();
        nameout.setError("Enter your name");
        return;

    }

    auths.createUserWithEmailAndPassword(emailin,namein).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressBarout.setVisibility(View.GONE);
            if (task.isSuccessful()){
                startActivity(new Intent(singout.this, MainActivity.class));
                Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
            }
            else {
                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(getApplicationContext(), "User is already login", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });





}

}



