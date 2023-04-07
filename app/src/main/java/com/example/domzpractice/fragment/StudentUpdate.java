package com.example.domzpractice.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.domzpractice.R;


public class StudentUpdate extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);

        Intent intent = getIntent();
        tv = findViewById(R.id.textView);
        String name =   intent.getStringExtra("name");
        tv.setText(name);

    }
}