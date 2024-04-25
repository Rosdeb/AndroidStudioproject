package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name,emial;
    TextView singtext,show;
    Button singin;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    ImageView back;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        name = findViewById(R.id.nameid);
        show = findViewById(R.id.showdatafirebase);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DetailsActivity.class));
            }
        });
        back = findViewById(R.id.back);

        auth= FirebaseAuth.getInstance();

        emial = findViewById(R.id.email);
        singtext = findViewById(R.id.singoutidtextview);
        singin = findViewById(R.id.singbutton);
        progressBar= findViewById(R.id.progressbar);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              datasave();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        singtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, singout.class));

            }
        });

    }

    public  void datasave(){
        String namein=name.getText().toString().trim();
        String emailin=emial.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);

        if (namein.isEmpty()){
            name.requestFocus();
            name.setError("Enter your name");
            return;

        }
        if (namein.length()<6){
            name.requestFocus();
            name.setError("Enter your name should be 6 character");
            return;

        }
        if (emailin.isEmpty()){
            emial.requestFocus();
            emial.setError("Enter your name");
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailin).matches()){
            name.requestFocus();
            name.setError("Enter your name");
            return;

        }

      auth.createUserWithEmailAndPassword(emailin,namein).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              progressBar.setVisibility(View.GONE);
              if (task.isSuccessful()){
                  Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
              }
              else {
                  if(task.getException() instanceof FirebaseAuthUserCollisionException){
                      Toast.makeText(getApplicationContext(),"User is already login",Toast.LENGTH_SHORT).show();

                  }

                  Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
              }
          }
      });

        String key = databaseReference.push().getKey();
        Student student = new Student(namein,emailin);
        databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(),"realtime data save successfull",Toast.LENGTH_SHORT).show();







    }

}