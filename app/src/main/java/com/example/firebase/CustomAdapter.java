package com.example.firebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student> studentList;

    public CustomAdapter( Activity context, List<Student> studentList) {
        super(context, R.layout.detailst,studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.detailst,null,true);
        Student student = studentList.get(position);

        TextView na=  view.findViewById(R.id.name);
        TextView em=  view.findViewById(R.id.email);


        return view;
    }
}
