package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth auths;
    EditText email,password;
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        email = findViewById(R.id.nameidfirebase);
        password =findViewById(R.id.passwordfirebse);
        button = findViewById(R.id.datasavefirebase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datasave();
            }
        });
        auths = FirebaseAuth.getInstance();

    }
    private  void datasave(){

        String email1 = email.getText().toString().trim();
        String password2  = password.getText().toString().trim();
        auths.createUserWithEmailAndPassword(email1,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                }else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already login",Toast.LENGTH_SHORT).show();

                    }

                    Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}