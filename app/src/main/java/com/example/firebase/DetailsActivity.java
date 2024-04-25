package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    ListView listView ;
    CustomAdapter customAdapter;
    DatabaseReference databaseReference ;
    private List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        listView = findViewById(R.id.listviewid);
        databaseReference = FirebaseDatabase.getInstance().getReference("student");
        studentList = new ArrayList<>();
        customAdapter= new CustomAdapter(DetailsActivity.this,studentList);
    }

    @Override
    protected void onStart() {databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            studentList.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                Student student  = dataSnapshot .getValue(Student.class);
                studentList.add(student);
            }
            listView.setAdapter(customAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
        super.onStart();
    }
}